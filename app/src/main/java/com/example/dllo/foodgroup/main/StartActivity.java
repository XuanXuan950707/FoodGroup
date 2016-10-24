package com.example.dllo.foodgroup.main;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;

public class StartActivity extends BaseActivity implements View.OnClickListener {

//    private ImageView iv;
    private Intent intent;
    private TextView tv;

    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
//        iv = bindView(R.id.start_iv);
        tv = bindView(R.id.start_tv);
        setClick(this, tv);

    }

    @Override
    protected void iniData() {
        Log.d("StartActivity", "lalala");
        intent = new Intent(StartActivity.this,MainActivity.class);

        timer.start();
    }

    private CountDownTimer timer = new CountDownTimer(3000,1000) {
        @Override
        public void onTick(long l) {
            tv.setText("跳过"+l/1000+"s");
        }

        @Override
        public void onFinish() {
            startActivity(intent);
        }
    };

    @Override
    public void onClick(View view) {
//        startActivity(intent);
    }
}
