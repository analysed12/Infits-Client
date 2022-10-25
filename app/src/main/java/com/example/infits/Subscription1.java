package com.example.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Subscription1 extends AppCompatActivity {

    ImageButton btnclose, btnup,btndown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription1);

        btnclose = findViewById(R.id.btnclose);
        btnup = findViewById(R.id.btnup);
        btndown = findViewById(R.id.btndown);

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icl = new Intent(Subscription1.this, DashBoardMain.class);
                startActivity(icl);
            }
        });


        btndown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent idw = new Intent(Subscription1.this, Subscription2.class);
                startActivity(idw);
            }
        });
    }
}