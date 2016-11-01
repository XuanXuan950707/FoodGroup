package com.example.dllo.foodgroup.strolleat.cooking;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseFragment;
import com.example.dllo.foodgroup.strolleat.StrolleatWebActivity;
import com.example.dllo.foodgroup.strolleat.knowledge.KnowledgeAdapter;
import com.example.dllo.foodgroup.Bean.KnowledgeBean;
import com.example.dllo.foodgroup.Bean.KnowledgeItemBean;
import com.example.dllo.foodgroup.tools.EndLessOnScrollListener;
import com.example.dllo.foodgroup.tools.GsonRequest;
import com.example.dllo.foodgroup.tools.VolleySingleton;
import com.example.dllo.foodgroup.tools.WebActivityListener;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class CookingFragment extends BaseFragment implements WebActivityListener{

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private ArrayList<KnowledgeItemBean> arrayList;
    private KnowledgeAdapter adapter;
    private String url="http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=4&per=10";

    private int page = 2;
    @Override
    protected void initData() {
        arrayList = new ArrayList<>();
        adapter = new KnowledgeAdapter(getContext());
        adapter.setWebActivityListener(this);
        getGsonRequest(url);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.clear();
                getGsonRequest(url);
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });
        recyclerView.addOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                getGsonRequest
                        ("http://food.boohee.com/fb/v1/feeds/category_feed?page="+page+"&category=4&per=10");
                page++;
            }
        });


    }

    @Override
    protected void initView() {

        refreshLayout = bindView(R.id.cooking_refresh);
        recyclerView = bindView(R.id.cooking_recyclerview);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_cooking;
    }
    protected void getGsonRequest(String url){
        GsonRequest<KnowledgeBean> gsonRequest =new GsonRequest<>(
                KnowledgeBean.class, url, new Response.Listener<KnowledgeBean>() {
            @Override
            public void onResponse(KnowledgeBean response) {
                for (int i = 0;i < response.getFeeds().size();i++){
                    KnowledgeItemBean bean = new KnowledgeItemBean();
                    bean.setContent_type(response.getFeeds().get(i).getContent_type());
                    bean.setSource(response.getFeeds().get(i).getSource());
                    bean.setTitle(response.getFeeds().get(i).getTitle());
                    bean.setTail(response.getFeeds().get(i).getTail());
                    bean.setImages(response.getFeeds().get(i).getImages());
                    bean.setLink(response.getFeeds().get(i).getLink());
                    arrayList.add(bean);
                }
                adapter.setArraylist(arrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    @Override
    public void setUrl(String url) {
        Intent intent = new Intent(getActivity(), StrolleatWebActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}
