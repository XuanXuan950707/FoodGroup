package com.example.dllo.foodgroup.main;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;
import com.example.dllo.foodgroup.letorm.DBTool;
import com.example.dllo.foodgroup.letorm.WebCollectBean;
import com.example.dllo.foodgroup.strolleat.knowledge.KnowledgeFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/31.
 */
public class StrolleatWebActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private ImageButton back;
    private String title;
    private String url;
    private LinearLayout collect;
    private DBTool dbTool;
    private ImageView heart;
    private TextView collectText;

    @Override
    protected int getLayout() {
        return R.layout.activity_strolleatweb;
    }

    @Override
    protected void initView() {
        heart = bindView(R.id.strolleatweb_heart);
        collectText = bindView(R.id.strolleatweb_collectText);
        webView = bindView(R.id.strolleatweb_webview);
        back = bindView(R.id.strolleatweb_return);
        collect = bindView(R.id.strolleatweb_collect);
        setClick(this, back,collect);
    }

    @Override
    protected void iniData() {
        dbTool = DBTool.getInstance();
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        Log.d("StrolleatWebActivity", intent.getStringExtra("title"));
        url = intent.getStringExtra("url");
        dbTool.queryAllWebCollect(new DBTool.OnQueryCollectListener() {
            @Override
            public void onQuery(ArrayList<WebCollectBean> webCollectBeen) {
                for (int i = 0; i < webCollectBeen.size(); i++) {
                    if (webCollectBeen.get(i).getTitle().equals(title)&&webCollectBeen.get(i).getUrl().equals(url)){
                        hadCollect();
                    }
                }
            }
        });

        webView.loadUrl(url);
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
            case R.id.strolleatweb_collect:
                setCollect();
                break;

        }
    }
    private void hadCollect(){
        heart.setImageResource(R.mipmap.ic_favorate_checked);
        collectText.setText("已收藏");
    }

    private void setCollect() {
        if (collectText.getText().equals("收藏")) {
            hadCollect();
            WebCollectBean bean = new WebCollectBean();
            bean.setTitle(title);
            bean.setUrl(url);
            dbTool.insertWebCollect(bean);
        }else if (collectText.getText().equals("已收藏")){
            heart.setImageResource(R.mipmap.ic_favorate_unchecked);
            collectText.setText("收藏");
            dbTool.deleteWebCollect(url);
        }
    }
}
