package com.example.dllo.foodgroup.foodencyclopedia;

import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.tools.GsonRequest;
import com.example.dllo.foodgroup.tools.TestBean;
import com.example.dllo.foodgroup.tools.VolleySingleton;
import com.example.dllo.foodgroup.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/10/24.
 */
public class FoodEncyclopediaFragment extends BaseFragment {

    private ListView lv;
    private LinearLayout headViewLayout;
    private String url = "http://food.boohee.com/fb/v1/categories/list";
    private GridView classGridView;
    private ArrayList<GridBean> classList;
    private GridViewAdapter classAdapter;
    private GridViewAdapter brandAdapter;
    private GridView brandGridView;
    private ArrayList<GridBean> brandList;
    private GridView restaurantGridView;
    private ArrayList<GridBean> restaurantList;
    private GridViewAdapter restaurantAdapter;

    @Override
    protected void initData() {

        brandList = new ArrayList<>();
        classList = new ArrayList<>();
        restaurantList = new ArrayList<>();
        classAdapter = new GridViewAdapter(getContext());
        brandAdapter = new GridViewAdapter(getContext());
        restaurantAdapter = new GridViewAdapter(getContext());

        GsonRequest<TestBean> gsonRequest =
                new GsonRequest<TestBean>(TestBean.class,
                        url, new Response.Listener<TestBean>() {
                    @Override
                    public void onResponse(TestBean response) {

//                        List<Map<String, Object>> item = new ArrayList<>();
//                        for (int i = 0;i < response.getGroup().get(0).getCategories().size();i++) {
//                            Map<String, Object> map = new HashMap<String, Object>();
//                            map.put("name", response.getGroup().get(0).getCategories().get(i).getName());
//                            map.put("image", response.getGroup().get(0).getCategories().get(i).getImage_url());
//                            item.add(map);
//                        }

//                        GridViewAdapter adapter = new GridViewAdapter
//                        gv.setAdapter(adapter);
                        for (int i = 0;i < response.getGroup().get(0).getCategories().size();i++){
                            GridBean bean = new GridBean();
                            bean.setTitle(response.getGroup().get(0).getCategories().get(i).getName());
                            bean.setImage(response.getGroup().get(0).getCategories().get(i).getImage_url());
                            classList.add(bean);
                        }
                        classAdapter.setArrayList(classList);
                        classGridView.setAdapter(classAdapter);
                        for (int i = 0;i < response.getGroup().get(1).getCategories().size();i++){
                            GridBean bean = new GridBean();
                            bean.setTitle(response.getGroup().get(1).getCategories().get(i).getName());
                            bean.setImage(response.getGroup().get(1).getCategories().get(i).getImage_url());
                            brandList.add(bean);
                        }
                        brandAdapter.setArrayList(brandList);
                        brandGridView.setAdapter(brandAdapter);
                        for (int i = 0;i < response.getGroup().get(2).getCategories().size();i++){
                            GridBean bean = new GridBean();
                            bean.setTitle(response.getGroup().get(2).getCategories().get(i).getName());
                            bean.setImage(response.getGroup().get(2).getCategories().get(i).getImage_url());
                            restaurantList.add(bean);
                        }
                        restaurantAdapter.setArrayList(restaurantList);
                        restaurantGridView.setAdapter(restaurantAdapter);

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
        headViewLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate
                (R.layout.activity_headfoodencylopedia,null);
        classGridView = bindView(R.id.foodencylope_class);
        brandGridView = bindView(R.id.foodencylope_brand);
        restaurantGridView = bindView(R.id.foodencylope_restaurant);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_foodencylopedia;
    }
}
