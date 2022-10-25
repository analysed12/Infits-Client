package com.example.infits;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by adityagohad on 06/06/17.
 */

public class PickerAdapterWeight extends RecyclerView.Adapter<PickerAdapterWeight.TextVH> {

    private Context context;
    private List<String> dataList;
    private RecyclerView recyclerView;

    public PickerAdapterWeight(Context context, List<String> dataList, RecyclerView recyclerView) {
        this.context = context;
        this.dataList = dataList;
        this.recyclerView = recyclerView;
    }

    @Override
    public TextVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.picker_item_layout, parent, false);
        return new TextVH(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(TextVH holder, final int position) {
        holder.points.setText(dataList.get(position));
        if (position % 5 == 0){
//            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.interval ));
            if (position > 10){
                holder.points.setPadding(0,20,0,20);
            }
            else {
                holder.points.setPadding(5,20,5,20);
            }
            holder.points.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.interval_weight,0,0);
            holder.points.setText(String.valueOf(position));
            holder.points.setTextColor(Color.parseColor("#545454"));
        }
        else {
            if (position > 10) {
                holder.points.setPadding(0, 20, 0, 20);
            } else {
                holder.points.setPadding(5, 20, 5, 20);
            }
            holder.points.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.lines_weight, 0, 0);
            holder.points.setText(String.valueOf(position));
            holder.points.setTextColor(Color.parseColor("#FFFFFF"));
        }
//        if (position % 5 == 0){
////            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.interval ));
//
//            holder.points.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.interval_weight,0,0);
//            holder.points.setText(String.valueOf(position));
//            holder.points.setTextColor(Color.parseColor("#545454"));
//            holder.points.setPadding(0,15,0,15);
//        }
//        else{
//            holder.points.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.lines_weight,0,0);
//            holder.points.setText(String.valueOf(position));
//            holder.points.setPadding(0,15,0,15);
//            holder.points.setTextColor(Color.parseColor("#FFFFFF"));
//        }
        holder.points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerView != null) {
                    recyclerView.smoothScrollToPosition(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void swapData(List<String> newData) {
        dataList = newData;
        notifyDataSetChanged();
    }

    class TextVH extends RecyclerView.ViewHolder {
        TextView points;
//        ImageView imageView;
        public TextVH(View itemView) {
            super(itemView);
            points = (TextView) itemView.findViewById(R.id.interval_text);
//            imageView = itemView.findViewById(R.id.points);
        }
    }
}