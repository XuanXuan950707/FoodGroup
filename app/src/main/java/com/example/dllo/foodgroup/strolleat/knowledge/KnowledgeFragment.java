package com.example.dllo.foodgroup.strolleat.knowledge;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodgroup.Bean.KnowledgeBean;
import com.example.dllo.foodgroup.Bean.KnowledgeItemBean;
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
public class KnowledgeFragment extends BaseFragment implements WebCollectListener{

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
//    private String url = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=3&per=10";
    private KnowledgeAdapter adapter;
    private ArrayList<KnowledgeItemBean> arraylist;
    private int page = 2;
    private Data data;
    private EndLessOnScrollListener endLessOnScrollListener;

    @Override
    protected void initData() {
        data = new Data();
        arraylist = new ArrayList<>();
        adapter = new KnowledgeAdapter(getContext());
        adapter.setWebCollectListener(this);
        getGsonRequest(data.KNOWLEDGE_HEAD+1+data.KNOWLEDGE_END);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arraylist.clear();
                getGsonRequest(data.KNOWLEDGE_HEAD+1+data.KNOWLEDGE_END);
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
                endLessOnScrollListener.resetPreviousTotal();
            }
        });
        recyclerView.addOnScrollListener(endLessOnScrollListener = new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                getGsonRequest(data.KNOWLEDGE_HEAD+page+data.KNOWLEDGE_END);
                page++;

            }
        });
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.knowledge_recyclerview);
        refreshLayout = bindView(R.id.knowledge_refresh);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_knowledge;
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
                    Log.d("KnowledgeFragment", response.getFeeds().get(i).getLink());
                    arraylist.add(bean);
                }
                adapter.setArraylist(arraylist);
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
