package com.example.dllo.foodgroup.main;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;
import com.example.dllo.foodgroup.foodencyclopedia.FoodEncyclopediaFragment;
import com.example.dllo.foodgroup.personage.PersonageFragment;
import com.example.dllo.foodgroup.strolleat.StrollEatFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/21.
 */
public class MainActivity extends BaseActivity {

    private ViewPager vp;
    private TabLayout tb;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        vp = bindView(R.id.main_vp);
        tb = bindView(R.id.main_tb);
    }

    @Override
    protected void iniData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FoodEncyclopediaFragment());
        fragments.add(new StrollEatFragment());
        fragments.add(new PersonageFragment());

        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);

        tb.setTabTextColors(Color.GRAY,Color.DKGRAY);
        tb.getTabAt(0).setIcon(R.drawable.tablayout_foodencyclopedia);
        tb.getTabAt(1).setIcon(R.drawable.tablayout_strolleat);
        tb.getTabAt(2).setIcon(R.drawable.tablayout_personage);


    }
}
