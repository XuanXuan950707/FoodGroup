package com.example.dllo.foodgroup.foodencyclopedia;

import android.view.View;
import android.widget.ImageButton;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;

/**
 * Created by dllo on 16/10/27.
 */
public class SearchActivity extends BaseActivity {

    private ImageButton returnButton;
    private ImageButton searchButton;

    @Override
    protected int getLayout() {
        return R.layout.activity_searchfood;
    }

    @Override
    protected void initView() {

        returnButton = bindView(R.id.searchfood_return);
        searchButton = bindView(R.id.searchfood_search);
    }

    @Override
    protected void iniData() {
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
//        overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
