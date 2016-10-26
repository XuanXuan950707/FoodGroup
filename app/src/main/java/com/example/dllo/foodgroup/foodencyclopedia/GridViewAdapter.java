package com.example.dllo.foodgroup.foodencyclopedia;

import android.content.Context;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/10/25.
 */
public class GridViewAdapter extends SimpleAdapter {
    public GridViewAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }
}
