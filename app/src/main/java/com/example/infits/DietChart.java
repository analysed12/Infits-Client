package com.example.infits;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;

import java.util.Date;


public class DietChart extends AppCompatActivity {

    DayScrollDatePicker dayDatePicker;
    String selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_chart);

        dayDatePicker = findViewById(R.id.dayDatePicker);
        dayDatePicker.setStartDate(1,1,2022);
        dayDatePicker.getSelectedDate(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@Nullable Date date) {
                selectedDay = date.toString();
                Toast.makeText(DietChart.this, selectedDay,Toast.LENGTH_SHORT).show();
            }
        });
    }
}