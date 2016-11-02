package com.example.dllo.foodgroup.Bean;

import android.graphics.Bitmap;

/**
 * Created by dllo on 16/10/25.
 */
public class GridBean {
    String title;
    String image;
    int id;
    String kind;
    String Subid;
    String Subname;

    public String getSubid() {
        return Subid;
    }

    public void setSubid(String subid) {
        Subid = subid;
    }

    public String getSubname() {
        return Subname;
    }

    public void setSubname(String subname) {
        Subname = subname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public Bitmap getBitmap() {
//        return bitmap;
//    }
//
//    public void setBitmap(Bitmap bitmap) {
//        this.bitmap = bitmap;
//    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
