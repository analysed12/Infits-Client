package com.example.infits;

import android.graphics.drawable.Drawable;

public interface FoodDetailsListener {
    void setDetails(int position);
    void setDetails(String name, String calorie, String description, String nutrients, String ingredients, Drawable photo, String category, String preparation);
}
