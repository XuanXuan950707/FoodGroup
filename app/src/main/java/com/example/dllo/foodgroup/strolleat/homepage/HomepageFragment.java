package com.example.dllo.foodgroup.strolleat.homepage;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseFragment;
import com.example.dllo.foodgroup.tools.GsonRequest;
import com.example.dllo.foodgroup.tools.TestBean;
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

    @Override
    protected void initData() {
        arrayList = new ArrayList<>();
        adapter = new HomepageAdapter(getContext());
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
        rc.setAdapter(adapter);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager
                (2,StaggeredGridLayoutManager.VERTICAL);
        rc.setLayoutManager(manager);




    }

    @Override
    protected void initView() {

        rc = bindView(R.id.homepage_rc);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_homepage;
    }
}
