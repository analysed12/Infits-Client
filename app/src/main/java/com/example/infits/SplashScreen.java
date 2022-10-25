package com.example.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;

public class SplashScreen extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences loginDetails = getSharedPreferences("loginDetails",MODE_PRIVATE);
                boolean isLoggedIn = loginDetails.getBoolean("hasLoggedIn", false);

                if(isLoggedIn) {
                    intent = new Intent(SplashScreen.this, DashBoardMain.class);
                    setDataFromDatabase(loginDetails);
                } else {
                    intent = new Intent(SplashScreen.this, Landing1.class);
                }

                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    private void setDataFromDatabase(SharedPreferences prefs) {
        DataFromDatabase.flag = prefs.getBoolean("flag", true);
        DataFromDatabase.clientuserID = prefs.getString("clientuserID", "");
        DataFromDatabase.dietitianuserID = prefs.getString("dietitianuserID", "");
        DataFromDatabase.name = prefs.getString("name", "");
        DataFromDatabase.password = prefs.getString("password", "");
        DataFromDatabase.email = prefs.getString("email", "");
        DataFromDatabase.mobile = prefs.getString("mobile", "");
        DataFromDatabase.profilePhoto = prefs.getString("profilePhoto", "");
        DataFromDatabase.location = prefs.getString("location", "");
        DataFromDatabase.age = prefs.getString("age", "");
        DataFromDatabase.gender = prefs.getString("gender", "");
        DataFromDatabase.weight = prefs.getString("weight", "");
        DataFromDatabase.height = prefs.getString("height", "");
        DataFromDatabase.profilePhotoBase = prefs.getString("profilePhotoBase", "");
        DataFromDatabase.proUser = prefs.getBoolean("proUser", false);
        byte[] qrimage = Base64.decode(DataFromDatabase.profilePhoto,0);
        DataFromDatabase.profile = BitmapFactory.decodeByteArray(qrimage,0,qrimage.length);
    }
}