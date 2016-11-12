package com.example.dllo.foodgroup.personage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/12.
 */
public class MyCollectAdapter extends FragmentPagerAdapter {
    private String[] titles = {"文章","食物"};
    private ArrayList<Fragment> fragments;

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public MyCollectAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null? 0:fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
