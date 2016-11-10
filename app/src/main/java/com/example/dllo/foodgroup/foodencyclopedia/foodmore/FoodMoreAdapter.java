package com.example.dllo.foodgroup.foodencyclopedia.foodmore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseViewHolder;
import com.example.dllo.foodgroup.foodencyclopedia.search.SearchActivity;
import com.example.dllo.foodgroup.tools.SearchListener;
import com.example.dllo.foodgroup.tools.WebActivityListener;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/2.
 */
public class FoodMoreAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private ArrayList<FoodMoreItemBean> arrayList;
    private String address = String.valueOf(2);
    private SearchListener searchListenner;

    public void setSearchListenner(SearchListener searchListenner) {
        this.searchListenner = searchListenner;
    }

    public FoodMoreAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<FoodMoreItemBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }


    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.getViewHolder(parent, R.layout.foodmore_item);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.setCirleImage(R.id.foodmore_item_image,arrayList.get(position).getThumb_image_url());
        holder.setText(R.id.foodmore_item_name,arrayList.get(position).getName());
        holder.setText(R.id.foodmore_item_cacalory,arrayList.get(position).getCalory());
        holder.setText(R.id.foodmore_item_weight,arrayList.get(position).getWeight());
        if (arrayList.get(position).getHealth_light() == 1) {
            holder.setImage(R.id.foodmore_item_health_light,R.mipmap.ic_food_light_green);
        }else if (arrayList.get(position).getHealth_light() == 2){
            holder.setImage(R.id.foodmore_item_health_light,R.mipmap.ic_food_light_yellow);
        }else if (arrayList.get(position).getHealth_light() == 3){
            holder.setImage(R.id.foodmore_item_health_light,R.mipmap.ic_food_light_red);
        }
        if (address.equals("1")){
            holder.setTextVisible(R.id.foodmore_item_compare);
            holder.setItemClick(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    searchListenner.setCode(arrayList.get(position).getCode());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arrayList== null ? 0:arrayList.size();
    }
}
