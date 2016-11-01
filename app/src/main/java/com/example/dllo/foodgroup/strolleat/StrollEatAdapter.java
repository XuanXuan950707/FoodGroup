package com.example.dllo.foodgroup.strolleat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class StrollEatAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> fragments;

    private String[] title = {"首页","测评","知识","美食"};
    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public StrollEatAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
