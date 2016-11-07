package com.example.dllo.foodgroup.foodencyclopedia.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/7.
 */
public class SearchAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private ArrayList<String> arrayList;
    private Context context;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.getViewHolder(parent, R.layout.searchfood_history);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setText(R.id.history_tv,arrayList.get(position));
        Log.d("SearchAdapter", arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList == null? 0: arrayList.size();
    }
}
