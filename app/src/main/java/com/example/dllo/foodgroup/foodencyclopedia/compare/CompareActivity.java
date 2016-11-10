package com.example.dllo.foodgroup.foodencyclopedia.compare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;
import com.example.dllo.foodgroup.foodencyclopedia.foodmore.FoodMoreItemBean;
import com.example.dllo.foodgroup.foodencyclopedia.search.SearchActivity;
import com.example.dllo.foodgroup.foodencyclopedia.search.SearchFoodBean;
import com.example.dllo.foodgroup.tools.GsonRequest;
import com.example.dllo.foodgroup.tools.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/9.
 */
public class CompareActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton back;
    private ImageView leftMessage;
    private ImageView leftClear;
    private ImageView rightMessage;
    private ImageView rightClear;
    private String group;
    private CompareBean leftBean;
    private CompareBean rightBean;
    private TextView lefttitle;
    private TextView righttitle;
    private ArrayList<CompareItemBean> arrayList = new ArrayList<>();
    private ArrayList<CompareItemBean> leftArrayList = new ArrayList<>();
    private ArrayList<CompareItemBean> rightArrayList = new ArrayList<>();
    private ListView listView;
    private CompareAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_compare;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.compare_listview);
        lefttitle = bindView(R.id.compare_lefttitle);
        righttitle = bindView(R.id.compare_righttitle);
        back = bindView(R.id.compare_return);
        leftMessage = bindView(R.id.compare_leftMessage);
        rightMessage = bindView(R.id.compare_rightMessage);
        leftClear = bindView(R.id.compare_leftclear);
        rightClear = bindView(R.id.compare_rightclear);
        setClick(this,back,leftMessage,rightMessage);
    }

    @Override
    protected void iniData() {
//        Intent intent = getIntent();
//        Log.d("CompareActivity", intent.getStringExtra("address"));
        adapter = new CompareAdapter(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.compare_return:
                onBackPressed();
                break;
            case R.id.compare_leftMessage:
                group = "left";
                addMessage();
                break;
            case R.id.compare_rightMessage:
                group = "right";
                addMessage();
                break;
        }
    }
    public static final int REQUSEST_CODE = 101;
    public void addMessage(){
        Intent intent = new Intent(CompareActivity.this, SearchActivity.class);
        intent.putExtra("address","1");
        startActivityForResult(intent,REQUSEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUSEST_CODE == requestCode && SearchActivity.RESULT_CODE == resultCode) {
            Log.d("CompareActivity", data.getStringExtra("code"));
            String code = data.getStringExtra("code");
            if (group.equals("left")){
                getLeftGsonRequest("http://food.boohee.com/fb/v1/foods/"+code+"/brief?app_device=Android&app_version=2.6&channel=baidu&user_key=90026eec-a1ef-44ff-87bb-e196d7b2848f&token=WDQy4wnxCkVbEy2zG4cB&phone_model=ZTE+N909&os_version=4.1.2%20HTTP/1.1");
            }else if(group.equals("right")){
                getRightGsonRequest("http://food.boohee.com/fb/v1/foods/"+code+"/brief?app_device=Android&app_version=2.6&channel=baidu&user_key=90026eec-a1ef-44ff-87bb-e196d7b2848f&token=WDQy4wnxCkVbEy2zG4cB&phone_model=ZTE+N909&os_version=4.1.2%20HTTP/1.1");
            }
        }
    }
    protected void getLeftGsonRequest(String url) {
        arrayList.clear();
        leftArrayList.clear();
        GsonRequest<CompareBean> gsonRequest = new GsonRequest<>(
                CompareBean.class, url, new Response.Listener<CompareBean>() {
            @Override
            public void onResponse(CompareBean response) {
//                leftBean = response;
                Log.d("CompareActivity", response.getThumb_image_url());
                VolleySingleton.getInstance().getImage
                        (response.getThumb_image_url(),leftMessage);
                lefttitle.setText(response.getName());
                for (int i = 0; i < response.getNutrition().size(); i++) {
                    CompareItemBean bean = new CompareItemBean();
                    bean.setLeft(response.getNutrition().get(i).getValue()
                            +response.getNutrition().get(i).getUnit());
                    bean.setCenter(response.getNutrition().get(i).getName());
                    leftArrayList.add(bean);
                }
                finalArraylist();
                adapter.setArrayList(arrayList);
                listView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
    protected void getRightGsonRequest(String url) {
        arrayList.clear();
        rightArrayList.clear();
        GsonRequest<CompareBean> gsonRequest = new GsonRequest<>(
                CompareBean.class, url, new Response.Listener<CompareBean>() {
            @Override
            public void onResponse(CompareBean response) {
//                leftBean = response;
                Log.d("CompareActivity", response.getThumb_image_url());
                VolleySingleton.getInstance().getImage
                        (response.getThumb_image_url(),rightMessage);
                righttitle.setText(response.getName());
                for (int i = 0; i < response.getNutrition().size(); i++) {
                    CompareItemBean bean = new CompareItemBean();
                    bean.setRight(response.getNutrition().get(i).getValue()
                            +response.getNutrition().get(i).getUnit());
                    bean.setCenter(response.getNutrition().get(i).getName());
                    rightArrayList.add(bean);
                }
                finalArraylist();
                adapter.setArrayList(arrayList);
                listView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
    public void finalArraylist(){
        ArrayList<CompareItemBean> leftTestArraylist = leftArrayList;
        ArrayList<CompareItemBean> rightTestArraylist = rightArrayList;
        Log.d("CompareActivity", "leftArrayList.size():" + leftArrayList.size());
        Log.d("CompareActivity", "rightArrayList.size():" + rightArrayList.size());
        if (leftTestArraylist.size() >= rightTestArraylist.size()){
            arrayList.addAll(leftTestArraylist);
            for (int i = 0;i < leftArrayList.size();i++){
                for (int j = 0 ;j < rightArrayList.size();j++){
                    if (rightTestArraylist.get(j).getCenter() == leftTestArraylist.get(i).getCenter()){
                        arrayList.get(i).setRight(rightTestArraylist.get(j).getRight());
                        Log.d("CompareActivity", rightTestArraylist.get(j).getRight());
                    }
                }
            }
        }else if(leftArrayList.size() < rightArrayList.size()){
            arrayList.addAll(rightTestArraylist);
            for (int i = 0;i < rightTestArraylist.size();i++){
                for (int j = 0 ;j < leftTestArraylist.size();j++){
                    if (leftTestArraylist.get(j).getCenter() == rightTestArraylist.get(i).getCenter()){
                        arrayList.get(i).setLeft(leftTestArraylist.get(j).getLeft());
                    }
                }
            }
        }
    }
}
