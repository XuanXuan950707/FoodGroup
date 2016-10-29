package com.example.dllo.foodgroup.strolleat.homepage;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
public class HomepageFragment extends BaseFragment {


    private RecyclerView rc;

    private String url = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=10";
    private ArrayList<HomepageItemBean> arrayList;
    private HomepageAdapter adapter;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void initData() {
        arrayList = new ArrayList<>();
        adapter = new HomepageAdapter(getContext());
        getGsonRequest(url);
        rc.setAdapter(adapter);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager
                (2,StaggeredGridLayoutManager.VERTICAL);
        rc.setLayoutManager(manager);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.clear();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                getGsonRequest(url);
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });
//        rc.addOnScrollListener(new EndlessOnScrollListener(manager) {
//            @Override
//            public void onLoadMore(int currentPage) {
//                getGsonRequest("http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=20");
//            }
//        });


    }

    protected void getGsonRequest(String url){
        GsonRequest<HomepageBean> gsonRequest =new GsonRequest<>(
                HomepageBean.class, url, new Response.Listener<HomepageBean>() {
            @Override
            public void onResponse(HomepageBean response) {
                for (int i = 0; i < response.getFeeds().size(); i++) {
                    HomepageItemBean bean = new HomepageItemBean();
                    bean.setCardImage(response.getFeeds().get(i).getCard_image());
                    bean.setTitle(response.getFeeds().get(i).getTitle());
                    Log.d("response", response.getFeeds().get(i).getTitle());
                    bean.setPublisher(response.getFeeds().get(i).getPublisher());
                    bean.setPublisherImage(response.getFeeds().get(i).getPublisher_avatar());
                    bean.setLikeCt(response.getFeeds().get(i).getLike_ct()+"");
                    bean.setDescription(response.getFeeds().get(i).getDescription());
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
    protected void initView() {

        refreshLayout = bindView(R.id.homepage_srl);
        rc = bindView(R.id.homepage_rc);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_homepage;
    }
}
