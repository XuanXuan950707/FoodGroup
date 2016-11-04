package com.example.dllo.foodgroup.foodmore;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.dllo.foodgroup.Bean.PopShowItemBean;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseViewHolder;
import com.example.dllo.foodgroup.tools.WebActivityListener;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/3.
 */
public class PopShowAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<PopShowItemBean> arrayList;
    WebActivityListener webActivityListener;

    public void setWebActivityListener(WebActivityListener webActivityListener) {
        this.webActivityListener = webActivityListener;
    }

    public PopShowAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<PopShowItemBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList == null? 0:arrayList.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        BaseViewHolder viewHolder = BaseViewHolder.getViewHolder
                (view,viewGroup, R.layout.popshow_item);
        viewHolder.setText(R.id.popshow_item_tv,arrayList.get(i).getName());
        Log.d("PopShowAdapter", arrayList.get(i).getName());
        viewHolder.setViewClick(R.id.popshow_item_tv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webActivityListener.setUrl(arrayList.get(i).getId());
                Toast.makeText(context, arrayList.get(i).getId(), Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder.getItemView();
    }
}
