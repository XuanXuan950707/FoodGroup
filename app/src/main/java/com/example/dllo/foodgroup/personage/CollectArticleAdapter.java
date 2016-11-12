package com.example.dllo.foodgroup.personage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseViewHolder;
import com.example.dllo.foodgroup.letorm.WebCollectBean;
import com.example.dllo.foodgroup.tools.WebCollectListener;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/12.
 */
public class CollectArticleAdapter extends BaseAdapter {
    private ArrayList<WebCollectBean> arrayList;
    private Context context;
    private WebCollectListener webCollectListener;

    public void setWebCollectListener(WebCollectListener webCollectListener) {
        this.webCollectListener = webCollectListener;
    }

    public CollectArticleAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<WebCollectBean> arrayList) {
        this.arrayList = arrayList;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        BaseViewHolder viewHolder = BaseViewHolder.getViewHolder
                (view,viewGroup, R.layout.collectarticle_item);
        viewHolder.setText(R.id.collectarticle_item_tv,arrayList.get(i).getTitle());
        viewHolder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webCollectListener.setCollectMessage(arrayList.get(i).getTitle(),arrayList.get(i).getUrl());
            }
        });
        return viewHolder.getItemView();
    }
}
