package com.example.dllo.foodgroup.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by dllo on 16/11/7.
 */
public class CirleDrawable extends Drawable{
    private Bitmap mBitmap;
    private Paint mPaint;
    private int r;

    public CirleDrawable(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);// 抗锯齿
        BitmapShader shader = new BitmapShader(mBitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        mPaint.setShader(shader); // 设置画笔的花纹
        // 计算出半径
        r = Math.min(mBitmap.getHeight() / 2,mBitmap.getWidth() / 2);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mBitmap.getWidth() / 2,
                mBitmap.getHeight() / 2,r,mPaint);
    }

    @Override
    public void setAlpha(int i) {
        mPaint.setAlpha(i);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    // 负责告诉Drawable他的宽高是多少

    @Override
    public int getIntrinsicHeight() {
        return 2 * r;
    }

    @Override
    public int getIntrinsicWidth() {
        return 2 * r;
    }
}
