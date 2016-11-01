package com.example.dllo.foodgroup.strolleat.appraisal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.foodgroup.Bean.AppraisalItemBean;
import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.base.BaseViewHolder;
import com.example.dllo.foodgroup.tools.WebActivityListener;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/29.
 */
public class AppraisalAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    Context context;
    ArrayList<AppraisalItemBean> arrayList;
    WebActivityListener listener;

    public void setListener(WebActivityListener listener) {
        this.listener = listener;
    }

    public AppraisalAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<AppraisalItemBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.appraisal_item,parent,false);
//        AppraisalViewHolder viewHolder = new AppraisalViewHolder(view);
        return BaseViewHolder.getViewHolder(parent,R.layout.appraisal_item);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.setImage
                (R.id.appraisal_item_background,arrayList.
                        get(position).getBackground());
//                (arrayList.get(position).getBackground(),holder.background);
//        holder.source.setText(arrayList.get(position).getSource());
        holder.setText(R.id.appraisal_item_source,arrayList.get(position).getSource());
//        holder.title.setText(arrayList.get(position).getTitle());
        holder.setText(R.id.appraisal_item_title,arrayList.get(position).getTitle());
//        holder.tail.setText(arrayList.get(position).getTail());
        holder.setText(R.id.appraisal_item_tail,arrayList.get(position).getTail());

        holder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setUrl(arrayList.get(position).getLink());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null? 0 : arrayList.size();
    }
    public class AppraisalViewHolder extends RecyclerView.ViewHolder{

        private final ImageView background;
        private final TextView source;
        private final TextView title;
        private final TextView tail;

        public AppraisalViewHolder(View itemView) {
            super(itemView);
            background = (ImageView) itemView.findViewById(R.id.appraisal_item_background);
            source = (TextView) itemView.findViewById(R.id.appraisal_item_source);
            title = (TextView) itemView.findViewById(R.id.appraisal_item_title);
            tail = (TextView) itemView.findViewById(R.id.appraisal_item_tail);
        }
    }
}
