package com.example.dllo.foodgroup.strolleat.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.foodgroup.Bean.HomepageItemBean;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseViewHolder;
import com.example.dllo.foodgroup.tools.CirclemageView;
import com.example.dllo.foodgroup.tools.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/27.
 */
public class HomepageAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private ArrayList<HomepageItemBean> arrayList;

    public HomepageAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<HomepageItemBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        int i = 0;
        if (arrayList.get(position).getTitle() == null){

            i = 2;
        }
        else if (arrayList.get(position).getTitle() != null){
            i = 1;
            Log.d("aaaaaa", arrayList.get(position).getTitle());
        }
        return i;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
//            View view = LayoutInflater.from(context).inflate
//                    (R.layout.homepage_item, parent, false);
//            MyViewHolder myViewHolder = new MyViewHolder(view);
            return BaseViewHolder.getViewHolder(parent,R.layout.homepage_item);
        }else if(viewType == 2){
//            View view1 = LayoutInflater.from(context).inflate
//                    (R.layout.homepage_shortitem,parent,false);
//            MyViewHolder myViewHolder = new MyViewHolder(view1);
            return BaseViewHolder.getViewHolder(parent,R.layout.homepage_shortitem);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        if (arrayList.get(position).getTitle() != null) {
//            VolleySingleton.getInstance().getImage
//                    (, holder.cardImage);
            holder.setImage(R.id.homepage_item_cardimage,arrayList.get(position).getCardImage());
//            VolleySingleton.getInstance().getImage
//                    (, holder.publisherImage);
            holder.setImage(R.id.homepage_item_publisherimage,
                    arrayList.get(position).getPublisherImage());
//            holder.title.setText();
            holder.setText(R.id.homepage_item_title,arrayList.get(position).getTitle());
//        Log.d("response", arrayList.get(position).getTitle());
//            holder.likeCt.setText();
            holder.setText(R.id.homepage_item_likect,arrayList.get(position).getLikeCt());
//            holder.publisher.setText();
            holder.setText(R.id.homepage_item_publisher,arrayList.get(position).getPublisher());
//            holder.description.setText();
            holder.setText(R.id.homepage_item_description,arrayList.get(position).getDescription());
        }else if(arrayList.get(position).getTitle() == null) {
//            VolleySingleton.getInstance().getImage
//                    (,holder.shortCardImage);
            holder.setImage(R.id.homepage_shortitem_iv,arrayList.get(position).getCardImage());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList == null? 0 : arrayList.size() ;
    }
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//
//        private final ImageView cardImage;
//        private final TextView title;
//
//        private final TextView likeCt;
//        private final TextView publisher;
//        private final TextView description;
//        private final CirclemageView publisherImage;
//        private final ImageView shortCardImage;
//
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            cardImage = (ImageView) itemView.findViewById(R.id.homepage_item_cardimage);
//            title = (TextView) itemView.findViewById(R.id.homepage_item_title);
//            publisherImage = (CirclemageView) itemView.findViewById(R.id.homepage_item_publisherimage);
//            likeCt = (TextView) itemView.findViewById(R.id.homepage_item_likect);
//            publisher = (TextView) itemView.findViewById(R.id.homepage_item_publisher);
//            description = (TextView) itemView.findViewById(R.id.homepage_item_description);
//            shortCardImage = (ImageView) itemView.findViewById(R.id.homepage_shortitem_iv);
//        }
//    }
}
