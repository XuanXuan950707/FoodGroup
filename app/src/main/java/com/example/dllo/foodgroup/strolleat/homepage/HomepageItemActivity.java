package com.example.dllo.foodgroup.strolleat.homepage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseActivity;
import com.example.dllo.foodgroup.tools.CirleDrawable;
import com.example.dllo.foodgroup.tools.ImageThread;
import com.example.dllo.foodgroup.tools.VolleySingleton;

/**
 * Created by dllo on 16/11/5.
 */
public class HomepageItemActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private ImageView imageView;
    private TextView name;
    private ImageView publisher;
    private TextView like;

    @Override
    protected int getLayout() {
        return R.layout.activity_homepage_item;
    }

    @Override
    protected void initView() {
        back = bindView(R.id.homepage_pic_back);
        imageView = bindView(R.id.homepage_pic_image);
        name = bindView(R.id.homepage_pic_name);
        publisher = bindView(R.id.homepage_pic_publisher);
        like = bindView(R.id.homepage_pic_like);
        setClick(this,back);
    }

    @Override
    protected void iniData() {
        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        like.setText(intent.getStringExtra("like"));
        VolleySingleton.getInstance().getImage
                (intent.getStringExtra("cardImage"),publisher);
//        VolleySingleton.getInstance().getImage
//                (intent.getStringExtra("publichImage"),imageView);
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (102 == message.what){
                    Bitmap bitmap = (Bitmap) message.obj;
                    CirleDrawable cirleDrawable
                            = new CirleDrawable(bitmap);
                    imageView.setImageDrawable(cirleDrawable);
                }
                return false;
            }
        });
        new ImageThread(handler,intent.getStringExtra("publichImage")).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.homepage_pic_back:
                onBackPressed();
                break;
        }
    }
}
