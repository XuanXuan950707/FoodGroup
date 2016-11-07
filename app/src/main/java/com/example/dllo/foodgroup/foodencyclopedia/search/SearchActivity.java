package com.example.dllo.foodgroup.foodencyclopedia.search;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;

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

    @Override
    protected int getLayout() {
        return R.layout.activity_searchfood;
    }

    @Override
    protected void initView() {

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
                corn,egg,soybean,strawberry);
    }

    @Override
    protected void iniData() {
        arrayList = new ArrayList<>();
        adapter = new SearchAdapter(this);
        adapter.setArrayList(arrayList);
        historyview.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        historyview.setLayoutManager(manager);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchfood_return:
                onBackPressed();
                break;
            case R.id.searchfood_search:
                Search();
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
        }
    }
    public void Search(){
        if (editText.getText().toString() != null) {
            arrayList.add(0,editText.getText().toString());
            Log.d("SearchActivity", editText.getText().toString());
            adapter.setArrayList(arrayList);
        }
    }
}
