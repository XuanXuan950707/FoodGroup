package com.example.dllo.foodgroup.strolleat.homepage;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;

/**
 * Created by dllo on 16/11/5.
 */
public class AdvertActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private WebView webView;

    @Override
    protected int getLayout() {
        return R.layout.activity_advert;
    }

    @Override
    protected void initView() {
        back = bindView(R.id.homepage_advert_back);
        webView = bindView(R.id.homepage_advert_webView);
        setClick(this,back);
    }

    @Override
    protected void iniData() {
        Intent intent = getIntent();
        webView.loadUrl(intent.getStringExtra("link"));
        webView.setWebViewClient(new WebViewClient());
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100){
                    Log.d("MainActivity", "网页加载完成");
//                    bar.setProgress(newProgress);
                }else {
                    Log.d("MainActivity", "加载%" + newProgress);
//                    bar.setProgress(newProgress);
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.homepage_advert_back:
                onBackPressed();
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(webView.canGoBack())
            {
                webView.goBack();//返回上一页面
                return true;
            }
            else
            {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
