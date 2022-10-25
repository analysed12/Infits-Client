package com.example.infits;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FoodDetailsDialog extends BottomSheetDialogFragment {

    FoodDetailsListener foodDetailsListener;

    ImageView close,foodPhoto;

    String name;
    String calorie;
    String description;
    String nutrients;
    String ingredients;
    Drawable photo;
    String category;
    String preparation;

    TextView food_name,food_calories,food_description,food_nutrients,food_ingredients,food_category, food_preparation;

    FoodDetailsDialog(String name, String calorie, String description, String nutrients, String ingredients, Drawable photo, String category, String preparation){
        this.name = name;
        this.calorie = calorie;
        this.description = description;
        this.nutrients = nutrients;
        this.ingredients = ingredients;
        this.photo = photo;
        this.category = category;
        this.preparation = preparation;
    }

    FoodDetailsDialog(){

    }

    public static FoodDetailsDialog  newInstance() {
        FoodDetailsDialog fragment = new FoodDetailsDialog();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.food_details_dialog, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        close = contentView.findViewById(R.id.close_food_details);
        foodPhoto = contentView.findViewById(R.id.food_photo);
        food_name = contentView.findViewById(R.id.food_name);
        food_calories = contentView.findViewById(R.id.food_calories);
        food_description = contentView.findViewById(R.id.food_description);
        food_nutrients = contentView.findViewById(R.id.food_nutrients);
        food_ingredients = contentView.findViewById(R.id.food_ingredients);
        food_preparation = contentView.findViewById(R.id.food_preparation);

        foodPhoto.setImageDrawable(photo);
        food_name.setText(name.toUpperCase());
        food_calories.setText(calorie + " kcal");
        food_description.setText(description);
        food_nutrients.setText(nutrients);
        food_ingredients.setText(ingredients);
        food_preparation.setText(preparation);
        close.setOnClickListener(v->{
            dialog.dismiss();
        });
    }

}
