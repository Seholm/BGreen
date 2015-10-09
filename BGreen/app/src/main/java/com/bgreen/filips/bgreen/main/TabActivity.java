package com.bgreen.filips.bgreen.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.bgreen.filips.bgreen.profile.ProfileFragment;
import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.TestFragment;
import com.bgreen.filips.bgreen.toplist.ToplistFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class TabActivity extends AppCompatActivity implements View.OnClickListener {


    private boolean popupAchivmentShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.tab_toolbar);
        setSupportActionBar(toolbar);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.tab_viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Fragment profileFragment = new ProfileFragment();
        Fragment toplistFragment = new ToplistFragment();


        //addFrag sets a new fragment to the Tab
        adapter.addFrag(profileFragment, "PROFILE");
        adapter.addFrag(new TestFragment(), "TEST");
        adapter.addFrag(toplistFragment, "TOPLIST");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab, menu);
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
}
