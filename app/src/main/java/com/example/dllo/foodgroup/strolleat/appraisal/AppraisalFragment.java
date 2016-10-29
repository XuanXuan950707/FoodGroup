package com.example.dllo.foodgroup.strolleat.appraisal;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseFragment;
import com.example.dllo.foodgroup.tools.EndlessOnScrollListener;
import com.example.dllo.foodgroup.tools.GsonRequest;
import com.example.dllo.foodgroup.tools.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class AppraisalFragment extends BaseFragment {

    private RecyclerView rv;
    private SwipeRefreshLayout refreshLayout;
    private AppraisalAdapter adapter;
    private ArrayList<AppraisalItemBean> arrayList;
    private String url = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=2&per=10";
    private LinearLayoutManager manager;
    private int page = 2;

    @Override
    protected void initData() {
        arrayList = new ArrayList<>();
        adapter = new AppraisalAdapter(getContext());
        getGsonRequest(url);
        rv.setAdapter(adapter);
        manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        rv.addOnScrollListener(new EndlessOnScrollListener(manager) {
            @Override
            public void onLoadMore() {
                refreshLayout.setRefreshing(true);
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
                getGsonRequest("http://food.boohee.com/fb/v1/feeds/category_feed?page="+page+"&category=2&per=10");
                page++;
                refreshLayout.setRefreshing(false);
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.clear();
                getGsonRequest(url);
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
}
