package com.example.dllo.foodgroup.foodencyclopedia.search;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodgroup.Bean.FoodMoreBean;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;
import com.example.dllo.foodgroup.foodencyclopedia.foodmore.FoodMoreAdapter;
import com.example.dllo.foodgroup.foodencyclopedia.foodmore.FoodMoreItemBean;
import com.example.dllo.foodgroup.tools.EndLessOnScrollListener;
import com.example.dllo.foodgroup.tools.GsonRequest;
import com.example.dllo.foodgroup.tools.VolleySingleton;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by dllo on 16/10/27.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton returnButton;
    private ImageButton searchButton;
    private RecyclerView historyview;
    private ArrayList<String> arrayList;
    private EditText editText;
    private SearchAdapter adapter;
    private Button apple;
    private Button banana;
    private Button bun;
    private Button potato;
    private Button yoghurt;
    private Button rice;
    private Button corn;
    private Button egg;
    private Button soybean;
    private Button strawberry;
    private LinearLayout history;
    private LinearLayout remove;
    private LinearLayout option;
    private ArrayList<FoodMoreItemBean> itemBeen;
    private FoodMoreAdapter foodMoreAdapter;
    private RecyclerView foodView;
    private ImageView clear;
    private String url;
    private int page = 2;
    private LinearLayoutManager manager1;

    @Override
    protected int getLayout() {
        return R.layout.activity_searchfood;
    }

    @Override
    protected void initView() {

        clear = bindView(R.id.searchfood_clear);
        foodView = bindView(R.id.searchfood_recycler);
        option = bindView(R.id.searchfood_options);
        history = bindView(R.id.searchfood_searchHistory);
        remove = bindView(R.id.searchfood_delete);
        editText = bindView(R.id.searchfood_edit);
        returnButton = bindView(R.id.searchfood_return);
        searchButton = bindView(R.id.searchfood_search);
        historyview = bindView(R.id.search_recyclerview);
        apple = bindView(R.id.searchfood_apple);
        banana = bindView(R.id.searchfood_banana);
        bun = bindView(R.id.searchfood_bun);
        potato = bindView(R.id.searchfood_potato);
        yoghurt = bindView(R.id.searchfood_yoghurt);
        rice = bindView(R.id.searchfood_rice);
        corn = bindView(R.id.searchfood_corn);
        egg = bindView(R.id.searchfood_egg);
        soybean = bindView(R.id.searchfood_soybean);
        strawberry = bindView(R.id.searchfood_strawberry);
        setClick(this,returnButton,searchButton, apple, banana, bun, potato,yoghurt,rice,
                corn,egg,soybean,strawberry,remove,clear);
    }

    @Override
    protected void iniData() {
        arrayList = new ArrayList<>();
        itemBeen = new ArrayList<>();
        if (arrayList.size() == 0){
            history.setVisibility(View.GONE);
        }
        adapter = new SearchAdapter(this);
        adapter.setArrayList(arrayList);
        historyview.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        historyview.setLayoutManager(manager);
        manager1 = new LinearLayoutManager(this);
        foodView.addOnScrollListener(new EndLessOnScrollListener(manager1) {
            @Override
            protected void onLoadMore(int curentPage) {
                getGsonRequest("http://food.boohee.com/fb/v1/search?page="+page+"&order_asc=desc&q="+toUtf8(url));
                page++;
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchfood_clear:
                clearAll();
                break;
            case R.id.searchfood_return:
                onBackPressed();
                break;
            case R.id.searchfood_search:
                search();
                break;
            case R.id.searchfood_apple:
                editText.setText("苹果");
                break;
            case R.id.searchfood_banana:
                editText.setText("香蕉");
                break;
            case R.id.searchfood_bun:
                editText.setText("馒头");
                break;
            case R.id.searchfood_corn:
                editText.setText("玉米");
                break;
            case R.id.searchfood_egg:
                editText.setText("鸡蛋");
                break;
            case R.id.searchfood_potato:
                editText.setText("红薯");
                break;
            case R.id.searchfood_rice:
                editText.setText("米饭");
                break;
            case R.id.searchfood_soybean:
                editText.setText("豆浆");
                break;
            case R.id.searchfood_strawberry:
                editText.setText("草莓");
                break;
            case R.id.searchfood_yoghurt:
                editText.setText("酸奶");
                break;
            case R.id.searchfood_delete:
                removeAll();
                break;
        }
    }
    public void search(){
        if (!TextUtils.isEmpty(editText.getText().toString())) {
            url = editText.getText().toString();
            Log.d("SearchActivity", toUtf8(url));
            for (int i = 0; i < arrayList.size(); i++) {
               if(arrayList.get(i).equals(editText.getText().toString())){
                   arrayList.remove(editText.getText().toString());
               }
            }

            arrayList.add(0,editText.getText().toString());
            if (arrayList.size() == 11){
                arrayList.remove(10);
            }
//            adapter.setArrayList(arrayList);
            itemBeen.clear();
            adapter.setArrayList(arrayList);
            option.setVisibility(View.GONE);
            foodView.setVisibility(View.VISIBLE);
            foodMoreAdapter = new FoodMoreAdapter(this);
            getGsonRequest("http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q="+toUtf8(url));
            foodView.setAdapter(foodMoreAdapter);
            foodView.setLayoutManager(manager1);
        }
    }
    public void removeAll(){
        arrayList.clear();
        history.setVisibility(View.GONE);
    }
    public void clearAll(){
        history.setVisibility(View.VISIBLE);
        option.setVisibility(View.VISIBLE);
        foodView.setVisibility(View.GONE);
        editText.setText("");
    }
    protected void getGsonRequest(String url) {
        GsonRequest<SearchFoodBean> gsonRequest = new GsonRequest<>(
                SearchFoodBean.class, url, new Response.Listener<SearchFoodBean>() {
            @Override
            public void onResponse(SearchFoodBean response) {
                for (int i = 0; i < response.getItems().size(); i++) {
                    FoodMoreItemBean bean = new FoodMoreItemBean();
                    bean.setName(response.getItems().get(i).getName());
                    Log.d("SearchActivity", response.getItems().get(i).getName());
                    bean.setThumb_image_url(response.getItems().get(i).getThumb_image_url());
                    bean.setHealth_light(response.getItems().get(i).getHealth_light());
                    bean.setWeight(response.getItems().get(i).getWeight());
                    bean.setCalory(response.getItems().get(i).getCalory());
//                    arrayList.add(bean);
                    itemBeen.add(bean);
                }
//                adapter.setArrayList(arrayList);
                foodMoreAdapter.setArrayList(itemBeen);
//                adapter.notifyDataSetChanged();
                foodMoreAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
    public static String toUtf8(String str) {
                 String result = null;
                 try {
                     result = URLEncoder.encode(str, "UTF-8");
                     } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                 return result;
             }
}
