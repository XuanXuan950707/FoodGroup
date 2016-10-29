package com.example.dllo.foodgroup.strolleat.cooking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/10/29.
 */
public class CookingAdapter extends RecyclerView.Adapter<CookingAdapter.CookingViewHolder> {
    Context context;
    CookingBean bean;
    @Override
    public CookingAdapter.CookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CookingAdapter.CookingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class CookingViewHolder extends RecyclerView.ViewHolder{

        public CookingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
