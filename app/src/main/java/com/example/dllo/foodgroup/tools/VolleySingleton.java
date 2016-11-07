package com.example.dllo.foodgroup.tools;

import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.dllo.foodgroup.R;

/**
 * Created by dllo on 16/10/24.
 */
public class VolleySingleton {
    private static VolleySingleton volleySingleton;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;// 用来请求图片的

    private VolleySingleton(){

        mRequestQueue = Volley.newRequestQueue(MyApp.getsContext());
        // 初始化ImageLoader
        mImageLoader = new ImageLoader(mRequestQueue,new MemoryCache());
    }
    public static VolleySingleton getInstance(){
        if (volleySingleton == null){
            synchronized (VolleySingleton.class){
                if (volleySingleton == null){
                    volleySingleton = new VolleySingleton();
                }
            }
        }
        return volleySingleton;
    }
    public void getImage(String url, ImageView imageView){
        mImageLoader.get(url,ImageLoader.getImageListener(
                imageView, R.mipmap.img_default_compared_food,R.mipmap.img_default_compared_food
        ));
    }



    // 获得
    public RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }

    public <T> void addRequest(Request<T> request){
        mRequestQueue.add(request);
    }
}
