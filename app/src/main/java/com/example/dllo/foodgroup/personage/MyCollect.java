package com.example.dllo.foodgroup.personage;

import android.view.View;
import android.widget.ImageView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;

/**
 * Created by dllo on 16/11/12.
 */
public class MyCollect extends BaseActivity implements View.OnClickListener {

    private ImageView back;

    @Override
    protected int getLayout() {
        return R.layout.activity_mycollect;
    }

    @Override
    protected void initView() {
        back = bindView(R.id.mycollect_return);
        setClick(this,back);
    }

    @Override
    protected void iniData() {

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
