package com.example.dllo.foodgroup.personage;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/12.
 */
public class MyCollect extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private ViewPager vp;
    private TabLayout tb;

    @Override
    protected int getLayout() {
        return R.layout.activity_mycollect;
    }

    @Override
    protected void initView() {
        vp = bindView(R.id.mycollect_vp);
        tb = bindView(R.id.mycollect_tb);
        back = bindView(R.id.mycollect_return);
        setClick(this,back);
    }

    @Override
    protected void iniData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new CollectArticleFragment());
        fragments.add(new CollectFoodFragment());
        MyCollectAdapter adapter = new MyCollectAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);

        tb.setTabTextColors(Color.GRAY,Color.RED);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mycollect_return:
                onBackPressed();
                break;
        }
    }
}
