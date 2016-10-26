package com.example.dllo.foodgroup.strolleat.appraisal;

import android.support.v7.widget.RecyclerView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;
import com.example.dllo.foodgroup.base.BaseFragment;

/**
 * Created by dllo on 16/10/24.
 */
public class AppraisalFragment extends BaseFragment {

    private RecyclerView rv;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        rv = bindView(R.id.appraisal_rv);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_appraisal;
    }
}
