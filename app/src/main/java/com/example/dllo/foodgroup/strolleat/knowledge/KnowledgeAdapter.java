package com.example.dllo.foodgroup.strolleat.knowledge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.tools.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/29.
 */
public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.KnowledgeViewHolder> {

    Context context;
    ArrayList<KnowledgeItemBean> arraylist;

    public KnowledgeAdapter(Context context) {
        this.context = context;
    }

    public void setArraylist(ArrayList<KnowledgeItemBean> arraylist) {
        this.arraylist = arraylist;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return arraylist.get(position).getContent_type();
    }

    @Override
    public KnowledgeAdapter.KnowledgeViewHolder onCreateViewHolder(ViewGroup parent,int ViewType) {
        if (ViewType == 1){
            View view = LayoutInflater.from(context).inflate(R.layout.knowledge_shortitem,parent,false);
            KnowledgeViewHolder shortholder = new KnowledgeViewHolder(view);
            return shortholder;
        }else if(ViewType == 2){
            View views = LayoutInflater.from(context).inflate(R.layout.knowledge_longitem,parent,false);
            KnowledgeViewHolder longholder = new KnowledgeViewHolder(views);
            return longholder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(KnowledgeAdapter.KnowledgeViewHolder holder, int position) {
        if (arraylist.get(position).getContent_type()==1){
            holder.shortTitle.setText(arraylist.get(position).getTitle());
            holder.shortSource.setText(arraylist.get(position).getSource());
            holder.shortTail.setText(arraylist.get(position).getTail());
            VolleySingleton.getInstance().getImage
                    (arraylist.get(position).getImages().get(0),holder.shortImage);

        }else if(arraylist.get(position).getContent_type()==2){
            holder.longTitle.setText(arraylist.get(position).getTitle());
            holder.longSource.setText(arraylist.get(position).getSource());
            holder.longTail.setText(arraylist.get(position).getTail());

            VolleySingleton.getInstance().getImage
                    (arraylist.get(position).getImages().get(0),holder.leftImage);
            VolleySingleton.getInstance().getImage
                    (arraylist.get(position).getImages().get(1),holder.centerImage);
            VolleySingleton.getInstance().getImage
                    (arraylist.get(position).getImages().get(2),holder.rightImage);

        }

    }

    @Override
    public int getItemCount() {
        return arraylist == null ? 0 : arraylist.size();

    }

    public class KnowledgeViewHolder extends RecyclerView.ViewHolder{

        private ImageView shortImage;
        private TextView shortTitle;
        private TextView shortSource;
        private TextView shortTail;
        private TextView longTitle;
        private TextView longSource;
        private TextView longTail;
        private  ImageView leftImage;
        private  ImageView centerImage;
        private  ImageView rightImage;

        public KnowledgeViewHolder(View itemView) {
            super(itemView);
                shortImage = (ImageView) itemView.findViewById(R.id.knowledge_shortitem_images);
                shortTitle = (TextView) itemView.findViewById(R.id.knowledge_shortitem_title);
                shortSource = (TextView) itemView.findViewById(R.id.knowledge_shortitem_source);
                shortTail = (TextView) itemView.findViewById(R.id.knowledge_shortitem_tail);
            longTitle = (TextView) itemView.findViewById(R.id.knowledge_longitem_title);
            longSource = (TextView) itemView.findViewById(R.id.knowledge_longitem_source);
            longTail = (TextView) itemView.findViewById(R.id.knowledge_longitem_tail);
            leftImage = (ImageView) itemView.findViewById(R.id.knowledge_longitem_leftimage);
            centerImage = (ImageView) itemView.findViewById(R.id.knowledge_longitem_centerimage);
            rightImage = (ImageView) itemView.findViewById(R.id.knowledge_longitem_rightimage);

        }

    }
}
