package com.bgreen.filips.bgreen.main;


import android.app.SearchManager;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.FrameLayout;


import android.widget.Toast;

import com.bgreen.filips.bgreen.achievements.presenter.AchievementFragment;
import com.bgreen.filips.bgreen.profile.model.IProfile;

import com.bgreen.filips.bgreen.profile.presenter.ProfileFragment;
import com.bgreen.filips.bgreen.R;

import com.bgreen.filips.bgreen.profile.model.ProfileHolder;
import com.bgreen.filips.bgreen.search.ISearchModel;
import com.bgreen.filips.bgreen.search.SearchModel;
import com.bgreen.filips.bgreen.toplist.ToplistFragment;


import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;
public class TabActivity extends AppCompatActivity implements View.OnClickListener {



    private boolean popupAchivmentShow; // A boolean representing if the popupAchivment should show or not
    private int currentPage;
    private final int POPUPACHIVMENT_SHOULD_NOT_SHOW = 10;

    private MyViewPager viewPager;
    private ToplistFragment originalTopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.tab_toolbar);
        setSupportActionBar(toolbar);
        originalTopList = new ToplistFragment();

        final MyViewPager viewPager = (MyViewPager) findViewById(R.id.tab_viewpager);

        this.viewPager = viewPager;
        setupViewPager(viewPager);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_tabs);
        tabLayout.setId(View.generateViewId());
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //If scrolling away from toplist, the toplist should be the original toplist

                if (position<1&&viewPager.getEnabledSwipe()==true) {
                    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();

                    originalTopList.enableFlip();

                    fragmentTransaction.replace(R.id.toplist_top_container, originalTopList);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
                //If scrolling away from toplist, the toplist should be the original toplist
                //If the pageswipe is disabled then enable it
                else if(position<1&& viewPager.getEnabledSwipe()==false){
                    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    originalTopList.disableFlip();
                    fragmentTransaction.replace(R.id.toplist_top_container, originalTopList);
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.commit();
                }
            }
            //make sure that when onBackPressed is called we make sure the currentpage is profile
            //so that onBackPressed don't exit application when current page is toplist
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private ViewPagerAdapter adapter;
    private ToplistFragment toplistFragment;
    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        toplistFragment = new ToplistFragment();
        Fragment aFragment = new AchievementFragment();
        //Creates a profilefragment with a achievementfragment in it
        ProfileFragment profileFragment = ProfileFragment.newInstance(aFragment);

        //addFrag sets a new fragment to the Tab
        adapter.addFrag(profileFragment, "PROFILE");
        adapter.addFrag(toplistFragment, "TOPLIST");

        viewPager.setAdapter(adapter);
        viewPager.setId(View.generateViewId());
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);


        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        final ISearchModel sModel = new SearchModel();

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    //When doing a search, the viewpager swipe should be disabled
                    viewPager.setEnabledSwipe(false);
                    //Get the fragment that is displayed in toplist tab and disable flip
                    ToplistFragment newToplistFragment = (ToplistFragment)getSupportFragmentManager().findFragmentById(R.id.toplist_top_container);
                    //Should not be able to flip card while searching
                    newToplistFragment.disableFlip();
                    //Sets the flipdisabled fragment as the fragment
                    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.toplist_top_container, newToplistFragment);
                    fragmentTransaction.commit();

                }else{
                    //if search is canceled, swipe should be available again
                    viewPager.setEnabledSwipe(true);
                    //if search is calceled, collapse the action view
                    menu.findItem(R.id.search).collapseActionView();

                    //if no search has been done the original list will be shown with flip enabled
                    if(sModel.isSearchDone()==false){
                        //Get the fragment that is displayed in toplisttab and enable flip
                        ToplistFragment newToplistFragment = (ToplistFragment)getSupportFragmentManager().findFragmentById(R.id.toplist_top_container);
                        //Should not be able to flip card while searching
                        newToplistFragment.enableFlip();
                        //sets the flipable fragment att the toplisttab fragment
                        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
                        fragmentTransaction.replace(R.id.toplist_top_container, newToplistFragment);
                        fragmentTransaction.commit();
                    }
                }
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //If someone presses the searchbutton then a search is made
            @Override
            public boolean onQueryTextSubmit(String query) {
                //retrives all profiles and executes a search with the query
                List<IProfile> profiles = ProfileHolder.getInstance().getProfiles();
                List<IProfile> searchList = sModel.doSearch(query, profiles);

                //if no result show a toast
                if (searchList.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Din sökning gav inga träffar",
                            Toast.LENGTH_LONG).show();


                }
                //Creates a new list with the result from the search
                Bundle bundle = new Bundle();
                for (int i = 0; i < searchList.size(); i++) {
                    bundle.putParcelable(i + "", searchList.get(i));

                }
                bundle.putInt("Storlek", searchList.size());
                //Creates new toplistfragment with new list
                ToplistFragment newTopList = ToplistFragment.newInstance(bundle);
                newTopList.enableFlip();

                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.toplist_top_container, newTopList);
                fragmentTransaction.commit();

                searchView.clearFocus();
                searchView.setQuery("", true);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.delete_button)){ // onclick to cancel the popupview that shows when you start the program
            FrameLayout achivmentContainer = (FrameLayout) findViewById(R.id.popup_achivments);
            achivmentContainer.setVisibility(View.GONE);
            CircleImageView button = (CircleImageView) findViewById(R.id.delete_button);
            button.setVisibility(View.GONE);
            //hides the frame when the cancel button is clicked
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        FrameLayout achivmentContainer = (FrameLayout) findViewById(R.id.popup_achivments);
        CircleImageView button = (CircleImageView) findViewById(R.id.delete_button);
        if(popupAchivmentShow) {//happens when you resume the application after you have tabbed out of it
            achivmentContainer.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
        }else{//happens when u resume to this activity from another activity, the popup should not show
            popupAchivmentShow = true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == POPUPACHIVMENT_SHOULD_NOT_SHOW) {
            //happens when u click back from DetailedAchivmentActivity, The popup should not show!
            popupAchivmentShow = false;
        }
    }


    @Override
    public void onBackPressed(){
        //If toplist tab is selected then set viewpager to profile tab
        if(currentPage==1){
            viewPager.setCurrentItem(0);
        }else{

            finish();
            System.exit(0);
        }
    }


}
