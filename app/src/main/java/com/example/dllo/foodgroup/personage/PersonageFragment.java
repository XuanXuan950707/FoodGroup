package com.example.dllo.foodgroup.personage;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseFragment;

/**
 * Created by dllo on 16/10/24.
 */
public class PersonageFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout myCollect;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        myCollect = bindView(R.id.personage_mycollect);
        setClick(this,myCollect);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_personage;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.personage_mycollect:
                Intent intent = new Intent(getActivity(),MyCollect.class);
                startActivity(intent);
                break;
        }
    }
}
