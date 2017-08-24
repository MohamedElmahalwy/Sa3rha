package com.sa3rha.android.sa3rha.Controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maged on 8/24/2017.
 */

public class ComparePagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments= new ArrayList<>();
    List <String> titles= new ArrayList<>();

    public ComparePagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    public void addFragment(Fragment fragment ,String title){
        fragments.add(fragment);
        titles.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}
