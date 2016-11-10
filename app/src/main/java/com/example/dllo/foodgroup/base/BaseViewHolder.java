package com.example.dllo.foodgroup.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.tools.CirleDrawable;
import com.example.dllo.foodgroup.tools.ImageThread;
import com.example.dllo.foodgroup.tools.VolleySingleton;

/**
 * Created by dllo on 16/10/31.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private View itemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArray<>();
    }

    /**
     * 通过View的id来获得指定的View
     * 如果该View没有赋值,就先执行findViewById
     * 然后把它放到View的集合里
     * @param id
     * @return 指定 View
     */
    public <T extends View> T getView(int id){
        View view = views.get(id);
        if (view == null){
            // 证明SpareArray里没有这个View
            view = itemView.findViewById(id);
            views.put(id,view);
        }
        return (T) view;
    }

    // 专门给ListView使用的方法
    public static BaseViewHolder getViewHolder(View itemView, ViewGroup parent,int itemId){
        BaseViewHolder baseViewHolder;
        if (itemView == null){
            Context context = parent.getContext();
            itemView = LayoutInflater.from(context).inflate(itemId,parent,false);
            baseViewHolder = new BaseViewHolder(itemView);
            itemView.setTag(baseViewHolder);
        }else {
            baseViewHolder = (BaseViewHolder) itemView.getTag();
        }
        return baseViewHolder;
    }

    // 专门给ListView使用的方法
    public static BaseViewHolder getViewHolder(ViewGroup viewGroup,int itemId){
        return getViewHolder(null,viewGroup,itemId);
    }

    // 返回行布局
    public View getItemView(){return itemView;}

    /*******ViewHolder 设置数据方法********/

    public BaseViewHolder setText(int id,String text){
        TextView textView = getView(id);
        textView.setText(text);
        return this;
    }
    public BaseViewHolder setTextVisible(int id){
        TextView textView = getView(id);
        textView.setVisibility(View.VISIBLE);
        return this;
    }

    public BaseViewHolder setImage(int id,int imgId){
        ImageView imageView = getView(id);
        imageView.setImageResource(imgId);
        return this;
    }

    public BaseViewHolder setImage(int id,String url){
        ImageView imageView = getView(id);
        VolleySingleton.getInstance().getImage
                (url,imageView);
        return this;
    }

    public BaseViewHolder setCirleImage(int id,String url){
        final ImageView imageView = getView(id);
//        Bitmap bitmap = BitmapFactory.decodeResource
//                (context.getResources(), R.mipmap.img_default_compared_food);

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
        new ImageThread(handler,url).start();
        return this;
    }

    public BaseViewHolder setItemClick(View.OnClickListener listener){
        itemView.setOnClickListener(listener);
        return this;
    }

    public BaseViewHolder setViewClick(int id, View.OnClickListener listener){
        getView(id).setOnClickListener(listener);
        return this;
    }
}
