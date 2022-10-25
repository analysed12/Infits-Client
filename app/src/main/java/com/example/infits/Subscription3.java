package com.example.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Subscription3 extends AppCompatActivity {

    ImageButton btnclose, btnup,btndown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription3);

        btnclose = findViewById(R.id.btnclose);
        btnup = findViewById(R.id.btnup);
        btndown = findViewById(R.id.btndown);

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icl = new Intent(Subscription3.this, DashBoardMain.class);
                startActivity(icl);
            }
        });

        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iup = new Intent(Subscription3.this, Subscription2.class);
                startActivity(iup);
            }
        });

    }
}