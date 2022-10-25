package com.example.infits;

import android.graphics.drawable.Drawable;

public interface AddButtonListenerChart {
    void addButtonOnClick(String name, String calorie, String description, String nutrients, String ingredients, Drawable photo, String category, String preparation,String meal);
    void addButtonOnClick(String position,String meal);
}
