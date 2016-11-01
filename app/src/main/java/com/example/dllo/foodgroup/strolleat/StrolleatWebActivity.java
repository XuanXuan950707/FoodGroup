package com.example.dllo.foodgroup.strolleat;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;
import com.example.dllo.foodgroup.strolleat.knowledge.KnowledgeFragment;

/**
 * Created by dllo on 16/10/31.
 */
public class StrolleatWebActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private ImageButton back;

    @Override
    protected int getLayout() {
        return R.layout.activity_strolleatweb;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.strolleatweb_webview);
        back = bindView(R.id.strolleatweb_return);

        setClick(this, back);
    }

    @Override
    protected void iniData() {
        Intent intent = getIntent();
        webView.loadUrl(intent.getStringExtra("url"));
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.strolleatweb_return:
                onBackPressed();
                break;

        }
    }
}
