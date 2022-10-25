package com.example.infits;

import android.app.ActivityManager;
import android.util.Log;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class GraphDataFetech {
    DataPoint weekData(String url){
            String from = "";
            String to = "";
            SimpleDateFormat fromTo = new SimpleDateFormat("yyyy-MM-dd");
            String [] days = new String[7];
            float[] dataPoints= new float[7];
            SimpleDateFormat sdf = new SimpleDateFormat("dd ");
            Calendar cal = Calendar.getInstance();
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                cal.add(Calendar.DAY_OF_YEAR, 0);
                days[0] = sdf.format(cal.getTime());
                for(int i = 0; i< 6; i++){
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    days[i] = sdf.format(cal.getTime());
                }
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
                cal.add(Calendar.DAY_OF_YEAR, -1);
                days[0] = sdf.format(cal.getTime());
                for(int i = 0; i< 6; i++){
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    days[i] = sdf.format(cal.getTime());
                }
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
                cal.add(Calendar.DAY_OF_YEAR, -2);
                days[0] = sdf.format(cal.getTime());
                for(int i = 0; i< 6; i++){
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    days[i] = sdf.format(cal.getTime());
                }
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
                cal.add(Calendar.DAY_OF_YEAR, -3);
                days[0] = (sdf.format(cal.getTime()));
                Log.d("calc",String.valueOf(cal.getTime()));
                for(int i = 0; i< 6; i++){
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    days[i+1] = (sdf.format(cal.getTime()));
                }
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
                cal.add(Calendar.DAY_OF_YEAR, -4);
                days[0] = sdf.format(cal.getTime());
                from = fromTo.format(cal.getTime());
                for(int i = 0; i< 6; i++){
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    days[i] = sdf.format(cal.getTime());
                }
                to = fromTo.format(cal.getTime());
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
                cal.add(Calendar.DAY_OF_YEAR, -5);
                days[0] = sdf.format(cal.getTime());
                for(int i = 0; i< 6; i++){
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    days[i] = sdf.format(cal.getTime());
                }
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                cal.add(Calendar.DAY_OF_YEAR, -6);
                days[0] = sdf.format(cal.getTime());
                for(int i = 0; i< 7; i++){
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    days[i] = sdf.format(cal.getTime());
                }
            }
            StringRequest stringRequest = new StringRequest(Request.Method.GET,url, response -> {
                List<String> allNames = new ArrayList<String>();
                JSONObject jsonResponse = null;
                try {
                    jsonResponse = new JSONObject(response);
                    JSONArray cast = jsonResponse.getJSONArray("steps");
                    for (int i=0; i<cast.length(); i++) {
                        JSONObject actor = cast.getJSONObject(i);
                        String name = actor.getString("steps");
                        allNames.add(name);
                        dataPoints[i] = Float.parseFloat(allNames.get(i))/1000;
                    }
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                            new DataPoint(0,dataPoints[0]),
                            new DataPoint(1,dataPoints[1]),
                            new DataPoint(2,dataPoints[2]),
                            new DataPoint(3,dataPoints[3]),
                            new DataPoint(4,dataPoints[4]),
                            new DataPoint(5,dataPoints[5]),
                            new DataPoint(6,dataPoints[6]),
                    });
//                    StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
//                    staticLabelsFormatter.setHorizontalLabels(days);
//                    staticLabelsFormatter.setVerticalLabels(new String[] {"0", "1000", "2000", "3000","4000","5000","6000","7000","8000"});
            //        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
               //     graph.addSeries(series);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            },error -> {
                Log.d("Data",error.toString().trim());
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return super.getParams();
                }
            };
//            Volley.newRequestQueue(get).add(stringRequest);
        return null;
        }
    }
