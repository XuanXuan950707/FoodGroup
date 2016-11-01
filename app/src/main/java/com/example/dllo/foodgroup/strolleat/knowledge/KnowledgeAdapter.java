package com.example.dllo.foodgroup.strolleat.knowledge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.foodgroup.Bean.KnowledgeItemBean;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseViewHolder;
import com.example.dllo.foodgroup.tools.WebActivityListener;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/29.
 */
public class KnowledgeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private ArrayList<KnowledgeItemBean> arraylist;
    WebActivityListener webActivityListener;

    public void setWebActivityListener(WebActivityListener webActivityListener) {
        this.webActivityListener = webActivityListener;
    }

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
    public BaseViewHolder onCreateViewHolder(ViewGroup parent,int ViewType) {
        if (ViewType == 1){
//            View view = LayoutInflater.from(context).inflate(R.layout.knowledge_shortitem,parent,false);
//            KnowledgeViewHolder shortholder = new KnowledgeViewHolder(view);
            return BaseViewHolder.getViewHolder(parent,R.layout.knowledge_shortitem);
        }else if(ViewType == 2){
//            View views = LayoutInflater.from(context).inflate(R.layout.knowledge_longitem,parent,false);
//            KnowledgeViewHolder longholder = new KnowledgeViewHolder(views);
            return BaseViewHolder.getViewHolder(parent,R.layout.knowledge_longitem);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        if (arraylist.get(position).getContent_type()==1){
//            holder.shortTitle.setText(arraylist.get(position).getTitle());
            holder.setText(R.id.knowledge_shortitem_title,arraylist.get(position).getTitle());
//            holder.shortSource.setText(arraylist.get(position).getSource());
            holder.setText(R.id.knowledge_shortitem_source,arraylist.get(position).getSource());
//            holder.shortTail.setText(arraylist.get(position).getTail());
            holder.setText(R.id.knowledge_shortitem_tail,arraylist.get(position).getTail());
//            VolleySingleton.getInstance().getImage
//                    (arraylist.get(position).getImages().get(0),holder.shortImage);
            holder.setImage(R.id.knowledge_shortitem_images,arraylist.get(position).getImages().get(0));
            holder.setItemClick(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    webActivityListener.setUrl(arraylist.get(position).getLink());
                }
            });

        }else if(arraylist.get(position).getContent_type()==2){
//            holder.longTitle.setText();
            holder.setText(R.id.knowledge_longitem_title,arraylist.get(position).getTitle());
//            holder.longSource.setText();
            holder.setText(R.id.knowledge_longitem_source,arraylist.get(position).getSource());
//            holder.longTail.setText();
            holder.setText(R.id.knowledge_longitem_tail,arraylist.get(position).getTail());

//            VolleySingleton.getInstance().getImage
//                    (,holder.leftImage);
            holder.setImage(R.id.knowledge_longitem_leftimage,arraylist.get(position).getImages().get(0));
//            VolleySingleton.getInstance().getImage
//                    (arraylist.get(position).getImages().get(1),holder.centerImage);
            holder.setImage(R.id.knowledge_longitem_centerimage,arraylist.get(position).getImages().get(1));
//            VolleySingleton.getInstance().getImage
//                    (arraylist.get(position).getImages().get(2),holder.rightImage);
            holder.setImage(R.id.knowledge_longitem_rightimage,arraylist.get(position).getImages().get(2));

            holder.setItemClick(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    webActivityListener.setUrl(arraylist.get(position).getLink());
                }
            });
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
