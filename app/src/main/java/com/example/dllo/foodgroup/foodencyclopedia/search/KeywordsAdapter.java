package com.example.dllo.foodgroup.foodencyclopedia.search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseViewHolder;
import com.example.dllo.foodgroup.tools.SearchListener;

/**
 * Created by dllo on 16/11/11.
 */
public class KeywordsAdapter extends BaseAdapter {
    private KeywordsBean bean;
    private Context context;
    private SearchListener searchListener;

    public void setSearchListener(SearchListener searchListener) {
        this.searchListener = searchListener;
    }

    public KeywordsAdapter(Context context) {
        this.context = context;
    }

    public void setBean(KeywordsBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getKeywords().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getKeywords().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        BaseViewHolder viewHolder = BaseViewHolder.getViewHolder
                (view,viewGroup, R.layout.searchfood_keywords);
        viewHolder.setText(R.id.searchfood_keywords_tv,bean.getKeywords().get(i));
        viewHolder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchListener.setKeywords(bean.getKeywords().get(i));
            }
        });
        return viewHolder.getItemView();
    }
}
