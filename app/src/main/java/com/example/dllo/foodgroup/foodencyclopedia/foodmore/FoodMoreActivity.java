package com.example.dllo.foodgroup.foodencyclopedia.foodmore;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodgroup.Bean.FoodMoreBean;
import com.example.dllo.foodgroup.Bean.PopShowItemBean;
import com.example.dllo.foodgroup.Bean.TestBean;
import com.example.dllo.foodgroup.Bean.TypeBean;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;
import com.example.dllo.foodgroup.tools.Data;
import com.example.dllo.foodgroup.tools.EndLessOnScrollListener;
import com.example.dllo.foodgroup.tools.GsonRequest;
import com.example.dllo.foodgroup.tools.VolleySingleton;
import com.example.dllo.foodgroup.tools.WebActivityListener;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/2.
 */
public class FoodMoreActivity extends BaseActivity implements View.OnClickListener,WebActivityListener,FoodValueListener{

    private TextView titles;
    private ImageButton back;
    private String id;
    private RecyclerView recyclerView;
    private ArrayList<FoodMoreItemBean> arrayList;
    private FoodMoreAdapter adapter;
    private String kind;
    private int page = 2;
    private int subValuepage = 2;
    private TextView poptext;
    private PopupWindow popupWindow;
    private TextView type;
    private GridView grid;
    private ArrayList<PopShowItemBean> popShowItemBeen;
    private ArrayList<PopShowItemBean> popIntBean;
    private TestBean testBean;
    private String order = "1";
    private EndLessOnScrollListener endLessOnScrollListener ;
    private PopupWindow intpop;
    private boolean subValue = true;
    private String localFoodId;
    private LinearLayout down;
    private LinearLayout up;
    private String sort = "0";
    private Data data;

    @Override
    protected int getLayout() {
        return R.layout.foodmore;
    }

    @Override
    protected void initView() {
        titles = bindView(R.id.foodmore_title);
        back = bindView(R.id.foodmore_return);
        recyclerView = bindView(R.id.foodmore_recyclerview);
        poptext = bindView(R.id.foodmore_popshow);
        type = bindView(R.id.foodmore_type);
        down = bindView(R.id.foodmore_orderdown);
        up = bindView(R.id.foodmore_orderup);
        setClick(this, back, poptext, type,down,up);
    }

