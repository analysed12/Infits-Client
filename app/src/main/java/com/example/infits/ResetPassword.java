package com.example.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ResetPassword extends AppCompatActivity {

    ImageView back_login;
    Button sendMailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        back_login = findViewById(R.id.back_login);
        sendMailBtn = findViewById(R.id.sendMailBtn);

        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResetPassword.this, Login.class);
                startActivity(i);
            }
        });

        sendMailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            new SendMail();
//                            System.out.println("Sent");
//                        } catch (Exception e) {
//                            Log.e("SendMail", e.getMessage(), e);
//                        }
//                    }
//                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String emailId = "azarcrackzz@gmail.com";
                        String message = "item + price";
                        SendMail sm = new SendMail(getApplicationContext(), emailId, message);
                    }
                }).start();
            }
        });
    }
}