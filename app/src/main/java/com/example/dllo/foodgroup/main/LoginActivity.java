package com.example.dllo.foodgroup.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;

/**
 * Created by dllo on 16/11/5.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout loginQQ;
    private ImageView back;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        back = bindView(R.id.login_return);
        loginQQ = bindView(R.id.login_qq);
        setClick(this, back,loginQQ);
    }

    @Override
    protected void iniData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_return:
                onBackPressed();
                break;
            case R.id.login_qq:

                break;
        }
    }
}
