package com.example.dllo.foodgroup.personage;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseFragment;
import com.example.dllo.foodgroup.main.LoginActivity;
import com.example.dllo.foodgroup.main.PersonageMessageActivity;
import com.example.dllo.foodgroup.tools.CirclemageView;
import com.example.dllo.foodgroup.tools.VolleySingleton;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/10/24.
 */
public class PersonageFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout myCollect;
    private TextView login;
    private  String name;
    private String icon;
    private TextView settingMeaasge;
    private ImageView setting;
    private TextView username;
    private CirclemageView usericon;

    @Override
    protected void initData() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        try {

            PlatformDb platformDb = qq.getDb();
            name = platformDb.getUserName();
            icon = platformDb.getUserIcon();

            if (!TextUtils.isEmpty(name)) {
                login.setVisibility(View.GONE);
                settingMeaasge.setVisibility(View.VISIBLE);
                username.setVisibility(View.VISIBLE);
                username.setText(name);
                VolleySingleton.getInstance().getImage(icon, usericon);
            }
        } catch (NullPointerException e) {

        }
    }

    @Override
    protected void initView() {
        myCollect = bindView(R.id.personage_mycollect);
        login = bindView(R.id.personage_login);
        setting = bindView(R.id.personage_setting);
        settingMeaasge = bindView(R.id.personage_setting_message);
        username = bindView(R.id.personage_username);
        usericon = bindView(R.id.personage_icon);
        setClick(this,myCollect,login, setting,settingMeaasge);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_personage;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.personage_mycollect:
                Intent intent = new Intent(getActivity(),MyCollect.class);
                startActivity(intent);
                break;
            case R.id.personage_login:
                Intent intent1 = new Intent(getContext(), LoginActivity.class);
                startActivityForResult(intent1,1);
                break;
            case R.id.personage_setting:
                Intent intent2 = new Intent(getContext(),SettingActivity.class);
                startActivity(intent2);
                break;
            case R.id.personage_setting_message:
                Intent intent3 = new Intent(getContext(),PersonageMessageActivity.class);
                intent3.putExtra("name",name);
                intent3.putExtra("icon",icon);
                startActivity(intent3);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            login.setVisibility(View.VISIBLE);
            settingMeaasge.setVisibility(View.GONE);
            username.setVisibility(View.GONE);
            username.setText("");
            usericon.setImageResource(R.mipmap.ic_analyse_default);
            return;
        }
        Log.d("MyFragment", "resultCode:" + resultCode);
        if (requestCode == 1 && LoginActivity.RESULT == resultCode && data == null) {
            name = data.getStringExtra("name");
            Log.d("MyFragment123", name);
            icon = data.getStringExtra("icon");
            Log.d("MyFragment123", icon);
            login.setVisibility(View.GONE);
            settingMeaasge.setVisibility(View.VISIBLE);
            username.setVisibility(View.VISIBLE);
            username.setText(name);
            VolleySingleton.getInstance().getImage(icon, usericon);

        }

    }
}
