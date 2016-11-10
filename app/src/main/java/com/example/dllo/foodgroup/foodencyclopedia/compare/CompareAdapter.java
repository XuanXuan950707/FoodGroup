package com.example.dllo.foodgroup.foodencyclopedia.compare;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/10.
 */
public class CompareAdapter extends BaseAdapter {

    Context context;
    ArrayList<CompareItemBean> arrayList;

    public CompareAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<CompareItemBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
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
        BaseViewHolder viewHolder = BaseViewHolder.getViewHolder
                (view,viewGroup, R.layout.compare_item);
        if (arrayList.get(i).getLeft() == null) {
            viewHolder.setText(R.id.compare_item_left,"--");
        }else {

            viewHolder.setText(R.id.compare_item_left, arrayList.get(i).getLeft());
        }
        viewHolder.setText(R.id.compare_item_center,arrayList.get(i).getCenter());
        if (arrayList.get(i).getRight() == null) {
            viewHolder.setText(R.id.compare_item_right,"--");
        }else {

            viewHolder.setText(R.id.compare_item_right, arrayList.get(i).getRight());
        }
        return viewHolder.getItemView();
    }
}
