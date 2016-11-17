package com.example.dllo.foodgroup.tools;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.dllo.foodgroup.R;

/**
 * Created by dllo on 16/10/24.
 */
public class VolleySingleton {
    private static VolleySingleton volleySingleton;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private VolleySingleton(){

        mRequestQueue = Volley.newRequestQueue(MyApp.getsContext());
        mImageLoader = new ImageLoader(mRequestQueue,new MemoryCache());
    }

    public static VolleySingleton getInstance(){
        if (volleySingleton == null){
            // 线程锁
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
    public void getCircleImg(String url,ImageView imageView){
        CircleImgListener circleImgListener = new CircleImgListener(imageView);
        mImageLoader.get(url,circleImgListener);
    }

    class CircleImgListener implements ImageLoader.ImageListener{
        private ImageView imageView;

        public CircleImgListener(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
            Bitmap bitmap = response.getBitmap();
            if(bitmap == null){
                imageView.setImageResource(R.mipmap.ic_launcher);
            }else {
                CirleDrawable circleDrawable = new CirleDrawable(bitmap);
                imageView.setImageDrawable(circleDrawable);
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
    }
    public <T> void addRequest(Request<T> request){
        mRequestQueue.add(request);
    }
}
