package com.example.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Subscription2 extends AppCompatActivity {

    ImageButton btnclose, btnup,btndown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription2);

        btnclose = findViewById(R.id.btnclose);
        btnup = findViewById(R.id.btnup);
        btndown = findViewById(R.id.btndown);

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icl = new Intent(Subscription2.this, DashBoardMain.class);
                startActivity(icl);
            }
        });

        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iup = new Intent(Subscription2.this, Subscription1.class);
                startActivity(iup);
            }
        });

        btndown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent idw = new Intent(Subscription2.this, Subscription3.class);
                startActivity(idw);
            }
        });
    }
}