package com.example.dllo.foodgroup.strolleat;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseFragment;
import com.example.dllo.foodgroup.strolleat.appraisal.AppraisalFragment;
import com.example.dllo.foodgroup.strolleat.cooking.CookingFragment;
import com.example.dllo.foodgroup.strolleat.homepage.HomepageFragment;
import com.example.dllo.foodgroup.strolleat.knowledge.KnowledgeFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class StrollEatFragment extends BaseFragment {

    private ViewPager vp;
    private TabLayout tb;

    @Override
    protected void initData() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomepageFragment());
        fragments.add(new AppraisalFragment());
        fragments.add(new KnowledgeFragment());
        fragments.add(new CookingFragment());
        StrollEatAdapter adapter = new StrollEatAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);
    }

    @Override
    protected void initView() {
        vp = bindView(R.id.strolleat_vp);
        tb = bindView(R.id.strolleat_tb);


    }

    @Override
    protected int getLayout() {
        return R.layout.activity_strolleat;
    }
}
