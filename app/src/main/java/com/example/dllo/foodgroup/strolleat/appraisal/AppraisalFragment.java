package com.example.dllo.foodgroup.strolleat.appraisal;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodgroup.Bean.AppraisalBean;
import com.example.dllo.foodgroup.Bean.AppraisalItemBean;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseFragment;
import com.example.dllo.foodgroup.main.StrolleatWebActivity;
import com.example.dllo.foodgroup.tools.Data;
import com.example.dllo.foodgroup.tools.EndLessOnScrollListener;
import com.example.dllo.foodgroup.tools.GsonRequest;
import com.example.dllo.foodgroup.tools.VolleySingleton;
import com.example.dllo.foodgroup.tools.WebActivityListener;
import com.example.dllo.foodgroup.tools.WebCollectListener;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class AppraisalFragment extends BaseFragment implements WebCollectListener{

    private RecyclerView rv;
    private SwipeRefreshLayout refreshLayout;
    private AppraisalAdapter adapter;
    private ArrayList<AppraisalItemBean> arrayList;
//    private String url = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=2&per=10";
    private LinearLayoutManager manager;
    private int page = 2;
    private Data data;

    @Override
    protected void initData() {
        data = new Data();
        arrayList = new ArrayList<>();
        adapter = new AppraisalAdapter(getContext());
        adapter.setWebCollectListener(this);
        getGsonRequest(data.APPRAISAL_HEAD+1+data.APPRAISAL_END);
        rv.setAdapter(adapter);
        manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        rv.addOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
//                refreshLayout.setRefreshing(true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                getGsonRequest(data.APPRAISAL_HEAD+page+data.APPRAISAL_END);
                page++;
//                refreshLayout.setRefreshing(false);
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.clear();
                getGsonRequest(data.APPRAISAL_HEAD+1+data.APPRAISAL_END);
                refreshLayout.setRefreshing(false);
            }
        });


    }

    @Override
    protected void initView() {

        refreshLayout = bindView(R.id.appraisal_refresh);
        rv = bindView(R.id.appraisal_rv);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_appraisal;
    }
    protected void getGsonRequest(String url){
        GsonRequest<AppraisalBean> gsonRequest =new GsonRequest<>(
                AppraisalBean.class, url, new Response.Listener<AppraisalBean>() {
            @Override
            public void onResponse(AppraisalBean response) {
                for (int i = 0; i < response.getFeeds().size(); i++) {
                    AppraisalItemBean bean = new AppraisalItemBean();
                    bean.setBackground(response.getFeeds().get(i).getBackground());
                    bean.setTitle(response.getFeeds().get(i).getTitle());
                    Log.d("response", response.getFeeds().get(i).getTitle());
                    bean.setSource(response.getFeeds().get(i).getSource());
                    bean.setTail(response.getFeeds().get(i).getTail());
                    bean.setLink(response.getFeeds().get(i).getLink());
                    arrayList.add(bean);
                }
                adapter.setArrayList(arrayList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    @Override
    public void setCollectMessage(String title, String url) {
        Intent intent = new Intent(getActivity(), StrolleatWebActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        startActivity(intent);
    }
}
