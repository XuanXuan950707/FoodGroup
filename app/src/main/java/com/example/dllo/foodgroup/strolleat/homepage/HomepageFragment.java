package com.example.dllo.foodgroup.strolleat.homepage;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseFragment;

/**
 * Created by dllo on 16/10/24.
 */
public class HomepageFragment extends BaseFragment {

    private SwipeRefreshLayout srl;
    private RecyclerView rc;

    @Override
    protected void initData() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(false);
            }
        });


    }

    @Override
    protected void initView() {
        srl = bindView(R.id.homepage_srl);
        rc = bindView(R.id.homepage_rc);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_homepage;
    }
}
