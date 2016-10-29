package com.example.dllo.foodgroup.strolleat.cooking;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseFragment;
import com.example.dllo.foodgroup.strolleat.knowledge.KnowledgeAdapter;
import com.example.dllo.foodgroup.strolleat.knowledge.KnowledgeBean;
import com.example.dllo.foodgroup.strolleat.knowledge.KnowledgeItemBean;
import com.example.dllo.foodgroup.tools.EndlessOnScrollListener;
import com.example.dllo.foodgroup.tools.GsonRequest;
import com.example.dllo.foodgroup.tools.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class CookingFragment extends BaseFragment {

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
        recyclerView.addOnScrollListener(new EndlessOnScrollListener(manager) {
            @Override
            public void onLoadMore() {
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
}
