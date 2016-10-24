package com.example.dllo.foodgroup.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class MainAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments;

    private String[] titles = {"食物百科","逛吃","我的"};
    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public MainAdapter(FragmentManager fm) {
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
