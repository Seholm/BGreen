package com.bgreen.filips.bgreen.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bgreen.filips.bgreen.profile.ProfileFragment;
import com.bgreen.filips.bgreen.R;
import com.bgreen.filips.bgreen.TestFragment;

public class TabActivity extends AppCompatActivity {

    String profileEmail;
    String profileName;
    String profileID;
    String profileImgURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        Bundle extras = getIntent().getExtras();
        profileName = extras.getString("PROFILE_NAME");
        profileEmail = extras.getString("PROFILE_MAIL");
        profileImgURL = extras.getString("PROFILE_IMAGE");
        profileID = extras.getString("PROFILE_ID");

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
        Bundle bundle = new Bundle();
        bundle.putString("PROFILE_NAME_TO_FRAGMENT", profileName);
        bundle.putString("PROFILE_EMAIL_TO_FRAGMENT", profileEmail);
        bundle.putString("PROFILE_IMG_URL_TO_FRAGMENT", profileImgURL);
        bundle.putString("PROFILE_ID_TO_FRAGMENT", profileID);

        profileFragment.setArguments(bundle);

        //addFrag sets a new fragment to the Tab
        adapter.addFrag(profileFragment, "PROFILE");
        adapter.addFrag(new TestFragment(), "TEST");
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
}
