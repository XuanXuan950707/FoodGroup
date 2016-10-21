package com.example.dllo.foodgroup;

import android.content.Intent;
import android.os.CountDownTimer;

import com.example.dllo.foodgroup.base.BaseActivity;

public class StartActivity extends BaseActivity {


    private Intent intent;

    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        
    }

    @Override
    protected void iniData() {

        timer.start();
    }

    private CountDownTimer timer = new CountDownTimer(3000,1000) {
        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            intent = new Intent(StartActivity.this,MainActivity.class);
            startActivity(intent);
        }
    };
}
