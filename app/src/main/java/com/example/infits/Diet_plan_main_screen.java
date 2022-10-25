package com.example.infits;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Diet_plan_main_screen extends AppCompatActivity {


    String url = String.format("%sdiet_chart_client_side.php",DataFromDatabase.ipConfig);

    RadioGroup days;

    RadioButton sun, mon, tue, wed, thur, fri, sat;

    ImageView btnBack;

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

    RecyclerView breakfast_list,lunch_list,snack_list,dinner_list,add_breakfast,add_lunch,add_dinner,add_snack;

    AddButtonListenerChart addButtonListenerChart;

    public static FoodDetailsListener foodDetailsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan_main_screen);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                finish();
                startActivity(new Intent(getApplicationContext(),DashBoardMain.class));
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

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

        LinearLayout sun = findViewById(R.id.sunday);
        TextView sunText = findViewById(R.id.sunday_date);
        LinearLayout mon = findViewById(R.id.monday);
        TextView monText = findViewById(R.id.monday_date);
        LinearLayout tues = findViewById(R.id.tuesday);
        TextView tuesText = findViewById(R.id.tuesday_date);
        LinearLayout wed = findViewById(R.id.wednesday);
        TextView wedText = findViewById(R.id.wednesday_date);
        LinearLayout thurs = findViewById(R.id.thursday);
        TextView thursText = findViewById(R.id.thursday_date);
        LinearLayout fri = findViewById(R.id.friday);
        TextView friText = findViewById(R.id.friday_date);
        LinearLayout saturday = findViewById(R.id.saturday);
        TextView saturdayText = findViewById(R.id.saturday_date);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd");
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        String[] dates = new String[7];
        for(int i = 0; i < 7; i++){
            dates[i] = format.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        sunText.setText(dates[0]);
        monText.setText(dates[1]);
        tuesText.setText(dates[2]);
        wedText.setText(dates[3]);
        thursText.setText(dates[4]);
        friText.setText(dates[5]);
        saturdayText.setText(dates[6]);

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
            public void addButtonOnClick(String name, String calorie, String description, String nutrients, String ingredients, Drawable photo, String category, String preparation,String meal) {

            }

            @Override
            public void addButtonOnClick(String position,String meal) {

            }
        };

        foodDetailsListener = new FoodDetailsListener() {
            @Override
            public void setDetails(int position) {

            }

            @Override
            public void setDetails(String name, String calorie, String description, String nutrients, String ingredients, Drawable photo, String category, String preparation) {
                FoodDetailsDialog foodDetailsDialog = new FoodDetailsDialog(name,calorie,description,nutrients,ingredients,photo,category,preparation);
                foodDetailsDialog.show(getSupportFragmentManager(),"FoodDetails");
            }
        };

        item_overview_breakfast = findViewById(R.id.item_overview_breakfast);
        item_overview_lunch = findViewById(R.id.item_overview_lunch);
        item_overview_snack = findViewById(R.id.item_overview_snacks);
        item_overview_dinner = findViewById(R.id.item_overview_dinner);

