package com.example.dllo.foodgroup.foodencyclopedia.search;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
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
import com.example.dllo.foodgroup.foodencyclopedia.compare.CompareActivity;
import com.example.dllo.foodgroup.foodencyclopedia.foodmore.FoodMoreAdapter;
import com.example.dllo.foodgroup.foodencyclopedia.foodmore.FoodMoreItemBean;
import com.example.dllo.foodgroup.letorm.DBTool;
import com.example.dllo.foodgroup.letorm.SearchHistory;
import com.example.dllo.foodgroup.tools.EndLessOnScrollListener;
import com.example.dllo.foodgroup.tools.GsonRequest;
import com.example.dllo.foodgroup.tools.SearchListener;
import com.example.dllo.foodgroup.tools.VolleySingleton;
import com.example.dllo.foodgroup.tools.WebActivityListener;
import com.litesuits.orm.LiteOrm;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dllo on 16/10/27.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener,SearchListener{

    private ImageButton returnButton;
    private ImageButton searchButton;
    private RecyclerView historyview;
    private ArrayList<String> arrayList;
    private EditText editText;
    private SearchAdapter adapter;
    private LinearLayout history;
    private LinearLayout remove;
    private LinearLayout option;
    private ArrayList<FoodMoreItemBean> itemBeen;
    private FoodMoreAdapter foodMoreAdapter;
    private RecyclerView foodView;
    private ImageView clear;
    private String url;
    private int page = 2;
    private LinearLayoutManager foodmanager;
    private EndLessOnScrollListener endLessOnScrollListener;
    private String address;
    private GridView gridView;
    private KeywordsAdapter keywordsAdapter;
    private String keywords ="http://food.boohee.com/fb/v1/keywords?app_device=Android&app_version=2.6&channel=baidu&user_key=90026eec-a1ef-44ff-87bb-e196d7b2848f&token=WDQy4wnxCkVbEy2zG4cB&phone_model=ZTE+N909&os_version=4.1.2";
    private LiteOrm liteOrm;
    private DBTool dbTool;


    @Override
    protected int getLayout() {
        return R.layout.activity_searchfood;
    }

    @Override
    protected void initView() {

        gridView = bindView(R.id.searchfood_gridView);
        historyview = bindView(R.id.search_recyclerview);
        editText = bindView(R.id.searchfood_edit);
        clear = bindView(R.id.searchfood_clear);
        foodView = bindView(R.id.searchfood_recycler);
        returnButton = bindView(R.id.searchfood_return);
        searchButton = bindView(R.id.searchfood_search);
        remove = bindView(R.id.searchfood_delete);
        history = bindView(R.id.searchfood_searchHistory);
        option = bindView(R.id.searchfood_options);
        setClick(this, returnButton, searchButton, remove, clear);
    }

    @Override
    protected void iniData() {
        Intent intent = getIntent();
        address = intent.getStringExtra("address");
//        Log.d("SearchActivity", intent.getStringExtra("address"));
        arrayList = new ArrayList<>();
        itemBeen = new ArrayList<>();
        dbTool = DBTool.getInstance();
        dbTool.queryAllHistory(new DBTool.OnQueryListener() {
            @Override
            public void onQuery(ArrayList<SearchHistory> histories) {
                if (histories.size() != 0){
                    for (int i = 0 ; i < histories.size() ; i++){
                        arrayList.add(histories.get(i).getSearchName());
                    }
                }

                if (arrayList.size() == 0) {
                    history.setVisibility(View.GONE);
                }else {
                    history.setVisibility(View.VISIBLE);
                    adapter.setArrayList(arrayList);
                }
            }
        });

        keywordsAdapter = new KeywordsAdapter(this);
        keywordsAdapter.setSearchListener(this);
        getKeywords(keywords);
        gridView.setAdapter(keywordsAdapter);
        adapter = new SearchAdapter(this);
        adapter.setSearchListener(this);
        adapter.setArrayList(arrayList);
        historyview.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        historyview.setLayoutManager(manager);
        foodmanager = new LinearLayoutManager(this);
        foodView.addOnScrollListener(endLessOnScrollListener = new EndLessOnScrollListener(foodmanager) {
            @Override
            protected void onLoadMore(int curentPage) {
                getGsonRequest("http://food.boohee.com/fb/v1/search?page=" + page + "&order_asc=desc&q=" + toUtf8(url));
                page++;
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchfood_clear:
                clearAll();
                break;
            case R.id.searchfood_return:
                onBackPressed();
                break;
            case R.id.searchfood_search:
                search();
                break;
            case R.id.searchfood_delete:
                removeAll();
                break;
        }
    }

    public void search() {
        if (!TextUtils.isEmpty(editText.getText().toString())) {
            page = 2;
            endLessOnScrollListener.resetPreviousTotal();
            url = editText.getText().toString();
            Log.d("SearchActivity", toUtf8(url));

            if (arrayList.contains(editText.getText().toString())) {
                int index = arrayList.indexOf(editText.getText().toString());
                Collections.swap(arrayList, 0, index);
            }
//            for (int i = 0; i < arrayList.size(); i++) {
//                if (arrayList.get(i).equals(editText.getText().toString())) {
//                    arrayList.remove(editText.getText().toString());
//                }
//            }

            else {
                arrayList.add(0, editText.getText().toString());
            }
            if (arrayList.size() == 11) {
                arrayList.remove(10);
            }
//            adapter.setArrayList(arrayList);
            itemBeen.clear();
            adapter.setArrayList(arrayList);

            foodView.setVisibility(View.VISIBLE);
            foodMoreAdapter = new FoodMoreAdapter(this);
            foodMoreAdapter.setSearchListenner(this);
            foodMoreAdapter.setAddress(address);
            getGsonRequest("http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q=" + toUtf8(url));
            foodView.setLayoutManager(foodmanager);
            foodView.setAdapter(foodMoreAdapter);
            option.setVisibility(View.GONE);
            ArrayList<SearchHistory> searchHistories = new ArrayList<>();
            dbTool.deleteAllHistory();
            for (int i = 0; i < arrayList.size(); i++) {
                SearchHistory searchHistory = new SearchHistory();
//            searchHistory.setId(i);
                searchHistory.setSearchName(arrayList.get(i));
                searchHistories.add(searchHistory);
//            searchHistories.get(i).setId(i);
//            searchHistories.get(i).setSearchName(arrayList.get(i));
            }
            dbTool.insertSearchHistory(searchHistories);
        }
    }

    public void removeAll() {
        arrayList.clear();
        history.setVisibility(View.GONE);
    }

    public void clearAll() {
        history.setVisibility(View.VISIBLE);
        option.setVisibility(View.VISIBLE);
        foodView.setVisibility(View.GONE);
        editText.setText("");
    }

    protected void getKeywords(String url){
        GsonRequest<KeywordsBean> gsonRequest = new GsonRequest<>(
                KeywordsBean.class, url, new Response.Listener<KeywordsBean>() {
            @Override
            public void onResponse(KeywordsBean response) {
                Log.d("SearchActivity", response.getKeywords().get(1));
                keywordsAdapter.setBean(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
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
                    bean.setCode(response.getItems().get(i).getCode());
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

    public static final int RESULT_CODE = 100;

    @Override
    public void setCode(String code) {
        Intent intent = new Intent(SearchActivity.this, CompareActivity.class);
        intent.putExtra("code",code);
        setResult(RESULT_CODE,intent);
        finish();
    }

    @Override
    public void setKeywords(String keyword) {
        editText.setText(keyword);
        search();
    }

    @Override
    public void setHistoryMessage(String historyMessage) {
        editText.setText(historyMessage);
        search();
    }

    @Override
    public void finish() {

        super.finish();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }


}
