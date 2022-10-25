package com.example.infits;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    TextView term, memlog;
    Button signbtn;
    RadioButton agreeToCondition;

    String url = String.format("%sregister_client.php",DataFromDatabase.ipConfig);

    EditText fullName,userName,emailID,password,phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        agreeToCondition = findViewById(R.id.agree);
        term = (TextView) findViewById(R.id.term);
        memlog = (TextView) findViewById(R.id.memlog);
        signbtn = (Button) findViewById(R.id.signbtn);
        fullName = findViewById(R.id.new_name);
        userName = findViewById(R.id.new_user_name);
        emailID = findViewById(R.id.new_email);
        password = findViewById(R.id.new_password);
        phoneNo = findViewById(R.id.new_phone_number);

        term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Signup.this, TermsAndConditions.class);
                startActivity(it);
            }
        });

        memlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ilo = new Intent(Signup.this, Login.class);
                startActivity(ilo);
            }
        });

        if (agreeToCondition.isSelected()){
            signbtn.setClickable(true);
        }
        signbtn.setOnClickListener(v -> {

            String userID = userName.getText().toString();
            String passwordStr = password.getText().toString();
            String emailStr = emailID.getText().toString();
            String phoneStr = phoneNo.getText().toString();
            String fullNameStr = fullName.getText().toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST,url, response -> {
                System.out.println(response);
                if (response.equals("success")){
                    Toast.makeText(getApplicationContext(), "Registration completed", Toast.LENGTH_SHORT).show();
                    Intent id = new Intent(getApplicationContext(), Login.class);
                    startActivity(id);
                }
                else{
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                }
            },error -> {
                Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();}){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> data = new HashMap<>();
                    data.put("userID",userID);
                    data.put("password",passwordStr);
                    data.put("email",emailStr);
                    data.put("name",fullNameStr);
                    data.put("phone",phoneStr);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        });
    }
}