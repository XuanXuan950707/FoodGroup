package com.example.dllo.foodgroup.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/2.
 */
public class FoodMoreAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private ArrayList<FoodMoreItemBean> arrayList;

    public FoodMoreAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<FoodMoreItemBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.getViewHolder(parent, R.layout.foodmore_item);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setImage(R.id.foodmore_item_image,arrayList.get(position).getThumb_image_url());
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
    }

    @Override
    public int getItemCount() {
        return arrayList== null ? 0:arrayList.size();
    }
}
