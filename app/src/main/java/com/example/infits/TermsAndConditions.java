package com.example.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TermsAndConditions extends AppCompatActivity {

    ImageButton imgback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

        imgback = (ImageButton) findViewById(R.id.imgback);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ib = new Intent(TermsAndConditions.this, Signup.class);
                startActivity(ib);
            }
        });
    }
}