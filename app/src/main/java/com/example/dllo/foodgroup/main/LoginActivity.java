package com.example.dllo.foodgroup.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/11/5.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout loginQQ;
    private ImageView back;
    public static final int RESULT = 0;

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
                loginqq();
                break;
        }
    }

    private void loginqq() {
        Log.d("LoginActivity", "QQ");
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.authorize();
        qq.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                PlatformDb platformDb = platform.getDb();
                String name = platformDb.getUserName();
                Log.d("LoginActivity", name);
                String icon = platformDb.getUserIcon();
                Intent intent = new Intent();
                intent.putExtra("name", name);
                intent.putExtra("icon", icon);
                setResult(RESULT, intent);
                finish();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
    }
}
