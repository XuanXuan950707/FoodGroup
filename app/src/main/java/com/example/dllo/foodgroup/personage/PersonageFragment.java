package com.example.dllo.foodgroup.personage;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseFragment;
import com.example.dllo.foodgroup.main.LoginActivity;
import com.example.dllo.foodgroup.main.PersonageMessageActivity;

/**
 * Created by dllo on 16/10/24.
 */
public class PersonageFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout myCollect;
    private TextView login;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        myCollect = bindView(R.id.personage_mycollect);
        login = bindView(R.id.personage_login);
        setClick(this,myCollect,login);
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
            case R.id.personage_login:
                Intent intent1 = new Intent(getContext(), LoginActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
