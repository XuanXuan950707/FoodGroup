package com.example.dllo.foodgroup.tools;

import android.app.Application;
import android.content.Context;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by dllo on 16/10/24.
 *
 * !!!!!!写完app一定一定一定要注册!!!!
 */
public class MyApp extends Application {
    // 所有跟界面无关的 都可以用这个全局的Context
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        ShareSDK.initSDK(this,"19182555737d6");
    }
    public static Context getsContext(){
        return sContext;
    }
}
