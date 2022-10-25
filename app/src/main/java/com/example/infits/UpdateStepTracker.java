package com.example.infits;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdateStepTracker extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String url="http://192.168.9.1/infits/steptracker.php";;
        StringRequest request = new StringRequest(Request.Method.POST,url, response -> {
            if (response.equals("updated")){
                Toast.makeText(context.getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                Log.d("Response",response);
            }
            else{
                Toast.makeText(context.getApplicationContext(), "Not working", Toast.LENGTH_SHORT).show();
                Log.d("Response",response);
            }
        },error -> {
            Toast.makeText(context.getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.format(date);
                Map<String,String> data = new HashMap<>();
                data.put("userID","Azarudeen");
                data.put("dateandtime", String.valueOf(date));
                data.put("distance", "14");
                data.put("avgspeed", "14");
                data.put("calories","34");
                data.put("steps", String.valueOf(FetchTrackerInfos.currentSteps));
                data.put("goal", "5000");
                return data;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
}
