package com.example.dllo.foodgroup.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dllo on 16/10/26.
 */
public class ImageThread extends Thread{
    private Handler handler;
    private String image;
    public ImageThread(Handler handler,String image){
        super();
        this.handler = handler;
        this.image = image;
    }
    public ImageThread(){
        super();
    }

    @Override
    public void run() {
        super.run();
        try {
            URL url = new URL(image);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                connection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
