package com.example.dllo.foodgroup.strolleat.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.tools.CirclemageView;
import com.example.dllo.foodgroup.tools.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/27.
 */
public class HomepageAdapter extends RecyclerView.Adapter<HomepageAdapter.MyViewHolder> {
    Context context;
    ArrayList<HomepageItemBean> arrayList;

    public HomepageAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<HomepageItemBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate
                (R.layout.homepage_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        VolleySingleton.getInstance().getImage
                (arrayList.get(position).getCardImage(),holder.cardImage);
        VolleySingleton.getInstance().getImage
                (arrayList.get(position).getPublisherImage(),holder.publisherImage);
        holder.title.setText(arrayList.get(position).getTitle());
//        Log.d("response", arrayList.get(position).getTitle());
        holder.likeCt.setText(arrayList.get(position).getLikeCt());
        holder.publisher.setText(arrayList.get(position).getPublisher());
        holder.description.setText(arrayList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return arrayList == null? 0 : arrayList.size() ;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView cardImage;
        private final TextView title;

        private final TextView likeCt;
        private final TextView publisher;
        private final TextView description;
        private final CirclemageView publisherImage;


        public MyViewHolder(View itemView) {
            super(itemView);
            cardImage = (ImageView) itemView.findViewById(R.id.homepage_item_cardimage);
            title = (TextView) itemView.findViewById(R.id.homepage_item_title);
            publisherImage = (CirclemageView) itemView.findViewById(R.id.homepage_item_publisherimage);
            likeCt = (TextView) itemView.findViewById(R.id.homepage_item_likect);
            publisher = (TextView) itemView.findViewById(R.id.homepage_item_publisher);
            description = (TextView) itemView.findViewById(R.id.homepage_item_description);
        }
    }
}
