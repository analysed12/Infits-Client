package com.example.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Landing2 extends AppCompatActivity {

    ImageButton b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing2);

        b1=(ImageButton) findViewById(R.id.circleBtn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Landing2.this, Landing3.class);
                startActivity(i);
            }
        });
    }
}