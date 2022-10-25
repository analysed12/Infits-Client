package com.example.infits;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class Dietician_review_adapter extends RecyclerView.Adapter<Dietician_review_adapter.Dietician_review_ViewHolder> {
    Context ct;
    private List<Dietician_review> list1;
    Dietician_review_adapter(Context ct, List<Dietician_review> list1){
        this.ct = ct;
        this.list1=list1;
    }

    @NonNull
    @Override
    public Dietician_review_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view=inflater.inflate(R.layout.dietician_reviews,parent,false);
        return new Dietician_review_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Dietician_review_ViewHolder holder, int position) {
        Dietician_review pos=list1.get(position);

        String r=pos.getReviewer_rating();
        String t=pos.getReviwer_review();
        String img=pos.getReviewer_img();
        String n=pos.getReviewer_name();

        File imgFile = new File(img);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            holder.rimg.setImageBitmap(myBitmap);

        }
        holder.rrating.setText(r);
        holder.rreview.setText(t);
        holder.rname.setText(n);
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    class Dietician_review_ViewHolder extends RecyclerView.ViewHolder{

        TextView rreview,rname,rrating;
        ImageView rimg;

        public Dietician_review_ViewHolder(@NonNull View itemView) {
            super(itemView);
            rreview= itemView.findViewById(R.id.reviewer_review);
            rrating=itemView.findViewById(R.id.reviewer_rating);
            rimg=itemView.findViewById(R.id.review_profile_pic);
            rname=itemView.findViewById(R.id.reviewer_name);
        }
    }
}
