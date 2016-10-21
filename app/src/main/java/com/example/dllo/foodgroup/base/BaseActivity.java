package com.example.dllo.foodgroup.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/10/21.
 */
public abstract class BaseActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayout());
    }
    protected abstract int getLayout();

    // 初始化组件,各种findViewById
    protected abstract void initView();

    // 初始化数据,基本上就是拉去网络数据
    protected abstract void iniData();

    // 简化findViewById省去强转的过程
    protected <T extends View> T bindView(int id){
        return (T) findViewById(id);
    }

    protected void setClick(View.OnClickListener clickListener,View ... views){
        for (View view : views){
            view.setOnClickListener(clickListener);
        }
    }
}
