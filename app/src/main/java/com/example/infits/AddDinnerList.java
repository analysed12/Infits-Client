package com.example.infits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddDinnerList extends RecyclerView.Adapter<AddDinnerList.AddBreakfastListViewHolder> {

    List<ModelForFood> breakfastList;
    Context context;
    AddButtonListenerChart addButtonListenerChart;
    FoodDetailsListener foodDetailsListener;


    public AddDinnerList(List<ModelForFood> breakfastList, Context context, AddButtonListenerChart addButtonListenerChart, FoodDetailsListener foodDetailsListener) {
        this.breakfastList = breakfastList;
        this.context = context;
        this.addButtonListenerChart = addButtonListenerChart;
        this.foodDetailsListener = foodDetailsListener;
    }

    @NonNull
    @Override
    public AddBreakfastListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddBreakfastListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.food_items_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddBreakfastListViewHolder holder, int position) {
        holder.photo.setImageDrawable(breakfastList.get(position).getPhoto());
        holder.name.setText(breakfastList.get(position).getName());
        holder.calorie.setText(breakfastList.get(position).getCalorie()+" kcal");
        holder.quantity.setText(breakfastList.get(position).getQuantity());
        holder.itemList.setOnClickListener(v->{
            foodDetailsListener.setDetails(breakfastList.get(position).getName(),breakfastList.get(position).getCalorie(),breakfastList.get(position).getDescription(),breakfastList.get(position).getNutrients(),breakfastList.get(position).getIngredients(),breakfastList.get(position).getPhoto(),"",breakfastList.get(position).getPreparation());
        });
    }

    @Override
    public int getItemCount() {
        return breakfastList.size();
    }

    public class AddBreakfastListViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView name,calorie,quantity;
        LinearLayout itemList;

        public AddBreakfastListViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
            name = itemView.findViewById(R.id.food_name);
            calorie = itemView.findViewById(R.id.food_calorie);
            itemList = itemView.findViewById(R.id.list_item);
            quantity = itemView.findViewById(R.id.amount);
//            small_pic = itemView.findViewById(R.id.small_pic);
        }
    }
}