package com.example.infits;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddSnack extends RecyclerView.Adapter<AddSnack.AddBreakfastViewHolder> {

    List<ModelForFood> breakfastList;
    Context context;
    AddButtonListenerChart addButtonListenerChart;

    public AddSnack(List<ModelForFood> breakfastList, Context context, AddButtonListenerChart addButtonListenerChart) {
        this.breakfastList = breakfastList;
        this.context = context;
        this.addButtonListenerChart = addButtonListenerChart;
    }

    @NonNull
    @Override
    public AddBreakfastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddBreakfastViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.add_btn_for_diet_plan,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddBreakfastViewHolder holder, int position) {
        holder.photo.setImageDrawable(breakfastList.get(position).getPhoto());
//        holder.photo.setImageDrawable(context.getResources().getDrawable(R.drawable.add_food));
        Bitmap bit = BitmapFactory.decodeResource(context.getResources(), R.drawable.add_food);
        Drawable b = new BitmapDrawable(context.getResources(),bit);
        holder.small_pic.setOnClickListener(v->{
        if (position != 0 ){
            if(breakfastList.get(position-1).getPhoto().getConstantState()  != context.getResources().getDrawable(R.drawable.add_food).getConstantState()){
                addButtonListenerChart.addButtonOnClick(String.valueOf(position),"snack");
            }
            else{
                Toast.makeText(context, "ChoosePrevious", Toast.LENGTH_SHORT).show();
//            System.out.println(holder.photo.getDrawable().getConstantState());
//            System.out.println(context.getResources().getDrawable(R.drawable.add_food).getConstantState());
            }
        }
        else if (position == 0){
            addButtonListenerChart.addButtonOnClick(String.valueOf(position),"snack");
        }
        });
    }

    @Override
    public int getItemCount() {
        return breakfastList.size();
    }

    public class AddBreakfastViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        CardView small_pic;

        public AddBreakfastViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.plus);
            small_pic = itemView.findViewById(R.id.small_pic);
        }
    }
}
