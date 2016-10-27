package com.example.dllo.foodgroup.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.foodgroup.R;

/**
 * Created by dllo on 16/10/21.
 */
public abstract class BaseFragment extends Fragment {
    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayout();

    protected void setClick(View.OnClickListener clickListener,View ... views){
        for (View view : views){
            view.setOnClickListener(clickListener);
        }
    }

    protected <T extends View> T bindView(int id){
        return (T) getView().findViewById(id);
    }
    protected <T extends View> T bindView(View view,int id){
        return (T) view.findViewById(id);
    }
}
