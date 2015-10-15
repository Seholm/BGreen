package com.bgreen.filips.bgreen.main;


import android.app.SearchManager;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import android.widget.FrameLayout;


import android.widget.Toast;

import com.bgreen.filips.bgreen.profile.IProfile;

import com.bgreen.filips.bgreen.profile.ProfileFragment;
import com.bgreen.filips.bgreen.R;

import com.bgreen.filips.bgreen.profile.ProfileHolder;
import com.bgreen.filips.bgreen.search.SearchModel;
import com.bgreen.filips.bgreen.toplist.ToplistFragment;


import de.hdodenhof.circleimageview.CircleImageView;

import java.util.ArrayList;
import java.util.List;
public class TabActivity extends AppCompatActivity implements View.OnClickListener {



    private boolean popupAchivmentShow;


    private MyViewPager viewPager;
    private Fragment originalTopList = new ToplistFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.tab_toolbar);
        setSupportActionBar(toolbar);

        final MyViewPager viewPager = (MyViewPager) findViewById(R.id.tab_viewpager);

        this.viewPager = viewPager;
        setupViewPager(viewPager);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_tabs);
        tabLayout.setId(View.generateViewId());
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position<1) {


                    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();

                    fragmentTransaction.replace(R.id.toplist_top_container, originalTopList);
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    private boolean drag = false;
    private ViewPagerAdapter adapter;
    private Fragment toplistFragment;
    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Fragment profileFragment = new ProfileFragment();
        toplistFragment = new ToplistFragment();

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


        // Associate searchable configuration with the SearchView
        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        final SearchModel sModel = new SearchModel();

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    viewPager.setEnabledSwipe(false);

                }else{
                    viewPager.setEnabledSwipe(true);
                    menu.findItem(R.id.search).collapseActionView();
                }
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                viewPager.setCurrentItem(1);
                //viewPager.setEnabledSwipe(false);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                List<IProfile> profiles = ProfileHolder.getInstance().getProfiles();
                List<IProfile> searchList = sModel.doSearch(query, profiles);
                boolean searchGaveresult = true;
                if (searchList.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Din sökning gav inga träffar",
                            Toast.LENGTH_LONG).show();
                    searchGaveresult = false;

                }
                Bundle bundle = new Bundle();
                for (int i = 0; i < searchList.size(); i++) {
                    bundle.putParcelable(i + "", searchList.get(i));

                }
                bundle.putInt("Storlek", searchList.size());

                Fragment newTopList = ToplistFragment.newInstance(bundle);

                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();

                fragmentTransaction.replace(R.id.toplist_top_container, newTopList, "hej");

                fragmentTransaction.addToBackStack("tag_toplist_fragment");

                fragmentTransaction.commit();
                searchView.clearFocus();
                searchView.setQuery("", true);



                //TODO: Fixa så gamla vyn inte syns
                //Varför i själva fan försvinner inte det gamla fragmentet?!?!?!?!
                //Jag hatar fragments av hela mitt hjärta!

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
        if(v == findViewById(R.id.delete_button)){
            FrameLayout achivmentContainer = (FrameLayout) findViewById(R.id.popup_achivments);
            achivmentContainer.setVisibility(View.GONE);
            CircleImageView button = (CircleImageView) findViewById(R.id.delete_button);
            button.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        FrameLayout achivmentContainer = (FrameLayout) findViewById(R.id.popup_achivments);
        CircleImageView button = (CircleImageView) findViewById(R.id.delete_button);
        if(popupAchivmentShow) {
            achivmentContainer.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
        }else{
            popupAchivmentShow = true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == 10) {
            //happens when u click back from DetailedAchivmentActiity
            popupAchivmentShow = false;
        }
    }


    @Override
    public void onBackPressed(){

        finish();
        System.exit(0);


    }


}
