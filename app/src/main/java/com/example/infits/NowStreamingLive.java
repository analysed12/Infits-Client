package com.example.infits;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NowStreamingLive extends RecyclerView.Adapter<NowStreamingLive.NowStreamingLiveViewHolder> {
    Context context;
    ArrayList<String> nowTitle = new ArrayList<>();
    ArrayList<String> nowDate = new ArrayList<>();
    ArrayList<String> nowTime = new ArrayList<>();
    ArrayList<String> nowNote = new ArrayList<>();
    NowStreamingLive(Context context, ArrayList<String> nowTitle, ArrayList<String> nowDate, ArrayList<String> nowTime, ArrayList<String> nowNote){
        this.context = context;
        this.nowTitle = nowTitle;
        this.nowDate  = nowDate;
        this.nowTime  = nowTime;
        this.nowNote  = nowNote;
    }
    @NonNull
    @Override
    public NowStreamingLiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.live_list_items,parent,false);
        return new NowStreamingLiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NowStreamingLiveViewHolder holder, int position) {
                    holder.nowTitle.setText(nowTitle.get(position));
                    holder.nowNotes.setText(nowNote.get(position));
                    holder.joinLive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LiveListAct.act.GotoLive(nowTitle.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return nowTitle.size();
//        return 2;
    }

    public class NowStreamingLiveViewHolder extends RecyclerView.ViewHolder {
        TextView nowTitle,nowNotes;
        ImageButton joinLive;
        public NowStreamingLiveViewHolder(@NonNull View itemView) {
            super(itemView);
            nowNotes = itemView.findViewById(R.id.notes);
            nowTitle = itemView.findViewById(R.id.title);
            joinLive = itemView.findViewById(R.id.join_live);
        }
    }
}