//        days = findViewById(R.id.days);

        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        switch (currentDay) {
            case Calendar.SUNDAY:
                getDietChart("sunday");
                sunText.setTextColor(Color.parseColor("#ffffff"));
                sunText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.date_bg));
                break;
            case Calendar.MONDAY:
                getDietChart("monday");
                monText.setTextColor(Color.parseColor("#ffffff"));
                monText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.date_bg));
                break;
            case Calendar.TUESDAY:
                getDietChart("tuesday");
                tuesText.setTextColor(Color.parseColor("#ffffff"));
                tuesText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.date_bg));
                break;
            case Calendar.WEDNESDAY:
                getDietChart("wednesday");
                wedText.setTextColor(Color.parseColor("#ffffff"));
                wedText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.date_bg));
                break;
            case Calendar.THURSDAY:
                getDietChart("thursday");
                thursText.setTextColor(Color.parseColor("#ffffff"));
                thursText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.date_bg));
                break;
            case Calendar.FRIDAY:
                getDietChart("friday");
                friText.setTextColor(Color.parseColor("#ffffff"));
                friText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.date_bg));
                break;
            case Calendar.SATURDAY:
                getDietChart("saturday");
                saturdayText.setTextColor(Color.parseColor("#ffffff"));
                saturdayText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.date_bg));
                break;
        }

        sun.setOnClickListener(v -> {
            setNullAdapter();
            getDietChart("sunday");

            sunText.setTextColor(Color.parseColor("#ffffff"));
            sunText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.date_bg));

            monText.setTextColor(Color.parseColor("#000000"));
            monText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            tuesText.setTextColor(Color.parseColor("#000000"));
            tuesText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            wedText.setTextColor(Color.parseColor("#000000"));
            wedText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            thursText.setTextColor(Color.parseColor("#000000"));
            thursText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            friText.setTextColor(Color.parseColor("#000000"));
            friText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            saturdayText.setTextColor(Color.parseColor("#000000"));
            saturdayText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));
        });

        mon.setOnClickListener(v -> {
            setNullAdapter();
            getDietChart("monday");

            sunText.setTextColor(Color.parseColor("#000000"));
            sunText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            monText.setTextColor(Color.parseColor("#ffffff"));
            monText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.date_bg));

            tuesText.setTextColor(Color.parseColor("#000000"));
            tuesText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            wedText.setTextColor(Color.parseColor("#000000"));
            wedText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            thursText.setTextColor(Color.parseColor("#000000"));
            thursText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            friText.setTextColor(Color.parseColor("#000000"));
            friText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            saturdayText.setTextColor(Color.parseColor("#000000"));
            saturdayText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));
        });

        tues.setOnClickListener(v -> {
            setNullAdapter();
            getDietChart("tuesday");

            sunText.setTextColor(Color.parseColor("#000000"));
            sunText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            monText.setTextColor(Color.parseColor("#000000"));
            monText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            tuesText.setTextColor(Color.parseColor("#ffffff"));
            tuesText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.date_bg));

            wedText.setTextColor(Color.parseColor("#000000"));
            wedText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            thursText.setTextColor(Color.parseColor("#000000"));
            thursText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            friText.setTextColor(Color.parseColor("#000000"));
            friText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            saturdayText.setTextColor(Color.parseColor("#000000"));
            saturdayText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));
        });

        wed.setOnClickListener(v -> {
            setNullAdapter();
            getDietChart("wednesday");

            sunText.setTextColor(Color.parseColor("#000000"));
            sunText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            monText.setTextColor(Color.parseColor("#000000"));
            monText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            tuesText.setTextColor(Color.parseColor("#000000"));
            tuesText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            wedText.setTextColor(Color.parseColor("#ffffff"));
            wedText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.date_bg));

            thursText.setTextColor(Color.parseColor("#000000"));
            thursText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            friText.setTextColor(Color.parseColor("#000000"));
            friText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            saturdayText.setTextColor(Color.parseColor("#000000"));
            saturdayText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));
        });

        thurs.setOnClickListener(v -> {
            setNullAdapter();
            getDietChart("thursday");

            sunText.setTextColor(Color.parseColor("#000000"));
            sunText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            monText.setTextColor(Color.parseColor("#000000"));
            monText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            tuesText.setTextColor(Color.parseColor("#000000"));
            tuesText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            wedText.setTextColor(Color.parseColor("#000000"));
            wedText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            thursText.setTextColor(Color.parseColor("#ffffff"));
            thursText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.date_bg));

            friText.setTextColor(Color.parseColor("#000000"));
            friText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            saturdayText.setTextColor(Color.parseColor("#000000"));
            saturdayText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));
        });

        fri.setOnClickListener(v -> {
            setNullAdapter();
            getDietChart("friday");

            sunText.setTextColor(Color.parseColor("#000000"));
            sunText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            monText.setTextColor(Color.parseColor("#000000"));
            monText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            tuesText.setTextColor(Color.parseColor("#000000"));
            tuesText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            wedText.setTextColor(Color.parseColor("#000000"));
            wedText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            thursText.setTextColor(Color.parseColor("#000000"));
            thursText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            friText.setTextColor(Color.parseColor("#ffffff"));
            friText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.date_bg));

            saturdayText.setTextColor(Color.parseColor("#000000"));
            saturdayText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));
        });

        saturday.setOnClickListener(v -> {
            setNullAdapter();
            getDietChart("saturday");

            sunText.setTextColor(Color.parseColor("#000000"));
            sunText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            monText.setTextColor(Color.parseColor("#000000"));
            monText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            tuesText.setTextColor(Color.parseColor("#000000"));
            tuesText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            wedText.setTextColor(Color.parseColor("#000000"));
            wedText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            thursText.setTextColor(Color.parseColor("#000000"));
            thursText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            friText.setTextColor(Color.parseColor("#000000"));
            friText.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));

            saturdayText.setTextColor(Color.parseColor("#ffffff"));
            saturdayText.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.date_bg));
        });