    @Override
    protected void iniData() {
        data = new Data();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        kind = intent.getStringExtra("kind");
        poptext.setVisibility(View.GONE);
        if (kind.equals("group")){
            poptext.setVisibility(View.VISIBLE);
            getPopShowItem();
        }
        Log.d("FoodMoreActivity", kind);
//        popArrayList = getPopShowItem();
        getTypePopShowItem();
        arrayList = new ArrayList<>();
        adapter = new FoodMoreAdapter(this);
        getGsonRequest(data.FOODMORE_KIND + kind + data.FOODMORE_VALUE + id + data.FOODMORE_ORDERBY+1+data.FOODMORE_PAGE+1+data.FOODMORE_ORDERASC+sort);
        Log.d("FoodMoreActivity", data.FOODMORE_KIND + kind + data.FOODMORE_VALUE + id + data.FOODMORE_ORDERBY + 1 + data.FOODMORE_PAGE + 1 + data.FOODMORE_ORDERASC + sort);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(endLessOnScrollListener = new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                if (subValue) {
                    getGsonRequest(data.FOODMORE_KIND + kind + data.FOODMORE_VALUE + id + data.FOODMORE_ORDERBY + order + data.FOODMORE_PAGE + page + data.FOODMORE_ORDERASC+sort);
                    page++;
                }
                else if (subValue == false){
                    getGsonRequest(data.FOODMORE_KIND + kind + data.FOODMORE_VALUE + id + data.FOODMORE_SUBVALUE + localFoodId + ")"+ data.FOODMORE_ORDERBY + order + data.FOODMORE_PAGE + subValuepage + data.FOODMORE_ORDERASC+sort);
//                    getGsonRequest("http://food.boohee.com/fb/v1/foods?kind=" + kind + "&value=" + id + "(&sub_value=" + localFoodId + ")"+ "&order_by=" + order + "&page=" + subValuepage + "&order_asc="+sort);
                    subValuepage++;
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.foodmore_return:

                onBackPressed();
                break;
            case R.id.foodmore_popshow:
                if (intpop != null && intpop.isShowing()) {
                    intpop.dismiss();
                }else {
                    intpop.showAsDropDown(poptext);
                }
//

                break;
            case R.id.foodmore_type:
                if(popupWindow != null && !popupWindow.isShowing()){
                    popupWindow.showAsDropDown(view);
                }else {
                    popupWindow.dismiss();
                }
                break;
            case R.id.foodmore_orderdown:
                orderDown();
                break;
            case R.id.foodmore_orderup:
                orderUp();
                break;
        }
    }
    private void orderDown(){
        down.setVisibility(View.GONE);
        up.setVisibility(View.VISIBLE);
        sort = "1";
        arrayList.clear();
        endLessOnScrollListener.resetPreviousTotal();
        if (subValue) {
            getGsonRequest(data.FOODMORE_KIND + kind + data.FOODMORE_VALUE + id + data.FOODMORE_ORDERBY + order + data.FOODMORE_ORDERASC+sort);
        }else if (subValue == false){
            getGsonRequest(data.FOODMORE_KIND + kind + data.FOODMORE_VALUE + id + data.FOODMORE_SUBVALUE + localFoodId + ")"+ data.FOODMORE_ORDERBY + order + data.FOODMORE_PAGE + 1 + data.FOODMORE_ORDERASC+sort);
        }
    }

    private void orderUp(){
        up.setVisibility(View.GONE);
        down.setVisibility(View.VISIBLE);
        sort = "0";
        arrayList.clear();
        endLessOnScrollListener.resetPreviousTotal();
        if (subValue) {
            getGsonRequest(data.FOODMORE_KIND + kind + data.FOODMORE_VALUE + id + data.FOODMORE_ORDERBY + order + data.FOODMORE_ORDERASC+sort);
        }else if (subValue == false){
            getGsonRequest(data.FOODMORE_KIND + kind + data.FOODMORE_VALUE + id + data.FOODMORE_SUBVALUE + localFoodId + ")"+ data.FOODMORE_ORDERBY + order + data.FOODMORE_PAGE + 1 + data.FOODMORE_ORDERASC+sort);
        }
    }

    protected void getGsonRequest(String url) {
        GsonRequest<FoodMoreBean> gsonRequest = new GsonRequest<>(
                FoodMoreBean.class, url, new Response.Listener<FoodMoreBean>() {
            @Override
            public void onResponse(FoodMoreBean response) {
                for (int i = 0; i < response.getFoods().size(); i++) {
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
    protected void getPopShowItem(){
        String url = "http://food.boohee.com/fb/v1/categories/list";
        GsonRequest<TestBean> gsonRequest =
                new GsonRequest<TestBean>(TestBean.class,
                        url, new Response.Listener<TestBean>() {
                    @Override
                    public void onResponse(TestBean response) {
                        testBean = response;
                        initPop();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    protected void getTypePopShowItem() {
//        String url = "http://food.boohee.com/fb/v1/foods/sort_types";
        popShowItemBeen = new ArrayList<>();
        GsonRequest<TypeBean> gsonRequest =
                new GsonRequest<TypeBean>(TypeBean.class,
                        data.FOODMORE_SORTTYPE, new Response.Listener<TypeBean>() {
                    @Override
                    public void onResponse(TypeBean response) {
                        for (int i = 0; i < response.getTypes().size(); i++) {
                            PopShowItemBean bean = new PopShowItemBean();
                            bean.setName(response.getTypes().get(i).getName());
                            bean.setId(response.getTypes().get(i).getIndex());
                            popShowItemBeen.add(bean);
                            initTypesPop();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        VolleySingleton.getInstance().addRequest(gsonRequest);

    }

    private void initPop(){
        popIntBean = new ArrayList<>();
        PopShowItemBean head = new PopShowItemBean();
        head.setId(id);
        head.setName("全部");
        popIntBean.add(head);
        View view = LayoutInflater.from(this).inflate
                (R.layout.popshow,null);
        intpop = new PopupWindow(view, 300,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ListView listView = (ListView) view.findViewById(R.id.popshow_listview);
        IntPopAdapter popAdapter = new IntPopAdapter(this);
        popAdapter.setFoodValueListener(this);
        for (int i = 0; i < testBean.getGroup().get(0).getCategories().get(Integer.parseInt(id)-1).getSub_categories().size(); i++){
            PopShowItemBean bean = new PopShowItemBean();
            bean.setId(""+testBean.getGroup().get(0).getCategories().get(Integer.parseInt(id)-1).getSub_categories().get(i).getId());
            bean.setName(testBean.getGroup().get(0).getCategories().get(Integer.parseInt(id)-1).getSub_categories().get(i).getName());
            popIntBean.add(bean);
        }
        popAdapter.setArrayList(popIntBean);
        listView.setAdapter(popAdapter);

    }
    private void initTypesPop() {
        View view = LayoutInflater.from(this).inflate
                (R.layout.typepop, null);
        popupWindow = new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT, 500);
        grid = (GridView) view.findViewById(R.id.typepop_gridview);
        // popupWindow.setContentView(view);
        PopShowAdapter popAdapter = new PopShowAdapter(this);
        popAdapter.setWebActivityListener(this);
        popAdapter.setArrayList(popShowItemBeen);
        grid.setAdapter(popAdapter);
//        popupWindow.showAsDropDown(type);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
        if (intpop != null && intpop.isShowing()) {
            intpop.dismiss();
        }

    }

    @Override
    public void setUrl(String url) {
        down.setVisibility(View.VISIBLE);
        up.setVisibility(View.GONE);
        sort = "0";
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
        page = 2;
        subValuepage = 2;
        order = url;
        Log.d("order", order);
        arrayList.clear();
        if (subValue) {
            getGsonRequest(data.FOODMORE_KIND + kind + data.FOODMORE_VALUE + id + data.FOODMORE_ORDERBY + order +data.FOODMORE_PAGE+1+data.FOODMORE_ORDERASC+0);
        }else if (subValue == false){
            getGsonRequest(data.FOODMORE_KIND + kind + data.FOODMORE_VALUE + id + data.FOODMORE_SUBVALUE + localFoodId + ")"+ data.FOODMORE_ORDERBY + order + data.FOODMORE_PAGE+1+data.FOODMORE_ORDERASC+0);
        }
        endLessOnScrollListener.resetPreviousTotal();
        type.setText(popShowItemBeen.get(Integer.parseInt(order)-2).getName());
    }

    @Override
    public void foodValue(String foodId, String value) {
        if (intpop != null && intpop.isShowing()){
            intpop.dismiss();
        }
        page = 2;
        subValuepage = 2;
        arrayList.clear();
        if (foodId != id) {
            localFoodId = foodId;
            subValue = false;
            getGsonRequest(data.FOODMORE_KIND + kind + data.FOODMORE_VALUE + id + data.FOODMORE_SUBVALUE + foodId + ")" + data.FOODMORE_ORDERBY + order +data.FOODMORE_PAGE+1+data.FOODMORE_ORDERASC +sort);
        }else if (foodId == id){

            subValue = true;
            getGsonRequest(data.FOODMORE_KIND + kind + data.FOODMORE_VALUE + id + data.FOODMORE_ORDERBY+1+data.FOODMORE_PAGE+1+data.FOODMORE_ORDERASC+sort);
        }
        endLessOnScrollListener.resetPreviousTotal();
        poptext.setText(value);
    }
}
