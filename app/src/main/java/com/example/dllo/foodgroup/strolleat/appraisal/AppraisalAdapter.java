package com.example.dllo.foodgroup.strolleat.appraisal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.foodgroup.R;
import com.example.dllo.foodgroup.strolleat.homepage.HomepageAdapter;
import com.example.dllo.foodgroup.tools.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/29.
 */
public class AppraisalAdapter extends RecyclerView.Adapter<AppraisalAdapter.AppraisalViewHolder>{
    Context context;
    ArrayList<AppraisalItemBean> arrayList;

    public AppraisalAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<AppraisalItemBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public AppraisalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.appraisal_item,parent,false);
        AppraisalViewHolder viewHolder = new AppraisalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AppraisalViewHolder holder, int position) {
        VolleySingleton.getInstance().getImage
                (arrayList.get(position).getBackground(),holder.background);
        holder.source.setText(arrayList.get(position).getSource());
        holder.title.setText(arrayList.get(position).getTitle());
        holder.tail.setText(arrayList.get(position).getTail());
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
