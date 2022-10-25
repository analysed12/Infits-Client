package com.example.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    public static final String LOGIN_PREFS = "LoginPrefs";

    SharedPreferences sharedPreferences;

    TextView reg, fpass;
    Button loginbtn;
    String passwordStr,usernameStr;
    String url = String.format("%slogin_client.php",DataFromDatabase.ipConfig);
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reg = (TextView) findViewById(R.id.reg);
        fpass = (TextView) findViewById(R.id.fpass);
        loginbtn = (Button) findViewById(R.id.logbtn);

        queue = Volley.newRequestQueue(this);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ireg = new Intent(Login.this, Signup.class);
                startActivity(ireg);
            }
        });

        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ip = new Intent(Login.this, ResetPassword.class);
                startActivity(ip);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username= findViewById(R.id.username);
                EditText password= findViewById(R.id.password);
                usernameStr = username.getText().toString();
                passwordStr = password.getText().toString();

                loginbtn.setClickable(false);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
                    if(response.equals("failure")){
                        Toast.makeText(Login.this,"Login failed",Toast.LENGTH_SHORT).show();
                        loginbtn.setClickable(true);
                    }else{
                        Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_LONG).show();
                        Intent id = new Intent(Login.this, DashBoardMain.class);
                        Log.d("Response Login",response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject object = jsonArray.getJSONObject(0);
                            DataFromDatabase.flag=true;
                            DataFromDatabase.clientuserID  = object.getString("clientuserID");
                            DataFromDatabase.dietitianuserID = object.getString("dietitianuserID");
                            DataFromDatabase.name = object.getString("name");
                            Log.d("name login",DataFromDatabase.name);
                            SharedPreferences loginDetails = getSharedPreferences("loginDetails",MODE_PRIVATE);
                            SharedPreferences.Editor editor = loginDetails.edit();

                            DataFromDatabase.password = object.getString("password");
                            DataFromDatabase.email = object.getString("email");
                            DataFromDatabase.mobile = object.getString("mobile");
                            DataFromDatabase.profilePhoto = object.getString("profilePhoto");
                            DataFromDatabase.location = object.getString("location");
                            DataFromDatabase.age = object.getString("age");
                            DataFromDatabase.gender  = object.getString("gender");
                            DataFromDatabase.weight  = object.getString("weight");
                            DataFromDatabase.height  = object.getString("height");
                            DataFromDatabase.profilePhotoBase = DataFromDatabase.profilePhoto;

                            System.out.println(DataFromDatabase.weight);
                            System.out.println(DataFromDatabase.height);

                            if (object.getString("verification").equals("0")){
                                DataFromDatabase.proUser = false;
                            }
                            if (object.getString("verification").equals("1")){
                                DataFromDatabase.proUser = true;
                            }
                            System.out.println(DataFromDatabase.proUser+" Prouser");
                            byte[] qrimage = Base64.decode(DataFromDatabase.profilePhoto,0);
                            DataFromDatabase.profile = BitmapFactory.decodeByteArray(qrimage,0,qrimage.length);
                            Log.d("Login Screen","client user id = "+ DataFromDatabase.clientuserID);

                            editor.putBoolean("hasLoggedIn", true);
                            editor.putBoolean("flag", true);
                            editor.putString("clientuserID",object.getString("clientuserID"));
                            editor.putString("dietitianuserID",object.getString("dietitianuserID"));
                            editor.putString("name",object.getString("name"));
                            editor.putString("password",object.getString("password"));
                            editor.putString("email",object.getString("email"));
                            editor.putString("mobile",object.getString("mobile"));
                            editor.putString("profilePhoto",object.getString("profilePhoto"));
                            editor.putString("location",object.getString("location"));
                            editor.putString("age",object.getString("age"));
                            editor.putString("gender",object.getString("gender"));
                            editor.putString("weight",object.getString("weight"));
                            editor.putString("height",object.getString("height"));
                            editor.putString("profilePhotoBase",object.getString("profilePhoto"));
                            editor.putBoolean("proUser",DataFromDatabase.proUser);
                            editor.apply();

                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(id);
                    }
                }, error -> {
                    Toast.makeText(Login.this,error.toString().trim(),Toast.LENGTH_SHORT).show();
                    loginbtn.setClickable(true);
                }){
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError{
                        LinkedHashMap<String,String> data = new LinkedHashMap<>();
                        data.put("userID",usernameStr);
                        data.put("password",passwordStr);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });
    }

    private void putDataInPreferences(SharedPreferences.Editor editor) {
        editor.putBoolean("hasLoggedIn", true);

    }
}