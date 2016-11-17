package com.example.dllo.foodgroup.main;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;
import com.example.dllo.foodgroup.tools.VolleySingleton;
import com.example.dllo.foodgroup.tools.WheelView;

import java.util.Arrays;

/**
 * Created by dllo on 16/11/14.
 */
public class PersonageMessageActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout setSex;
    private PopupWindow initPop;
    private Button sexNo;
    private Button sexYes;
    private static final String TAG = PersonageMessageActivity.class.getSimpleName();
    private static final String[] SEX_PLANETS = new String[] { "男","女" };
    private static final String[] AGE_PLANETS = new String[100];
    private static final String[] HEIGHT_PLANETS = new String[90];
    private static final String[] LEFT_WEIGHT_PLANETS = new String[120];
    private static final String[] RIGHT_WEIGHT_PLANETS = new String[10];
    private TextView setSexMessage;
    private String sex = "";
    private String age = "";
    private String height = "";
    private TextView setAgeMessage;
    private RelativeLayout setAge;
    private PopupWindow initAgePop;
    private PopupWindow initHeightPop;
    private PopupWindow initWeightPop;
    private RelativeLayout setHeight;
    private RelativeLayout setWeight;
    private TextView setHeightMessage;
    private TextView setWeightMessage;
    private String leftweight = "";
    private String rightweight = "";
    private ImageView back;
    private ImageView icon;
    private TextView name;

    @Override
    protected int getLayout() {
        return R.layout.activity_personagemessage;
    }

    @Override
    protected void initView() {
        icon = bindView(R.id.personagemessage_icon);
        name = bindView(R.id.personagemessage_name);
        setSex = bindView(R.id.personagemessage_sex);
        setAge = bindView(R.id.personagemessage_age);
        setHeight = bindView(R.id.personagemessage_height);
        setWeight = bindView(R.id.personagemessage_weight);
        setSexMessage = bindView(R.id.personagemessage_sextext);
        setAgeMessage = bindView(R.id.personagemessage_agetext);
        setHeightMessage = bindView(R.id.personagemessage_heighttext);
        setWeightMessage = bindView(R.id.personagemessage_weighttext);
        back = bindView(R.id.personagemessage_return);
        setClick(this,setSex,setAge,setHeight,setWeight,back);
    }

    @Override
    protected void iniData() {
        Intent intent = getIntent();
        for (int i = 0; i < AGE_PLANETS.length; i++) {
            AGE_PLANETS[i] = i+"";
        }
        for (int i = 0; i < HEIGHT_PLANETS.length; i++) {
            HEIGHT_PLANETS[i] = 140 + i +"cm";
        }
        for (int i = 0; i < LEFT_WEIGHT_PLANETS.length; i++) {
            LEFT_WEIGHT_PLANETS[i] = 30 + i + "";
        }
        for (int i = 0; i < RIGHT_WEIGHT_PLANETS.length; i++) {
            RIGHT_WEIGHT_PLANETS[i] = "."+i+"kg";
        }
        sexPop();
        agePop();
        heightPop();
        weightPop();
        name.setText(intent.getStringExtra("name"));
        VolleySingleton.getInstance().getCircleImg(intent.getStringExtra("icon"), icon);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.personagemessage_sex:
//                if (intpop != null && intpop.isShowing()) {
//                    intpop.dismiss();
//                }else {
//                    intpop.showAsDropDown(poptext);
//                }
                initPop.showAtLocation(PersonageMessageActivity.this.findViewById(R.id.personagemes)
                , Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.personagemessage_age:
                initAgePop.showAtLocation(PersonageMessageActivity.this.findViewById(R.id.personagemes)
                        , Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.personagemessage_height:
                initHeightPop.showAtLocation(PersonageMessageActivity.this.findViewById(R.id.personagemes)
                        , Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.personagemessage_weight:
                initWeightPop.showAtLocation(PersonageMessageActivity.this.findViewById(R.id.personagemes)
                        , Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.personagemessage_return:
                onBackPressed();
                break;
        }
    }

    private void sexPop(){
//        View view = LayoutInflater.from(this).inflate
//                (R.layout.popshow,null);
//        intpop = new PopupWindow(view, 300,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(this).inflate
                (R.layout.personage_pop,null);
        initPop = new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        WheelView wv = (WheelView) view.findViewById(R.id.pop_wheelview);
        // wv.setOffset(0);// 偏移量
        wv.setOffset(2);
        wv.setItems(Arrays.asList(SEX_PLANETS));// 实际内容
        wv.setSeletion(0);// 设置默认被选中的项目
        // wv.setSeletion(3);
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                // 选中后的处理事件
                Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex
                        + ", item: " + item);
                sex = item;
            }
        });
        sexNo = (Button) view.findViewById(R.id.pop_no);
        sexYes = (Button) view.findViewById(R.id.pop_yes);
        sexNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPop.dismiss();
            }
        });
        sexYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSexMessage.setText(sex);
                initPop.dismiss();
            }
        });
    }
    private void agePop(){
        View view = LayoutInflater.from(this).inflate
                (R.layout.personage_agepop,null);
        initAgePop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
               ViewGroup.LayoutParams.WRAP_CONTENT);
        WheelView wv = (WheelView) view.findViewById(R.id.agepop_wheelview);
        // wv.setOffset(0);// 偏移量
        wv.setOffset(2);
        wv.setItems(Arrays.asList(AGE_PLANETS));// 实际内容
        wv.setSeletion(0);// 设置默认被选中的项目
        // wv.setSeletion(3);
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                // 选中后的处理事件
                Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex
                        + ", item: " + item);
                age = item;
            }
        });
        Button ageNo = (Button) view.findViewById(R.id.agepop_no);
        Button ageYes = (Button) view.findViewById(R.id.agepop_yes);
        ageNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAgePop.dismiss();
            }
        });
        ageYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAgeMessage.setText(age);
                initAgePop.dismiss();
            }
        });
    }

    private void heightPop(){
        View view = LayoutInflater.from(this).inflate
                (R.layout.personage_heightpop,null);
        initHeightPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        WheelView wv = (WheelView) view.findViewById(R.id.heightpop_wheelview);
        // wv.setOffset(0);// 偏移量
        wv.setOffset(2);
        wv.setItems(Arrays.asList(HEIGHT_PLANETS));// 实际内容
        wv.setSeletion(0);// 设置默认被选中的项目
        // wv.setSeletion(3);
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                // 选中后的处理事件
                Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex
                        + ", item: " + item);
                height = item;
            }
        });
        Button heightNo = (Button) view.findViewById(R.id.heightpop_no);
        Button heightYes = (Button) view.findViewById(R.id.heightpop_yes);
        heightNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initHeightPop.dismiss();
            }
        });
        heightYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHeightMessage.setText(height);
                initHeightPop.dismiss();
            }
        });
    }
    private void weightPop() {
        View view = LayoutInflater.from(this).inflate
                (R.layout.personage_weightpop, null);
        initWeightPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        WheelView leftwv = (WheelView) view.findViewById(R.id.weightpop_leftwheelview);
        // wv.setOffset(0);// 偏移量
        leftwv.setOffset(2);
        leftwv.setItems(Arrays.asList(LEFT_WEIGHT_PLANETS));// 实际内容
        leftwv.setSeletion(0);// 设置默认被选中的项目
        // wv.setSeletion(3);
        leftwv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                // 选中后的处理事件
                Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex
                        + ", item: " + item);
                leftweight = item;
            }
        });
        WheelView rightwv = (WheelView) view.findViewById(R.id.weightpop_rightwheelview);
        // wv.setOffset(0);// 偏移量
        rightwv.setOffset(2);
        rightwv.setItems(Arrays.asList(RIGHT_WEIGHT_PLANETS));// 实际内容
        rightwv.setSeletion(0);// 设置默认被选中的项目
        // wv.setSeletion(3);
        rightwv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                // 选中后的处理事件
                Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex
                        + ", item: " + item);
                rightweight = item;
            }
        });
        Button weightNo = (Button) view.findViewById(R.id.weightpop_no);
        Button weightYes = (Button) view.findViewById(R.id.weightpop_yes);
        weightNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initWeightPop.dismiss();
            }
        });
        weightYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWeightMessage.setText(leftweight+rightweight);
                initWeightPop.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (initPop != null && initPop.isShowing()) {
                    initPop.dismiss();
                }
        if (initAgePop != null && initAgePop.isShowing()) {
            initAgePop.dismiss();
        }
        if (initHeightPop != null && initHeightPop.isShowing()) {
            initHeightPop.dismiss();
        }
        if (initWeightPop != null && initWeightPop.isShowing()) {
            initWeightPop.dismiss();
        }
    }
}
