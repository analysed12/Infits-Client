package com.example.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class ScanResult extends AppCompatActivity {

    TextView productName,quantity,fat,calories,carb,protein;

    ImageView image,notFound;

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        Intent intent = getIntent();

        String barcode = intent.getStringExtra("barcode");

        image = findViewById(R.id.image);

        quantity = findViewById(R.id.quantity);

        productName = findViewById(R.id.product_name);

//        fat = findViewById(R.id.fa);

        calories = findViewById(R.id.per_100g_title_energy);

//energy-kcal_serving

        notFound = findViewById(R.id.not_found);

        String url = String.format("https://world.openfoodfacts.org/api/v0/product/%s.json",barcode);
        StringRequest scanResult = new StringRequest(Request.Method.GET,url, response -> {

            System.out.println(response);
            try {
                JSONObject jsonResponse = new JSONObject(response);

                if (jsonResponse.getString("status").equals("1")){
                    JSONObject product = jsonResponse.getJSONObject("product");

                    Toast.makeText(this, jsonResponse.getString("code"), Toast.LENGTH_SHORT).show();
                    Glide.with(getApplicationContext()).load(product.getString("image_url")).into(image);
                    productName.append(" "+product.getString("product_name"));
                    quantity.setText(product.getString("serving_size"));
                    JSONObject nutrients = product.getJSONObject("nutriments");
                    calories.setText(nutrients.getString("energy-kcal_100g"));
                }
                if (jsonResponse.getString("status").equals("0")){
                            notFound.setVisibility(View.VISIBLE);
                }
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        },
                error -> {

                });
        Volley.newRequestQueue(getApplicationContext()).add(scanResult);

//        next = findViewById(R.id.nextbtn);

//        next.setOnClickListener(v->{
//            Intent intentToQuestion = new Intent(getApplicationContext(),QuestionsAfterScan.class);
//            startActivity(intentToQuestion);
//        });

    }
}