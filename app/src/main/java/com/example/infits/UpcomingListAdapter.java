package com.example.infits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UpcomingListAdapter extends RecyclerView.Adapter<UpcomingListAdapter.UpcomingListViewHolder> {
    Context context;

    ArrayList<String> upTitle = new ArrayList<>();
    ArrayList<String> upDate = new ArrayList<>();
    ArrayList<String> upTime = new ArrayList<>();
    ArrayList<String> upNote = new ArrayList<>();

    UpcomingListAdapter(Context context, ArrayList<String> upTitle, ArrayList<String> upDate, ArrayList<String> upTime, ArrayList<String> upNote){
        this.context = context;
        this.upTitle = upTitle;
        this.upDate = upDate;
        this.upNote = upNote;
        this.upTime = upTime;
    }
    @NonNull
    @Override
    public UpcomingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.upcoming_list_item,parent,false);
        return new UpcomingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingListViewHolder holder, int position) {
        holder.upTitle.setText(upTitle.get(position));
        holder.upNotes.setText(upNote.get(position));
        holder.upTime.setText(upTime.get(position));
        holder.upDate.setText(upDate.get(position));
    }

    @Override
    public int getItemCount() {
        return upTitle.size();
    }

    public class UpcomingListViewHolder extends RecyclerView.ViewHolder {
        TextView upTitle,upNotes,upDate,upTime;
        public UpcomingListViewHolder(@NonNull View itemView) {
            super(itemView);
            upNotes = itemView.findViewById(R.id.notes);
            upTitle = itemView.findViewById(R.id.title);
            upDate = itemView.findViewById(R.id.date);
            upTime = itemView.findViewById(R.id.clock);
        }
    }
}
