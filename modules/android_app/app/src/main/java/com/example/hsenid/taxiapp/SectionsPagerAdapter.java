package com.example.hsenid.taxiapp;

/**
 * Created by hsenid on 1/8/18.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hsenid.taxiapp.driver.DriverHomePageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList= new ArrayList<>();
    private final List<String> fragmentTitleList= new ArrayList<>();

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position){
        return fragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //return DriverHomePageActivity.PlaceholderFragment.newInstance(position + 1);
        DriverHomePageActivity.PlaceholderFragment myFragment= new DriverHomePageActivity.PlaceholderFragment();
        return  myFragment;
        //return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return fragmentList.size();
    }
}