//        days.setOnCheckedChangeListener((group, checkedId) -> {
//            if (checkedId == R.id.sun) {
//
//                setNullAdapter();
//                getDietChart("sunday");
//
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
//            }
//            if (checkedId == R.id.mon) {
//
//                setNullAdapter();
//                getDietChart("monday");
//
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
//            }
//            if (checkedId == R.id.tue) {
//
//                setNullAdapter();
//                getDietChart("tuesday");
//
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
//            }
//            if (checkedId == R.id.wed) {
//                setNullAdapter();
//                getDietChart("wednesday");
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
//            }
//            if (checkedId == R.id.thur) {
//
//                setNullAdapter();
//                getDietChart("thursday");
//
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
//                setNullAdapter();
//                getDietChart("friday");
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
//                setNullAdapter();
//                getDietChart("saturday");
//            }
//        });


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
            item_overview_breakfast.setVisibility(View.VISIBLE);
            item_overview_lunch.setVisibility(View.VISIBLE);
            item_overview_snack.setVisibility(View.GONE);
            item_overview_dinner.setVisibility(View.VISIBLE);

            breakfast_list.setVisibility(View.GONE);
            lunch_list.setVisibility(View.GONE);
            snack_list.setVisibility(View.VISIBLE);
            dinner_list.setVisibility(View.GONE);
        });

        breakfast_card.setOnClickListener(v -> {
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
        lunch_card.setOnClickListener(v -> {
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
        dinner_card.setOnClickListener(v -> {
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
        snack_card.setOnClickListener(v -> {
            breakfast.setChecked(false);
            lunch.setChecked(false);
            dinner.setChecked(false);
            snack.setChecked(true);
            item_overview_breakfast.setVisibility(View.VISIBLE);
            item_overview_lunch.setVisibility(View.VISIBLE);
            item_overview_snack.setVisibility(View.GONE);
            item_overview_dinner.setVisibility(View.VISIBLE);

            breakfast_list.setVisibility(View.GONE);
            lunch_list.setVisibility(View.GONE);
            snack_list.setVisibility(View.VISIBLE);
            dinner_list.setVisibility(View.GONE);
        });
    }

    private void setNullAdapter() {
        add_breakfast.setAdapter(null);
        breakfast_list.setAdapter(null);

        add_lunch.setAdapter(null);
        lunch_list.setAdapter(null);

        add_snack.setAdapter(null);
        snack_list.setAdapter(null);

        add_dinner.setAdapter(null);
        dinner_list.setAdapter(null);
    }

    private void getDietChart(String day) {
        StringRequest sunday = new StringRequest(Request.Method.POST,url,response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
//                JSONObject jsonObjectSunday = jsonObject.getJSONObject("sunday");
                JSONArray jsonArray = jsonObject.getJSONArray("breakfast");
                breakfastList.removeAll(breakfastList);
                breakfastListExpand.removeAll(breakfastListExpand);
                System.out.println(jsonArray.length());
                for(int i = 0;i<jsonArray.length();i++){
                    JSONObject food = jsonArray.getJSONObject(i);
                    String name = food.getString("name");
                    String calorie = food.getString("calorie");
                    String description = food.getString("description");
                    String nutrients = food.getString("nutrients");
                    String ingredients = food.getString("ingredients");
                    String photoString = food.getString("photo");
                    byte[] photoByte = Base64.decode(photoString,0);
                    Drawable photoDraw = new BitmapDrawable(getResources(),BitmapFactory.decodeByteArray(photoByte,0,photoByte.length));
                    String preparation = food.getString("preparation");
                    String quantity = food.getString("quantity");

                    System.out.println(name);

                    breakfastList.add(new ModelForFood(name,calorie,description,nutrients,ingredients,photoDraw,"",preparation,quantity));
                    breakfastListExpand.add(new ModelForFood(name,calorie,description,nutrients,ingredients,photoDraw,"",preparation,quantity));

                }
                add_breakfast.setAdapter(new AddBreakfast(breakfastList,getApplicationContext(),addButtonListenerChart));
                add_breakfast.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

                breakfast_list.setAdapter(new AddBreakfastList(breakfastListExpand,getApplicationContext(),addButtonListenerChart,foodDetailsListener));
                breakfast_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("lunch");
                lunchList.removeAll(lunchList);
                lunchListExpand.removeAll(lunchListExpand);
                for(int i = 0;i<jsonArray.length();i++){
                    JSONObject food = jsonArray.getJSONObject(i);
                    String name = food.getString("name");
                    String calorie = food.getString("calorie");
                    String description = food.getString("description");
                    String nutrients = food.getString("nutrients");
                    String ingredients = food.getString("ingredients");
                    String photoString = food.getString("photo");
                    byte[] photoByte = Base64.decode(photoString,0);
                    Drawable photoDraw = new BitmapDrawable(getResources(),BitmapFactory.decodeByteArray(photoByte,0,photoByte.length));
                    String preparation = food.getString("preparation");
                    String quantity = food.getString("quantity");

                    lunchList.add(new ModelForFood(name,calorie,description,nutrients,ingredients,photoDraw,"",preparation,quantity));
                    lunchListExpand.add(new ModelForFood(name,calorie,description,nutrients,ingredients,photoDraw,"",preparation,quantity));

                    add_lunch.setAdapter(new AddLunch(lunchList,getApplicationContext(),addButtonListenerChart));
                    add_lunch.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

                    lunch_list.setAdapter(new AddLunchList(lunchListExpand,getApplicationContext(),addButtonListenerChart,foodDetailsListener));
                    lunch_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
                }
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("snack");
                for(int i = 0;i<jsonArray.length();i++){
                    JSONObject food = jsonArray.getJSONObject(i);
                    String name = food.getString("name");
                    String calorie = food.getString("calorie");
                    String description = food.getString("description");
                    String nutrients = food.getString("nutrients");
                    String ingredients = food.getString("ingredients");
                    String photoString = food.getString("photo");
                    byte[] photoByte = Base64.decode(photoString,0);
                    Drawable photoDraw = new BitmapDrawable(getResources(),BitmapFactory.decodeByteArray(photoByte,0,photoByte.length));
                    String preparation = food.getString("preparation");
                    String quantity = food.getString("quantity");

                    snackList.removeAll(snackList);
                    snackListExpand.removeAll(snackListExpand);

                    snackList.add(new ModelForFood(name,calorie,description,nutrients,ingredients,photoDraw,preparation,"quantity"));
                    snackListExpand.add(new ModelForFood(name,calorie,description,nutrients,ingredients,photoDraw,preparation,"quantity"));

                    add_snack.setAdapter(new AddSnack(snackList,getApplicationContext(),addButtonListenerChart));
                    add_snack.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

                    snack_list.setAdapter(new AddSnackList(snackListExpand,getApplicationContext(),addButtonListenerChart,foodDetailsListener));
                    snack_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
                }
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("dinner");
                for(int i = 0;i<jsonArray.length();i++){
                    JSONObject food = jsonArray.getJSONObject(i);
                    String name = food.getString("name");
                    String calorie = food.getString("calorie");
                    String description = food.getString("description");
                    String nutrients = food.getString("nutrients");
                    String ingredients = food.getString("ingredients");
                    String photoString = food.getString("photo");
                    byte[] photoByte = Base64.decode(photoString,0);
                    Drawable photoDraw = new BitmapDrawable(getResources(),BitmapFactory.decodeByteArray(photoByte,0,photoByte.length));
                    String preparation = food.getString("preparation");
                    String quantity = food.getString("quantity");

                    dinnerList.removeAll(dinnerList);
                    dinnerListExpand.removeAll(dinnerListExpand);

                    dinnerList.add(new ModelForFood(name,calorie,description,nutrients,ingredients,photoDraw,preparation,"quantity"));
                    dinnerListExpand.add(new ModelForFood(name,calorie,description,nutrients,ingredients,photoDraw,preparation,"quantity"));

                    add_dinner.setAdapter(new AddDinner(dinnerList,getApplicationContext(),addButtonListenerChart));
                    add_dinner.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

                    dinner_list.setAdapter(new AddDinnerList(dinnerListExpand,getApplicationContext(),addButtonListenerChart,foodDetailsListener));
                    dinner_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
                }
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        },error->{
            System.out.println(error);
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("dietitianID",DataFromDatabase.dietitianuserID);
                data.put("clientID",DataFromDatabase.clientuserID);
                data.put("day",day);
                return data;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(sunday);
    }
}