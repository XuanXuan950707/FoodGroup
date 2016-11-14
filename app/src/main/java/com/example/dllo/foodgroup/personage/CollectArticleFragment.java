package com.example.dllo.foodgroup.personage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.ListView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseFragment;
import com.example.dllo.foodgroup.letorm.DBTool;
import com.example.dllo.foodgroup.letorm.SearchHistory;
import com.example.dllo.foodgroup.letorm.WebCollectBean;
import com.example.dllo.foodgroup.main.StrolleatWebActivity;
import com.example.dllo.foodgroup.tools.WebCollectListener;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/12.
 */
public class CollectArticleFragment extends BaseFragment implements WebCollectListener{

    private ListView listView;
    private ArrayList<WebCollectBean> arrayList;
    private DBTool dbTool;
    private CollectArticleAdapter articleAdapter;
    private RefreshBroadCast refreshBroadCast;

    @Override
    protected void initData() {
        articleAdapter = new CollectArticleAdapter(getContext());
        articleAdapter.setWebCollectListener(this);
        arrayList = new ArrayList<>();
        dbTool = DBTool.getInstance();
        dbTool.queryAllWebCollect(new DBTool.OnQueryCollectListener() {
            @Override
            public void onQuery(ArrayList<WebCollectBean> webCollectBeen) {
                articleAdapter.setArrayList(webCollectBeen);
                listView.setAdapter(articleAdapter);
            }
        });
        refreshBroadCast = new RefreshBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("CollectWeb");
        getContext().registerReceiver(refreshBroadCast,filter);
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.collectarticle_listview);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_collectarticle;
    }

    @Override
    public void setCollectMessage(String title, String url) {
        Intent intent = new Intent(getActivity(), StrolleatWebActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        startActivity(intent);
    }

    class RefreshBroadCast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            dbTool.queryAllWebCollect(new DBTool.OnQueryCollectListener() {
                @Override
                public void onQuery(ArrayList<WebCollectBean> webCollectBeen) {
                    articleAdapter.setArrayList(webCollectBeen);
                    listView.setAdapter(articleAdapter);
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(refreshBroadCast);
    }
}
