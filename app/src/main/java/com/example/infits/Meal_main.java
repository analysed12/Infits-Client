package com.example.infits;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Meal_main extends AppCompatActivity {

    RadioGroup days;

    RadioButton sun, mon, tue, wed, thur, fri, sat;

    //data models for every recycler

    public static List<ModelForFood> breakfastList;
    public static List<ModelForFood> breakfastListExpand;

    public static List<ModelForFood> breakfastListMon;
    public static List<ModelForFood> breakfastListExpandMon;

    public static List<ModelForFood> breakfastListTue;
    public static List<ModelForFood> breakfastListExpandTue;

    public static List<ModelForFood> breakfastListWed;
    public static List<ModelForFood> breakfastListExpandWed;

    public static List<ModelForFood> breakfastListThur;
    public static List<ModelForFood> breakfastListExpandThur;

    public static List<ModelForFood> breakfastListFri;
    public static List<ModelForFood> breakfastListExpandFri;

    public static List<ModelForFood> breakfastListSat;
    public static List<ModelForFood> breakfastListExpandSat;

    public static List<ModelForFood> lunchList;
    public static List<ModelForFood> lunchListExpand;

    public static List<ModelForFood> lunchListMon;
    public static List<ModelForFood> lunchListExpandMon;

    public static List<ModelForFood> lunchListTue;
    public static List<ModelForFood> lunchListExpandTue;

    public static List<ModelForFood> lunchListWed;
    public static List<ModelForFood> lunchListExpandWed;

    public static List<ModelForFood> lunchListThur;
    public static List<ModelForFood> lunchListExpandThur;

    public static List<ModelForFood> lunchListFri;
    public static List<ModelForFood> lunchListExpandFri;

    public static List<ModelForFood> lunchListSat;
    public static List<ModelForFood> lunchListExpandSat;

    public static List<ModelForFood> snackList;
    public static List<ModelForFood> snackListExpand;

    public static List<ModelForFood> snackListMon;
    public static List<ModelForFood> snackListExpandMon;

    public static List<ModelForFood> snackListTue;
    public static List<ModelForFood> snackListExpandTue;

    public static List<ModelForFood> snackListWed;
    public static List<ModelForFood> snackListExpandWed;

    public static List<ModelForFood> snackListThur;
    public static List<ModelForFood> snackListExpandThur;

    public static List<ModelForFood> snackListFri;
    public static List<ModelForFood> snackListExpandFri;

    public static List<ModelForFood> snackListSat;
    public static List<ModelForFood> snackListExpandSat;

    public static List<ModelForFood> dinnerList;
    public static List<ModelForFood> dinnerListExpand;

    public static List<ModelForFood> dinnerListMon;
    public static List<ModelForFood> dinnerListExpandMon;

    public static List<ModelForFood> dinnerListTue;
    public static List<ModelForFood> dinnerListExpandTue;

    public static List<ModelForFood> dinnerListWed;
    public static List<ModelForFood> dinnerListExpandWed;

    public static List<ModelForFood> dinnerListThur;
    public static List<ModelForFood> dinnerListExpandThur;

    public static List<ModelForFood> dinnerListFri;
    public static List<ModelForFood> dinnerListExpandFri;

    public static List<ModelForFood> dinnerListSat;
    public static List<ModelForFood> dinnerListExpandSat;

    RadioButton breakfast, lunch, dinner, snack;
    CardView breakfast_card, dinner_card, lunch_card, snack_card;
    LinearLayout item_overview_breakfast, item_overview_lunch, item_overview_snack, item_overview_dinner;

    RecyclerView breakfast_list, lunch_list, snack_list, dinner_list, add_breakfast, add_lunch, add_dinner, add_snack;

    AddButtonListenerChart addButtonListenerChart;

    public static FoodDetailsListener foodDetailsListener;

    ActivityResultLauncher<Intent> loadTemplate = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                }
            }
    );
    ActivityResultLauncher<Intent> addFood = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String position = data.getStringExtra("position");
                        String name = data.getStringExtra("name");
                        String description = data.getStringExtra("description");
                        String meal = data.getStringExtra("meal");
                        byte[] photoArr = data.getByteArrayExtra("photo");
                        Bitmap bitmap = BitmapFactory.decodeByteArray(photoArr, 0, photoArr.length);
                        Drawable photo = new BitmapDrawable(getResources(), bitmap);

                        if (meal.equals("breakfast")) {
                            breakfastList.get(Integer.parseInt(position)).setName(name);
//                                breakfastList.get(Integer.parseInt(position)).setCalorie(calorie);
                            breakfastList.get(Integer.parseInt(position)).setPhoto(photo);
                            try {
//                                    breakfastListExpand.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
                            } catch (Exception ex) {
                                Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
//                                    breakfastListExpand.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
                            }
                            if (breakfastListExpand.size() >= 4) {
                                breakfastList.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
                            }
                            add_breakfast.setAdapter(new AddBreakfast(breakfastList, getApplicationContext(), addButtonListenerChart));
                            add_breakfast.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                            breakfast_list.setAdapter(new AddBreakfastList(breakfastListExpand, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
                            breakfast_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                        if (meal.equals("lunch")) {
                            Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
                            lunchList.get(Integer.parseInt(position)).setName(name);
//                                lunchList.get(Integer.parseInt(position)).setCalorie(calorie);
                            lunchList.get(Integer.parseInt(position)).setPhoto(photo);
                            try {
                                lunchListExpand.set(Integer.parseInt(position), new ModelForFood(name, description, photo));
                            } catch (Exception ex) {
                                Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
                                lunchListExpand.add(new ModelForFood(name, description, photo));
                            }
                            if (lunchListExpand.size() >= 4) {
                                lunchList.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
                            }
                            add_lunch.setAdapter(new AddLunch(lunchList, getApplicationContext(), addButtonListenerChart));
                            add_lunch.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                            lunch_list.setAdapter(new AddLunchList(lunchListExpand, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
                            lunch_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                        if (meal.equals("snack")) {
                            Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
                            snackList.get(Integer.parseInt(position)).setName(name);
//                            snackList.get(Integer.parseInt(position)).setCalorie(calorie);
                            snackList.get(Integer.parseInt(position)).setPhoto(photo);
                            try {
                                snackListExpand.set(Integer.parseInt(position), new ModelForFood(name, description, photo));
                            } catch (Exception ex) {
                                Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
                                snackListExpand.add(new ModelForFood(name, description, photo));
                            }
                            if (snackListExpand.size() >= 4) {
                                snackList.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
                            }
                            add_snack.setAdapter(new AddSnack(snackList, getApplicationContext(), addButtonListenerChart));
                            add_snack.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                            snack_list.setAdapter(new AddSnackList(snackListExpand, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
                            snack_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                        if (meal.equals("dinner")) {
                            Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
                            dinnerList.get(Integer.parseInt(position)).setName(name);
                            dinnerList.get(Integer.parseInt(position)).setPhoto(photo);
                            try {
                                dinnerListExpand.set(Integer.parseInt(position), new ModelForFood(name, description, photo));
                            } catch (Exception ex) {
                                Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
                                dinnerListExpand.add(new ModelForFood(name, description, photo));
                            }
                            if (dinnerListExpand.size() >= 4) {
                                dinnerList.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
                            }
                            add_dinner.setAdapter(new AddDinner(dinnerList, getApplicationContext(), addButtonListenerChart));
                            add_dinner.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                            dinner_list.setAdapter(new AddDinnerList(dinnerListExpand, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
                            dinner_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }

                        addToDatabase(name, description, meal, position, Base64.encodeToString(photoArr, Base64.DEFAULT));
//                        if (sun.isChecked()) {
//                            if (meal.equals("breakfast")) {
//                                breakfastList.get(Integer.parseInt(position)).setName(name);
////                                breakfastList.get(Integer.parseInt(position)).setCalorie(calorie);
//                                breakfastList.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
////                                    breakfastListExpand.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
////                                    breakfastListExpand.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (breakfastListExpand.size() >= 4) {
//                                    breakfastList.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_breakfast.setAdapter(new AddBreakfast(breakfastList, getApplicationContext(), addButtonListenerChart));
//                                add_breakfast.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                breakfast_list.setAdapter(new AddBreakfastList(breakfastListExpand, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                breakfast_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("lunch")) {
//                                Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
//                                lunchList.get(Integer.parseInt(position)).setName(name);
////                                lunchList.get(Integer.parseInt(position)).setCalorie(calorie);
//                                lunchList.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    lunchListExpand.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
//                                    lunchListExpand.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (lunchListExpand.size() >= 4) {
//                                    lunchList.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_lunch.setAdapter(new AddLunch(lunchList, getApplicationContext(), addButtonListenerChart));
//                                add_lunch.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                lunch_list.setAdapter(new AddLunchList(lunchListExpand, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                lunch_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("snack")) {
//                                Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
//                                snackList.get(Integer.parseInt(position)).setName(name);
//                                snackList.get(Integer.parseInt(position)).setCalorie(calorie);
//                                snackList.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    snackListExpand.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
//                                    snackListExpand.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (snackListExpand.size() >= 4) {
//                                    snackList.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_snack.setAdapter(new AddSnack(snackList, getApplicationContext(), addButtonListenerChart));
//                                add_snack.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                snack_list.setAdapter(new AddSnackList(snackListExpand, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                snack_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("dinner")) {
//                                Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
//                                dinnerList.get(Integer.parseInt(position)).setName(name);
//                                dinnerList.get(Integer.parseInt(position)).setCalorie(calorie);
//                                dinnerList.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    dinnerListExpand.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
//                                    dinnerListExpand.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (dinnerListExpand.size() >= 4) {
//                                    dinnerList.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_dinner.setAdapter(new AddDinner(dinnerList, getApplicationContext(), addButtonListenerChart));
//                                add_dinner.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                dinner_list.setAdapter(new AddDinnerList(dinnerListExpand, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                dinner_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                        }
//                        if (mon.isChecked()) {
//                            if (meal.equals("breakfast")) {
//                                breakfastListMon.get(Integer.parseInt(position)).setName(name);
//                                breakfastListMon.get(Integer.parseInt(position)).setCalorie(calorie);
//                                breakfastListMon.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    breakfastListExpandMon.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    breakfastListExpandMon.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (breakfastListExpandMon.size() >= 4) {
//                                    breakfastListMon.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_breakfast.setAdapter(new AddBreakfast(breakfastListMon, getApplicationContext(), addButtonListenerChart));
//                                add_breakfast.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                breakfast_list.setAdapter(new AddBreakfastList(breakfastListExpandMon, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                breakfast_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("lunch")) {
//                                lunchListMon.get(Integer.parseInt(position)).setName(name);
//                                lunchListMon.get(Integer.parseInt(position)).setCalorie(calorie);
//                                lunchListMon.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    lunchListExpandMon.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    lunchListExpandMon.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (lunchListExpandMon.size() >= 4) {
//                                    lunchListMon.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_lunch.setAdapter(new AddLunch(lunchListMon, getApplicationContext(), addButtonListenerChart));
//                                add_lunch.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                lunch_list.setAdapter(new AddLunchList(lunchListExpandMon, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                lunch_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("snack")) {
//                                snackListMon.get(Integer.parseInt(position)).setName(name);
//                                snackListMon.get(Integer.parseInt(position)).setCalorie(calorie);
//                                snackListMon.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    snackListExpandMon.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    snackListExpandMon.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (snackListExpandMon.size() >= 4) {
//                                    snackListMon.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_snack.setAdapter(new AddSnack(snackListMon, getApplicationContext(), addButtonListenerChart));
//                                add_snack.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                snack_list.setAdapter(new AddSnackList(snackListExpandMon, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                snack_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("dinner")) {
//                                dinnerListMon.get(Integer.parseInt(position)).setName(name);
//                                dinnerListMon.get(Integer.parseInt(position)).setCalorie(calorie);
//                                dinnerListMon.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    dinnerListExpandMon.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    dinnerListExpandMon.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (dinnerListExpandMon.size() >= 4) {
//                                    dinnerListMon.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_dinner.setAdapter(new AddDinner(dinnerListMon, getApplicationContext(), addButtonListenerChart));
//                                add_dinner.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                dinner_list.setAdapter(new AddDinnerList(dinnerListExpandMon, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                dinner_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                        }
//                        if (tue.isChecked()) {
//                            if (meal.equals("breakfast")) {
//                                breakfastListTue.get(Integer.parseInt(position)).setName(name);
//                                breakfastListTue.get(Integer.parseInt(position)).setCalorie(calorie);
//                                breakfastListTue.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    breakfastListExpandTue.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    breakfastListExpandTue.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (breakfastListExpandTue.size() >= 4) {
//                                    breakfastListTue.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_breakfast.setAdapter(new AddBreakfast(breakfastListTue, getApplicationContext(), addButtonListenerChart));
//                                add_breakfast.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                breakfast_list.setAdapter(new AddBreakfastList(breakfastListExpandTue, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                breakfast_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("lunch")) {
//                                lunchListTue.get(Integer.parseInt(position)).setName(name);
//                                lunchListTue.get(Integer.parseInt(position)).setCalorie(calorie);
//                                lunchListTue.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    lunchListExpandTue.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    lunchListExpandTue.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (lunchListExpandTue.size() >= 4) {
//                                    lunchListTue.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_lunch.setAdapter(new AddLunch(lunchListTue, getApplicationContext(), addButtonListenerChart));
//                                add_lunch.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                lunch_list.setAdapter(new AddLunchList(lunchListExpandTue, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                lunch_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("snack")) {
//                                snackListTue.get(Integer.parseInt(position)).setName(name);
//                                snackListTue.get(Integer.parseInt(position)).setCalorie(calorie);
//                                snackListTue.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    snackListExpandTue.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    snackListExpandTue.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (snackListExpandTue.size() >= 4) {
//                                    snackList.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_snack.setAdapter(new AddSnack(snackListTue, getApplicationContext(), addButtonListenerChart));
//                                add_snack.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                snack_list.setAdapter(new AddSnackList(snackListExpandTue, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                snack_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("dinner")) {
//                                Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
//                                dinnerListTue.get(Integer.parseInt(position)).setName(name);
//                                dinnerListTue.get(Integer.parseInt(position)).setCalorie(calorie);
//                                dinnerListTue.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    dinnerListExpandTue.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    dinnerListExpandTue.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (dinnerListExpandTue.size() >= 4) {
//                                    dinnerListTue.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_dinner.setAdapter(new AddDinner(dinnerListTue, getApplicationContext(), addButtonListenerChart));
//                                add_dinner.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                dinner_list.setAdapter(new AddDinnerList(dinnerListExpandTue, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                dinner_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                        }
//                        if (wed.isChecked()) {
//                            if (meal.equals("breakfast")) {
//                                breakfastListWed.get(Integer.parseInt(position)).setName(name);
//                                breakfastListWed.get(Integer.parseInt(position)).setCalorie(calorie);
//                                breakfastListWed.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    breakfastListExpandWed.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    breakfastListExpandWed.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (breakfastListExpandWed.size() >= 4) {
//                                    breakfastListWed.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_breakfast.setAdapter(new AddBreakfast(breakfastListWed, getApplicationContext(), addButtonListenerChart));
//                                add_breakfast.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                breakfast_list.setAdapter(new AddBreakfastList(breakfastListExpandWed, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                breakfast_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("lunch")) {
//                                lunchListWed.get(Integer.parseInt(position)).setName(name);
//                                lunchListWed.get(Integer.parseInt(position)).setCalorie(calorie);
//                                lunchListWed.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    lunchListExpandWed.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    lunchListExpandWed.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (lunchListExpandWed.size() >= 4) {
//                                    lunchListWed.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_lunch.setAdapter(new AddLunch(lunchListWed, getApplicationContext(), addButtonListenerChart));
//                                add_lunch.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                lunch_list.setAdapter(new AddLunchList(lunchListExpandWed, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                lunch_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("snack")) {
//                                snackListWed.get(Integer.parseInt(position)).setName(name);
//                                snackListWed.get(Integer.parseInt(position)).setCalorie(calorie);
//                                snackListWed.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    snackListExpandWed.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    snackListExpandWed.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (snackListExpandWed.size() >= 4) {
//                                    snackListWed.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_snack.setAdapter(new AddSnack(snackListWed, getApplicationContext(), addButtonListenerChart));
//                                add_snack.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                snack_list.setAdapter(new AddSnackList(snackListExpandWed, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                snack_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("dinner")) {
//                                dinnerListWed.get(Integer.parseInt(position)).setName(name);
//                                dinnerListWed.get(Integer.parseInt(position)).setCalorie(calorie);
//                                dinnerListWed.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    dinnerListExpandWed.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    dinnerListExpandWed.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (dinnerListExpandWed.size() >= 4) {
//                                    dinnerListWed.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_dinner.setAdapter(new AddDinner(dinnerListWed, getApplicationContext(), addButtonListenerChart));
//                                add_dinner.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                dinner_list.setAdapter(new AddDinnerList(dinnerListExpandWed, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                dinner_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                        }
//                        if (thur.isChecked()) {
//                            if (meal.equals("breakfast")) {
//                                breakfastListThur.get(Integer.parseInt(position)).setName(name);
//                                breakfastListThur.get(Integer.parseInt(position)).setCalorie(calorie);
//                                breakfastListThur.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    breakfastListExpandThur.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    breakfastListExpandThur.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (breakfastListExpandThur.size() >= 4) {
//                                    breakfastListThur.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_breakfast.setAdapter(new AddBreakfast(breakfastListThur, getApplicationContext(), addButtonListenerChart));
//                                add_breakfast.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                breakfast_list.setAdapter(new AddBreakfastList(breakfastListExpandThur, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                breakfast_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("lunch")) {
//                                lunchListThur.get(Integer.parseInt(position)).setName(name);
//                                lunchListThur.get(Integer.parseInt(position)).setCalorie(calorie);
//                                lunchListThur.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    lunchListExpandThur.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    lunchListExpandThur.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (lunchListExpandThur.size() >= 4) {
//                                    lunchListThur.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_lunch.setAdapter(new AddLunch(lunchListThur, getApplicationContext(), addButtonListenerChart));
//                                add_lunch.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                lunch_list.setAdapter(new AddLunchList(lunchListExpandThur, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                lunch_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("snack")) {
//                                snackListThur.get(Integer.parseInt(position)).setName(name);
//                                snackListThur.get(Integer.parseInt(position)).setCalorie(calorie);
//                                snackListThur.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    snackListExpandThur.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    snackListExpandThur.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (snackListExpandThur.size() >= 4) {
//                                    snackListThur.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_snack.setAdapter(new AddSnack(snackListThur, getApplicationContext(), addButtonListenerChart));
//                                add_snack.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                snack_list.setAdapter(new AddSnackList(snackListExpandThur, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                snack_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("dinner")) {
//                                dinnerListThur.get(Integer.parseInt(position)).setName(name);
//                                dinnerListThur.get(Integer.parseInt(position)).setCalorie(calorie);
//                                dinnerListThur.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    dinnerListExpandThur.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    dinnerListExpandThur.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (dinnerListExpandThur.size() >= 4) {
//                                    dinnerListThur.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_dinner.setAdapter(new AddDinner(dinnerListThur, getApplicationContext(), addButtonListenerChart));
//                                add_dinner.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                dinner_list.setAdapter(new AddDinnerList(dinnerListExpandThur, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                dinner_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                        }
//                        if (fri.isChecked()) {
//                            if (meal.equals("breakfast")) {
//                                breakfastListFri.get(Integer.parseInt(position)).setName(name);
//                                breakfastListFri.get(Integer.parseInt(position)).setCalorie(calorie);
//                                breakfastListFri.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    breakfastListExpandFri.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    breakfastListExpandFri.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (breakfastListExpandFri.size() >= 4) {
//                                    breakfastListFri.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_breakfast.setAdapter(new AddBreakfast(breakfastListFri, getApplicationContext(), addButtonListenerChart));
//                                add_breakfast.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                breakfast_list.setAdapter(new AddBreakfastList(breakfastListExpandFri, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                breakfast_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("lunch")) {
//                                lunchListFri.get(Integer.parseInt(position)).setName(name);
//                                lunchListFri.get(Integer.parseInt(position)).setCalorie(calorie);
//                                lunchListFri.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    lunchListExpandFri.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    lunchListExpandFri.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (lunchListExpandFri.size() >= 4) {
//                                    lunchListFri.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_lunch.setAdapter(new AddLunch(lunchListFri, getApplicationContext(), addButtonListenerChart));
//                                add_lunch.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                lunch_list.setAdapter(new AddLunchList(lunchListExpandFri, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                lunch_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("snack")) {
//                                Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
//                                snackListFri.get(Integer.parseInt(position)).setName(name);
//                                snackListFri.get(Integer.parseInt(position)).setCalorie(calorie);
//                                snackListFri.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    snackListExpandFri.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
//                                    snackListExpandFri.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (snackListExpandFri.size() >= 4) {
//                                    snackListFri.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_snack.setAdapter(new AddSnack(snackListFri, getApplicationContext(), addButtonListenerChart));
//                                add_snack.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                snack_list.setAdapter(new AddSnackList(snackListExpandFri, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                snack_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("dinner")) {
//                                Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
//                                dinnerListFri.get(Integer.parseInt(position)).setName(name);
//                                dinnerListFri.get(Integer.parseInt(position)).setCalorie(calorie);
//                                dinnerListFri.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    dinnerListExpandFri.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
//                                    dinnerListExpandFri.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (dinnerListExpandFri.size() >= 4) {
//                                    dinnerListSat.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_dinner.setAdapter(new AddDinner(dinnerListFri, getApplicationContext(), addButtonListenerChart));
//                                add_dinner.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                dinner_list.setAdapter(new AddDinnerList(dinnerListExpandFri, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                dinner_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                        }
//                        if (sat.isChecked()) {
//                            if (meal.equals("breakfast")) {
//                                Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
//                                breakfastListSat.get(Integer.parseInt(position)).setName(name);
//                                breakfastListSat.get(Integer.parseInt(position)).setCalorie(calorie);
//                                breakfastListSat.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    breakfastListExpandSat.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
//                                    breakfastListExpandSat.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (breakfastListExpandSat.size() >= 4) {
//                                    breakfastListSat.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_breakfast.setAdapter(new AddBreakfast(breakfastListSat, getApplicationContext(), addButtonListenerChart));
//                                add_breakfast.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                breakfast_list.setAdapter(new AddBreakfastList(breakfastListExpandSat, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                breakfast_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("lunch")) {
//                                Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
//                                lunchListSat.get(Integer.parseInt(position)).setName(name);
//                                lunchListSat.get(Integer.parseInt(position)).setCalorie(calorie);
//                                lunchListSat.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    lunchListExpandSat.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
//                                    lunchListExpandSat.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (lunchListExpandSat.size() >= 4) {
//                                    lunchListSat.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_lunch.setAdapter(new AddLunch(lunchListSat, getApplicationContext(), addButtonListenerChart));
//                                add_lunch.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                lunch_list.setAdapter(new AddLunchList(lunchListExpandSat, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                lunch_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("snack")) {
//                                Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
//                                snackListSat.get(Integer.parseInt(position)).setName(name);
//                                snackListSat.get(Integer.parseInt(position)).setCalorie(calorie);
//                                snackListSat.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    snackListExpandSat.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
//                                    snackListExpandSat.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (snackListExpandSat.size() >= 4) {
//                                    snackListSat.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_snack.setAdapter(new AddSnack(snackListSat, getApplicationContext(), addButtonListenerChart));
//                                add_snack.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                snack_list.setAdapter(new AddSnackList(snackListExpandSat, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                snack_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                            if (meal.equals("dinner")) {
//                                Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
//                                dinnerListSat.get(Integer.parseInt(position)).setName(name);
//                                dinnerListSat.get(Integer.parseInt(position)).setCalorie(calorie);
//                                dinnerListSat.get(Integer.parseInt(position)).setPhoto(photo);
//                                try {
//                                    dinnerListExpandSat.set(Integer.parseInt(position), new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                } catch (Exception ex) {
//                                    Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
//                                    dinnerListExpandSat.add(new ModelForFood(name, calorie, description, nutrients, ingredients, photo, "", preparation, quantity));
//                                }
//                                if (dinnerListExpandSat.size() >= 4) {
//                                    dinnerListSat.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
//                                }
//                                add_dinner.setAdapter(new AddDinner(dinnerListSat, getApplicationContext(), addButtonListenerChart));
//                                add_dinner.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//                                dinner_list.setAdapter(new AddDinnerList(dinnerListExpandSat, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
//                                dinner_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                            }
//                        }
                    }
                }
            });

    private void addToDatabase(String name, String description, String meal, String position, String imageEncode) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("h.mm.ss a");

        String date = dateFormat.format(calendar.getTime());
        String time = timeFormat.format(calendar.getTime());

        String addMealUrl = String.format("%sgetMeal.php",DataFromDatabase.ipConfig);

        StringRequest addMealToDbRequest = new StringRequest(Request.Method.POST, addMealUrl,
                response -> Log.d("meal_main", response),
                error -> Log.e("meal_main", error.toString())
        ) {
            @NotNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();

                data.put("name", name);
                data.put("description", description);
                data.put("image", imageEncode);
                data.put("date", date);
                data.put("time", time);
                data.put("meal", meal);
                data.put("clientID", DataFromDatabase.clientuserID);
                data.put("position", position);

                return data;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(addMealToDbRequest);
    }

    private String url = String.format("%sgetMeal.php",DataFromDatabase.ipConfig);

    @SuppressLint("UseSupportActionBar")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_tracker_main);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                finish();
                startActivity(new Intent(getApplicationContext(),DashBoardMain.class));
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        breakfastList = new ArrayList<>();
        breakfastListExpand = new ArrayList<>();

        breakfastListMon = new ArrayList<>();
        breakfastListExpandMon = new ArrayList<>();

        breakfastListTue = new ArrayList<>();
        breakfastListExpandTue = new ArrayList<>();

        breakfastListWed = new ArrayList<>();
        breakfastListExpandWed = new ArrayList<>();

        breakfastListThur = new ArrayList<>();
        breakfastListExpandThur = new ArrayList<>();

        breakfastListFri = new ArrayList<>();
        breakfastListExpandFri = new ArrayList<>();

        breakfastListSat = new ArrayList<>();
        breakfastListExpandSat = new ArrayList<>();


        lunchList = new ArrayList<>();
        lunchListExpand = new ArrayList<>();

        lunchListMon = new ArrayList<>();
        lunchListExpandMon = new ArrayList<>();

        lunchListTue = new ArrayList<>();
        lunchListExpandTue = new ArrayList<>();

        lunchListWed = new ArrayList<>();
        lunchListExpandWed = new ArrayList<>();

        lunchListThur = new ArrayList<>();
        lunchListExpandThur = new ArrayList<>();

        lunchListFri = new ArrayList<>();
        lunchListExpandFri = new ArrayList<>();

        lunchListSat = new ArrayList<>();
        lunchListExpandSat = new ArrayList<>();


        snackList = new ArrayList<>();
        snackListExpand = new ArrayList<>();

        snackListMon = new ArrayList<>();
        snackListExpandMon = new ArrayList<>();

        snackListTue = new ArrayList<>();
        snackListExpandTue = new ArrayList<>();

        snackListWed = new ArrayList<>();
        snackListExpandWed = new ArrayList<>();

        snackListThur = new ArrayList<>();
        snackListExpandThur = new ArrayList<>();

        snackListFri = new ArrayList<>();
        snackListExpandFri = new ArrayList<>();

        snackListSat = new ArrayList<>();
        snackListExpandSat = new ArrayList<>();

        dinnerList = new ArrayList<>();
        dinnerListExpand = new ArrayList<>();

        dinnerListMon = new ArrayList<>();
        dinnerListExpandMon = new ArrayList<>();

        dinnerListTue = new ArrayList<>();
        dinnerListExpandTue = new ArrayList<>();

        dinnerListWed = new ArrayList<>();
        dinnerListExpandWed = new ArrayList<>();

        dinnerListThur = new ArrayList<>();
        dinnerListExpandThur = new ArrayList<>();

        dinnerListFri = new ArrayList<>();
        dinnerListExpandFri = new ArrayList<>();

        dinnerListSat = new ArrayList<>();
        dinnerListExpandSat = new ArrayList<>();


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.add_food);
        Drawable photo = getApplicationContext().getResources().getDrawable(R.drawable.add_food);

        for (int i = 0; i < 4; i++) {

            breakfastList.add(new ModelForFood("", "", photo));
            breakfastListMon.add(new ModelForFood("", "", photo));
            breakfastListTue.add(new ModelForFood("", "", photo));
            breakfastListWed.add(new ModelForFood("", "", photo));
            breakfastListThur.add(new ModelForFood("", "", photo));
            breakfastListFri.add(new ModelForFood("", "", photo));
            breakfastListSat.add(new ModelForFood("", "", photo));

            lunchList.add(new ModelForFood("", "", photo));
            lunchListMon.add(new ModelForFood("", "", photo));
            lunchListTue.add(new ModelForFood("", "", photo));
            lunchListWed.add(new ModelForFood("", "", photo));
            lunchListThur.add(new ModelForFood("", "", photo));
            lunchListFri.add(new ModelForFood("", "", photo));
            lunchListSat.add(new ModelForFood("", "", photo));

            snackList.add(new ModelForFood("", "", photo));
            snackListMon.add(new ModelForFood("", "", photo));
            snackListTue.add(new ModelForFood("", "", photo));
            snackListWed.add(new ModelForFood("", "", photo));
            snackListThur.add(new ModelForFood("", "", photo));
            snackListFri.add(new ModelForFood("", "", photo));
            snackListSat.add(new ModelForFood("", "", photo));

            dinnerList.add(new ModelForFood("", "", photo));
            dinnerListMon.add(new ModelForFood("", "", photo));
            dinnerListTue.add(new ModelForFood("", "", photo));
            dinnerListWed.add(new ModelForFood("", "", photo));
            dinnerListThur.add(new ModelForFood("", "", photo));
            dinnerListFri.add(new ModelForFood("", "", photo));
            dinnerListSat.add(new ModelForFood("", "", photo));

        }

        breakfast_card = findViewById(R.id.breakfast_card);
        lunch_card = findViewById(R.id.lunch_card);
        dinner_card = findViewById(R.id.dinner_card);
        snack_card = findViewById(R.id.snack_card);
        breakfast = findViewById(R.id.breakfast);
        lunch = findViewById(R.id.lunch);
        dinner = findViewById(R.id.dinner);
        snack = findViewById(R.id.snacks);

        breakfast_list = findViewById(R.id.breakfast_list);
        lunch_list = findViewById(R.id.lunch_list);
        snack_list = findViewById(R.id.snack_list);
        dinner_list = findViewById(R.id.dinner_list);

        add_breakfast = findViewById(R.id.add_breakfast);
        add_lunch = findViewById(R.id.add_lunch);
        add_dinner = findViewById(R.id.add_dinner);
        add_snack = findViewById(R.id.add_snack);

//        sun = findViewById(R.id.sun);
//        mon = findViewById(R.id.mon);
//        tue = findViewById(R.id.tue);
//        wed = findViewById(R.id.wed);
//        thur = findViewById(R.id.thur);
//        fri = findViewById(R.id.fri);
//        sat = findViewById(R.id.sat);

        Bitmap add = BitmapFactory.decodeResource(getResources(), R.drawable.add_food);

        addButtonListenerChart = new AddButtonListenerChart() {
            @Override
            public void addButtonOnClick(String name, String calorie, String description, String nutrients, String ingredients, Drawable photo, String category, String preparation, String meal) {

            }

            @Override
            public void addButtonOnClick(String position, String meal) {
                Intent intent = new Intent(getApplicationContext(), CameraForMealTracker.class);
                intent.putExtra("position", position);
                intent.putExtra("meal", meal);
                addFood.launch(intent);
            }
        };

        foodDetailsListener = new FoodDetailsListener() {
            @Override
            public void setDetails(int position) {

            }

            @Override
            public void setDetails(String name, String calorie, String description, String nutrients, String ingredients, Drawable photo, String category, String preparation) {
                FoodDetailsDialog foodDetailsDialog = new FoodDetailsDialog(name, calorie, description, nutrients, ingredients, photo, category, preparation);
                foodDetailsDialog.show(getSupportFragmentManager(), "FoodDetails");
            }
        };

        item_overview_breakfast = findViewById(R.id.item_overview_breakfast);
        item_overview_lunch = findViewById(R.id.item_overview_lunch);
        item_overview_snack = findViewById(R.id.item_overview_snacks);
        item_overview_dinner = findViewById(R.id.item_overview_dinner);

        setItemOverview(add_breakfast,add_lunch,add_snack,add_dinner,breakfastList,lunchList,snackList,dinnerList);

//        days = findViewById(R.id.days);

//        days.setOnCheckedChangeListener((group, checkedId) -> {
//            if (checkedId == R.id.sun) {
//                sun.setBackgroundColor(Color.parseColor("#1D8BF1"));
//                sun.setTextColor(getResources().getColor(R.color.white));
//                mon.setBackgroundColor(getResources().getColor(R.color.white));
//                mon.setTextColor(getResources().getColor(R.color.blue));
//                tue.setBackgroundColor(Color.WHITE);
//                tue.setTextColor(Color.parseColor("#1D8BF1"));
//                wed.setBackgroundColor(Color.WHITE);
//                wed.setTextColor(Color.parseColor("#1D8BF1"));
//                thur.setBackgroundColor(Color.WHITE);
//                thur.setTextColor(Color.parseColor("#1D8BF1"));
//                fri.setBackgroundColor(Color.WHITE);
//                fri.setTextColor(Color.parseColor("#1D8BF1"));
//                sat.setBackgroundColor(Color.WHITE);
//                sat.setTextColor(Color.parseColor("#1D8BF1"));
//
//                setItemOverview(add_breakfast,add_lunch,add_snack,add_dinner,breakfastList,lunchList,snackList,dinnerList);
//
//                setItemList(breakfast_list,lunch_list,snack_list,dinner_list,breakfastListExpand,lunchListExpand,snackListExpand,dinnerListExpand);
//
//            }
//            if (checkedId == R.id.mon) {
//                sun.setBackgroundColor(Color.WHITE);
//                sun.setTextColor(Color.parseColor("#1D8BF1"));
//                mon.setBackgroundColor(Color.parseColor("#1D8BF1"));
//                mon.setTextColor(Color.WHITE);
//                tue.setBackgroundColor(Color.WHITE);
//                tue.setTextColor(Color.parseColor("#1D8BF1"));
//                wed.setBackgroundColor(Color.WHITE);
//                wed.setTextColor(Color.parseColor("#1D8BF1"));
//                thur.setBackgroundColor(Color.WHITE);
//                thur.setTextColor(Color.parseColor("#1D8BF1"));
//                fri.setBackgroundColor(Color.WHITE);
//                fri.setTextColor(Color.parseColor("#1D8BF1"));
//                sat.setBackgroundColor(Color.WHITE);
//                sat.setTextColor(Color.parseColor("#1D8BF1"));
//
//                setItemOverview(add_breakfast,add_lunch,add_snack,add_dinner,breakfastListMon,lunchListMon,snackListMon,dinnerListMon);
//                setItemList(breakfast_list,lunch_list,snack_list,dinner_list,breakfastListExpandMon,lunchListExpandMon,snackListExpandMon,dinnerListExpandMon);
//            }
//            if (checkedId == R.id.tue) {
//                sun.setBackgroundColor(Color.WHITE);
//                sun.setTextColor(Color.parseColor("#1D8BF1"));
//                mon.setBackgroundColor(Color.WHITE);
//                mon.setTextColor(getResources().getColor(R.color.blue));
//                tue.setBackgroundColor(Color.parseColor("#1D8BF1"));
//                tue.setTextColor(Color.WHITE);
//                wed.setBackgroundColor(Color.WHITE);
//                wed.setTextColor(Color.parseColor("#1D8BF1"));
//                thur.setBackgroundColor(Color.WHITE);
//                thur.setTextColor(Color.parseColor("#1D8BF1"));
//                fri.setBackgroundColor(Color.WHITE);
//                fri.setTextColor(Color.parseColor("#1D8BF1"));
//                sat.setBackgroundColor(Color.WHITE);
//                sat.setTextColor(Color.parseColor("#1D8BF1"));
//
//                setItemOverview(add_breakfast,add_lunch,add_snack,add_dinner,breakfastListTue,lunchListTue,snackListTue,dinnerListTue);
//                setItemList(breakfast_list,lunch_list,snack_list,dinner_list,breakfastListExpand,lunchListExpandTue,snackListExpandTue,dinnerListExpandTue);
//
//            }
//            if (checkedId == R.id.wed) {
//                sun.setBackgroundColor(Color.WHITE);
//                sun.setTextColor(Color.parseColor("#1D8BF1"));
//                mon.setBackgroundColor(Color.WHITE);
//                mon.setTextColor(getResources().getColor(R.color.blue));
//                tue.setBackgroundColor(Color.WHITE);
//                tue.setTextColor(Color.parseColor("#1D8BF1"));
//                wed.setBackgroundColor(Color.parseColor("#1D8BF1"));
//                wed.setTextColor(Color.WHITE);
//                thur.setBackgroundColor(Color.WHITE);
//                thur.setTextColor(Color.parseColor("#1D8BF1"));
//                fri.setBackgroundColor(Color.WHITE);
//                fri.setTextColor(Color.parseColor("#1D8BF1"));
//                sat.setBackgroundColor(Color.WHITE);
//                sat.setTextColor(Color.parseColor("#1D8BF1"));
//
//                setItemOverview(add_breakfast,add_lunch,add_snack,add_dinner,breakfastListWed,lunchListWed,snackListWed,dinnerListWed);
//                setItemList(breakfast_list,lunch_list,snack_list,dinner_list,breakfastListExpandWed,lunchListExpandWed,snackListExpandWed,dinnerListExpandWed);
//
//            }
//            if (checkedId == R.id.thur) {
//                sun.setBackgroundColor(Color.WHITE);
//                sun.setTextColor(Color.parseColor("#1D8BF1"));
//                mon.setBackgroundColor(Color.WHITE);
//                mon.setTextColor(getResources().getColor(R.color.blue));
//                tue.setBackgroundColor(Color.WHITE);
//                tue.setTextColor(Color.parseColor("#1D8BF1"));
//                wed.setBackgroundColor(Color.WHITE);
//                wed.setTextColor(Color.parseColor("#1D8BF1"));
//                thur.setBackgroundColor(Color.parseColor("#1D8BF1"));
//                thur.setTextColor(Color.WHITE);
//                fri.setBackgroundColor(Color.WHITE);
//                fri.setTextColor(Color.parseColor("#1D8BF1"));
//                sat.setBackgroundColor(Color.WHITE);
//                sat.setTextColor(Color.parseColor("#1D8BF1"));
//
//                setItemOverview(add_breakfast,add_lunch,add_snack,add_dinner,breakfastListThur,lunchListThur,snackListThur,dinnerListThur);
//                setItemList(breakfast_list,lunch_list,snack_list,dinner_list,breakfastListExpandThur,lunchListExpandThur,snackListExpandThur,dinnerListExpandThur);
//
//            }
//            if (checkedId == R.id.fri) {
//                sun.setBackgroundColor(Color.WHITE);
//                sun.setTextColor(Color.parseColor("#1D8BF1"));
//                mon.setBackgroundColor(Color.WHITE);
//                mon.setTextColor(getResources().getColor(R.color.blue));
//                tue.setBackgroundColor(Color.WHITE);
//                tue.setTextColor(Color.parseColor("#1D8BF1"));
//                wed.setBackgroundColor(Color.WHITE);
//                wed.setTextColor(Color.parseColor("#1D8BF1"));
//                thur.setBackgroundColor(Color.WHITE);
//                thur.setTextColor(Color.parseColor("#1D8BF1"));
//                fri.setBackgroundColor(Color.parseColor("#1D8BF1"));
//                fri.setTextColor(Color.WHITE);
//                sat.setBackgroundColor(Color.WHITE);
//                sat.setTextColor(Color.parseColor("#1D8BF1"));
//
//                setItemOverview(add_breakfast,add_lunch,add_snack,add_dinner,breakfastListFri,lunchListFri,snackListFri,dinnerListFri);
//                setItemList(breakfast_list,lunch_list,snack_list,dinner_list,breakfastListExpandFri,lunchListExpandFri,snackListExpandFri,dinnerListExpandFri);
//
//            }
//            if (checkedId == R.id.sat) {
//                sun.setBackgroundColor(Color.WHITE);
//                sun.setTextColor(Color.parseColor("#1D8BF1"));
//                mon.setBackgroundColor(Color.WHITE);
//                mon.setTextColor(getResources().getColor(R.color.blue));
//                tue.setBackgroundColor(Color.WHITE);
//                tue.setTextColor(Color.parseColor("#1D8BF1"));
//                wed.setBackgroundColor(Color.WHITE);
//                wed.setTextColor(Color.parseColor("#1D8BF1"));
//                thur.setBackgroundColor(Color.WHITE);
//                thur.setTextColor(Color.parseColor("#1D8BF1"));
//                fri.setBackgroundColor(Color.WHITE);
//                fri.setTextColor(Color.parseColor("#1D8BF1"));
//                sat.setBackgroundColor(Color.parseColor("#1D8BF1"));
//                sat.setTextColor(Color.WHITE);
//
//                setItemOverview(add_breakfast,add_lunch,add_snack,add_dinner,breakfastListSat,lunchListSat,snackListSat,dinnerListSat);
//                setItemList(breakfast_list,lunch_list,snack_list,dinner_list,breakfastListExpandSat,lunchListExpandSat,snackListExpandSat,dinnerListExpandSat);
//
//            }
//        });


        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,response -> {

            System.out.println(response);

            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("meal");
                for (int i = 0;i < jsonArray.length();i++){
                    JSONObject meal = jsonArray.getJSONObject(i);
                    String name = meal.getString("name");
                    String description = meal.getString("description");
                    String mealString = meal.getString("meal");
                    String photoStr = meal.getString("image");
                    int position = meal.getInt("position");
                    System.out.println(name);
                    System.out.println(photoStr);
                    byte[] photoArr = Base64.decode(photoStr,Base64.DEFAULT);
                    Bitmap photoBit = BitmapFactory.decodeByteArray(photoArr, 0, photoArr.length);
                    Drawable photoDraw = new BitmapDrawable(getResources(), photoBit);
                    if (mealString.equals("breakfast")) {
                        System.out.println("inside breakfast");
                        breakfastList.get(position).setName(name);
                        breakfastList.get(position).setPhoto(photoDraw);
                        try {
                            System.out.println("inside try");
                            breakfastListExpand.set(position, new ModelForFood(name, description, photo));
                        } catch (Exception ex) {
                            Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
                                    breakfastListExpand.add(new ModelForFood(name, description, photoDraw));
                        }
                        if (breakfastListExpand.size() >= 4) {
                            breakfastList.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
                        }
                        add_breakfast.setAdapter(new AddBreakfast(breakfastList, getApplicationContext(), addButtonListenerChart));
                        add_breakfast.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                        breakfast_list.setAdapter(new AddBreakfastList(breakfastListExpand, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
                        breakfast_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                    if (mealString.equals("lunch")) {

                        Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
                        lunchList.get(position).setName(name);
                        lunchList.get(position).setPhoto(photoDraw);
                        try {
                            System.out.println("inside try");
                            lunchListExpand.set(position, new ModelForFood(name, description, photo));
                        } catch (Exception ex) {
                            Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
                            lunchListExpand.add(new ModelForFood(name, description, photoDraw));
                        }
                        if (lunchListExpand.size() >= 4) {
                            lunchList.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
                        }
                        add_lunch.setAdapter(new AddLunch(lunchList, getApplicationContext(), addButtonListenerChart));
                        add_lunch.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                        lunch_list.setAdapter(new AddLunchList(lunchListExpand, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
                        lunch_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                    if (meal.equals("snack")) {
                        Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
                        snackList.get(position).setName(name);
//                            snackList.get(i).setCalorie(calorie);
                        snackList.get(position).setPhoto(photo);
                        try {
                            snackListExpand.set(i, new ModelForFood(name, description, photo));
                        } catch (Exception ex) {
                            Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
                            snackListExpand.add(new ModelForFood(name, description, photo));
                        }
                        if (snackListExpand.size() >= 4) {
                            snackList.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
                        }
                        add_snack.setAdapter(new AddSnack(snackList, getApplicationContext(), addButtonListenerChart));
                        add_snack.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                        snack_list.setAdapter(new AddSnackList(snackListExpand, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
                        snack_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                    if (meal.equals("dinner")) {
                        Toast.makeText(Meal_main.this, "This for Morning", Toast.LENGTH_SHORT).show();
                        dinnerList.get(i).setName(name);
                        dinnerList.get(i).setPhoto(photo);
                        try {
                            dinnerListExpand.set(i, new ModelForFood(name, description, photo));
                        } catch (Exception ex) {
                            Toast.makeText(Meal_main.this, "Hi", Toast.LENGTH_SHORT).show();
                            dinnerListExpand.add(new ModelForFood(name, description, photo));
                        }
                        if (dinnerListExpand.size() >= 4) {
                            dinnerList.add(new ModelForFood("", "", getApplicationContext().getResources().getDrawable(R.drawable.add_food)));
                        }
                        add_dinner.setAdapter(new AddDinner(dinnerList, getApplicationContext(), addButtonListenerChart));
                        add_dinner.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                        dinner_list.setAdapter(new AddDinnerList(dinnerListExpand, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
                        dinner_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                }
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            catch (Exception exception){
                Toast.makeText(this, "please wait", Toast.LENGTH_SHORT).show();
            }
        },error -> {

        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("clientID",DataFromDatabase.clientuserID);
                data.put("date", "12 Aug 2022");
                return data;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);



        breakfast.setOnClickListener(v -> {

            breakfast.setChecked(true);
            lunch.setChecked(false);
            dinner.setChecked(false);
            snack.setChecked(false);

            item_overview_breakfast.setVisibility(View.GONE);
            item_overview_lunch.setVisibility(View.VISIBLE);
            item_overview_snack.setVisibility(View.VISIBLE);
            item_overview_dinner.setVisibility(View.VISIBLE);

            breakfast_list.setVisibility(View.VISIBLE);
            lunch_list.setVisibility(View.GONE);
            snack_list.setVisibility(View.GONE);
            dinner_list.setVisibility(View.GONE);
        });

        lunch.setOnClickListener(v -> {
            breakfast.setChecked(false);
            lunch.setChecked(true);
            dinner.setChecked(false);
            snack.setChecked(false);
            item_overview_breakfast.setVisibility(View.VISIBLE);
            item_overview_lunch.setVisibility(View.GONE);
            item_overview_snack.setVisibility(View.VISIBLE);
            item_overview_dinner.setVisibility(View.VISIBLE);

            breakfast_list.setVisibility(View.GONE);
            lunch_list.setVisibility(View.VISIBLE);
            snack_list.setVisibility(View.GONE);
            dinner_list.setVisibility(View.GONE);
        });

        dinner.setOnClickListener(v -> {
            breakfast.setChecked(false);
            lunch.setChecked(false);
            dinner.setChecked(true);
            snack.setChecked(false);
            item_overview_breakfast.setVisibility(View.VISIBLE);
            item_overview_lunch.setVisibility(View.VISIBLE);
            item_overview_snack.setVisibility(View.VISIBLE);
            item_overview_dinner.setVisibility(View.GONE);

            breakfast_list.setVisibility(View.GONE);
            lunch_list.setVisibility(View.GONE);
            snack_list.setVisibility(View.GONE);
            dinner_list.setVisibility(View.VISIBLE);
        });
        snack.setOnClickListener(v -> {
            breakfast.setChecked(false);
            lunch.setChecked(false);
            dinner.setChecked(false);
            snack.setChecked(true);
            item_overview_breakfast.setVisibility(View.GONE);
            item_overview_lunch.setVisibility(View.GONE);
            item_overview_snack.setVisibility(View.VISIBLE);
            item_overview_dinner.setVisibility(View.GONE);

            breakfast_list.setVisibility(View.VISIBLE);
            lunch_list.setVisibility(View.GONE);
            snack_list.setVisibility(View.VISIBLE);
            dinner_list.setVisibility(View.GONE);
        });
    }

    private void setBreakfast(RecyclerView add_breakfast,List<ModelForFood> breakfastList){
        add_breakfast.setAdapter(new AddBreakfast(breakfastList, getApplicationContext(), addButtonListenerChart));
        add_breakfast.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setLunch(RecyclerView add_lunch,List<ModelForFood> lunchList){
        add_lunch.setAdapter(new AddLunch(lunchList, getApplicationContext(), addButtonListenerChart));
        add_lunch.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setSnack(RecyclerView add_snack,List<ModelForFood> snackList){
        add_snack.setAdapter(new AddSnack(snackList, getApplicationContext(), addButtonListenerChart));
        add_snack.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setDinner(RecyclerView add_dinner,List<ModelForFood> dinnerList) {
        add_dinner.setAdapter(new AddDinner(dinnerList, getApplicationContext(), addButtonListenerChart));
        add_dinner.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
    }
    private void setItemOverview(RecyclerView add_breakfast,RecyclerView add_lunch,RecyclerView add_snack,RecyclerView add_dinner,List<ModelForFood> breakfastList,List<ModelForFood> lunchList,List<ModelForFood> snackList,List<ModelForFood> dinnerList){
        setBreakfast(add_breakfast,breakfastList);
        setLunch(add_lunch,lunchList);
        setSnack(add_snack,snackList);
        setDinner(add_dinner,dinnerList);
    }
    private void setItemList(RecyclerView breakfast_list,RecyclerView lunch_list,RecyclerView snack_list,RecyclerView dinner_list,List<ModelForFood> breakfastListExpand,List<ModelForFood> lunchListExpand,List<ModelForFood> snackListExpand,List<ModelForFood> dinnerListExpand){

        breakfast_list.setAdapter(new AddBreakfastList(breakfastListExpand, getApplicationContext(), addButtonListenerChart, foodDetailsListener));
        breakfast_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        lunch_list.setAdapter(new AddLunch(lunchListExpand, getApplicationContext(), addButtonListenerChart));
        lunch_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        snack_list.setAdapter(new AddSnack(snackListExpand, getApplicationContext(), addButtonListenerChart));
        snack_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        snack_list.setAdapter(new AddBreakfast(dinnerListExpand, getApplicationContext(), addButtonListenerChart));
        snack_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}