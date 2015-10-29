package com.bgreen.filips.bgreen.main.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Inserts the fragments in the TabActivity and returns
 * a position and the string for the title.
 *
 * Created by Albertsson on 15-09-15.
 */
class ViewPagerAdapter extends FragmentPagerAdapter {

    //A list of all the fragments that the adapter inserts in TabActivity
    private final List<Fragment> mFragmentList = new ArrayList<>();
    //A list of all the titles of the fragments
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    //Adds the inserted fragment to TabActivity with a title
    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
