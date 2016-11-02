package com.example.dllo.foodgroup.main;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodgroup.Bean.FoodMoreBean;
import com.example.dllo.foodgroup.Bean.HomepageBean;
import com.example.dllo.foodgroup.Bean.HomepageItemBean;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;
import com.example.dllo.foodgroup.tools.GsonRequest;
import com.example.dllo.foodgroup.tools.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/2.
 */
public class FoodMoreActivity extends BaseActivity implements View.OnClickListener {

    private TextView titles;
    private ImageButton back;
    private String id;
    private RecyclerView recyclerView;
    private ArrayList<FoodMoreItemBean> arrayList;
    private FoodMoreAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.foodmore;
    }

    @Override
    protected void initView() {
        titles = bindView(R.id.foodmore_title);
        back = bindView(R.id.foodmore_return);
        recyclerView = bindView(R.id.foodmore_recyclerview);
        setClick(this,back);
    }

    @Override
    protected void iniData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        String kind = intent.getStringExtra("kind");
        titles.setText(title);
        arrayList = new ArrayList<>();
        adapter = new FoodMoreAdapter(this);
        getGsonRequest("http://food.boohee.com/fb/v1/foods?kind="+kind+"&value="+id+"&order_by=1&page=1&order_asc=0");
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.foodmore_return:
                onBackPressed();
                break;
        }
    }
    protected void getGsonRequest(String url){
        GsonRequest<FoodMoreBean> gsonRequest =new GsonRequest<>(
                FoodMoreBean.class, url, new Response.Listener<FoodMoreBean>() {
            @Override
            public void onResponse(FoodMoreBean response) {
                for (int i = 0 ;i < response.getFoods().size();i++){
                    FoodMoreItemBean bean = new FoodMoreItemBean();
                    bean.setName(response.getFoods().get(i).getName());
                    bean.setThumb_image_url(response.getFoods().get(i).getThumb_image_url());
                    bean.setCalory(response.getFoods().get(i).getCalory());
                    bean.setWeight(response.getFoods().get(i).getWeight());
                    bean.setHealth_light(response.getFoods().get(i).getHealth_light());
                    arrayList.add(bean);
                }
                adapter.setArrayList(arrayList);
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}
