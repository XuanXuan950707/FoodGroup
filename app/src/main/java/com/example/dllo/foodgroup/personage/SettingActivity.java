package com.example.dllo.foodgroup.personage;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/11/16.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton back;
    private Button outLogin;
    private PlatformActionListener platformActionListener;

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        back = bindView(R.id.setting_return);
        outLogin = bindView(R.id.setting_outLogin);
        setClick(this,back,outLogin);

    }

    @Override
    protected void iniData() {
        platformActionListener = new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                platform.getDb().exportData();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        };

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setting_return:
                onBackPressed();
                break;
            case R.id.setting_outLogin:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                if (qq.isAuthValid()) {
                    qq.removeAccount(true);
                }
                qq.setPlatformActionListener(platformActionListener);
//authorize与showUser单独调用一个即可
                qq.authorize();//单独授权，OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息
//isValid和removeAccount不开启线程，会直接返回。
//                qq.removeAccount(true);
                setResult(-1);
                finish();
                break;
        }
    }
}
