package com.example.infits;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EnterFoodDetails extends AppCompatActivity {

    TextView meal,datetime;

    EditText name,description;

    ImageView foodEaten;

    Button saveMeal;

    String url = String.format("%ssaveMeal.php",DataFromDatabase.ipConfig);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_food_details);

        Intent getIntent = getIntent();

        String mealString = getIntent.getStringExtra("meal");

//        String nameString = getIntent.getStringExtra("name");

        byte[] photoArr = getIntent.getByteArrayExtra("photoByte");

        Bitmap foodEatenPhoto = BitmapFactory.decodeByteArray(photoArr,0,photoArr.length);

        foodEaten = findViewById(R.id.food_eaten);

        name = findViewById(R.id.name);

        meal = findViewById(R.id.meal);

        datetime = findViewById(R.id.date_and_time);

        saveMeal = findViewById(R.id.save_meal);

        description = findViewById(R.id.enter_description);

        foodEaten.setImageBitmap(foodEatenPhoto);

        meal.setText(mealString);

        Date date = new Date();

        String dateAndTime = new SimpleDateFormat("d MMM yyyy, h.m a").format(date);

        SimpleDateFormat todayDate = new SimpleDateFormat("d MMM yyyy");

        SimpleDateFormat todayTime = new SimpleDateFormat("h.m a");

        datetime.setText(dateAndTime);

        saveMeal.setOnClickListener(v->{

            StringRequest saveMealToDb = new StringRequest(Request.Method.POST,url,response->{

                System.out.println(response);

            },error -> {

            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    foodEatenPhoto.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                    String base64String = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);

                    String nameString = name.getText().toString();
                    String descriptionString = description.getText().toString();

                    Map<String,String> data = new HashMap<>();

                        String timeString = todayTime.format(date);
                        String dateString = todayDate.format(date);

                    data.put("meal",mealString);
                    data.put("image",base64String);
                    data.put("description",descriptionString);
                    data.put("name",nameString);
                    data.put("date",dateString);
                    data.put("time",timeString);
                    data.put("clientID","Azarudeen");

                    return data;
                }
            };
            Volley.newRequestQueue(getApplicationContext()).add(saveMealToDb);
        });
    }
}