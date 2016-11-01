package com.example.dllo.foodgroup.foodencyclopedia;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.foodgroup.Bean.GridBean;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/25.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<GridBean> arrayList;

    public GridViewAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<GridBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList == null? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseViewHolder viewHolder =
                BaseViewHolder.getViewHolder
                        (view,viewGroup,R.layout.foodencylopedia_item);
        viewHolder.setText(R.id.foodencylope_item_tv,arrayList.get(i).getTitle());
        viewHolder.setImage(R.id.foodencylope_item_iv,arrayList.get(i).getImage());
        viewHolder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return viewHolder.getItemView();
    }

//    private class MyViewHolder {
//
//    private final ImageView iv;
//    private final TextView tv;
//
//    public MyViewHolder(View view) {
//        iv = (ImageView) view.findViewById(R.id.foodencylope_item_iv);
//        tv = (TextView) view.findViewById(R.id.foodencylope_item_tv);
//    }

}
