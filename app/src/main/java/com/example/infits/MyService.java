package com.example.infits;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class MyService extends Service implements SensorEventListener {

    SensorManager sensorManager;
    Sensor stepSensor;
    PendingIntent pendingIntent = null;

    Intent intent = new Intent("com.example.infits");

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (stepSensor == null) {
            Log.e("Ser", "No sensor");
        } else {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }

        String input = intent.getStringExtra("inputExtra");

//        PendingIntent pendingIntent = PendingIntent.getActivity(this,
//                0, notificationIntent, 0);


        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

            SharedPreferences sh = getSharedPreferences("DateForSteps", Context.MODE_PRIVATE);

            Date dateObj = new Date();

            String date = sh.getString("date","");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-M-yyyy");

            System.out.println(date);

            System.out.println(simpleDateFormat.format(dateObj));

            if (!date.equals(simpleDateFormat.format(dateObj))) {
                FetchTrackerInfos.previousStep = FetchTrackerInfos.totalSteps;
                System.out.println("Reset");
                SharedPreferences sharedPreferences = getSharedPreferences("DateForSteps", Context.MODE_PRIVATE);
                Date dateForSteps = new Date();

                System.out.println(simpleDateFormat.format(dateForSteps));

                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                myEdit.putString("date", simpleDateFormat.format(dateForSteps));
                myEdit.putBoolean("verified",false);
                myEdit.commit();
            }

            Calendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);

            FetchTrackerInfos.totalSteps = (int) event.values[0];
            FetchTrackerInfos.currentSteps = ((int) FetchTrackerInfos.totalSteps - (int) FetchTrackerInfos.previousStep);

            updateSteps();

            Log.d("step", String.valueOf(FetchTrackerInfos.totalSteps));
            Log.d("stepPre", String.valueOf(FetchTrackerInfos.previousStep));
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                NotificationChannel chan = new NotificationChannel(
                        "MyChannelId",
                        "My Foreground Service",
                        NotificationManager.IMPORTANCE_LOW);
                chan.setLightColor(Color.BLUE);
                chan.setLockscreenVisibility(Notification.VISIBILITY_SECRET);

                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                assert manager != null;
                manager.createNotificationChannel(chan);

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                        this, "MyChannelId");
                Notification notification = notificationBuilder.setOngoing(true)
                        .setSmallIcon(R.mipmap.logo)
                        .setContentTitle(String.valueOf(FetchTrackerInfos.currentSteps))
                        .setPriority(NotificationManager.IMPORTANCE_LOW)
                        .setCategory(Notification.CATEGORY_SERVICE)
                        .setChannelId("MyChannelId")
                        .build();
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID,TAG,
//                            NotificationManager.IMPORTANCE_HIGH);
//                    notificationManager.createNotificationChannel(channel);
//
//                    Notification notification = new Notification.Builder(getApplicationContext(),CHANNEL_ID).build();
//                    startForeground(1, notification);
//                }
//                else {
//
//                     startForeground(1, notification);
//                }

//                Intent notificationIntent= new Intent(this, DashBoardMain.class);
//
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
//                    pendingIntent = PendingIntent.getActivity
//                            (this, 0, notificationIntent, PendingIntent.FLAG_MUTABLE);
//                }
//                else
//                {
//                    pendingIntent = PendingIntent.getActivity
//                            (this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);
//                }
                startForeground(1, notification);
                intent.putExtra("steps", FetchTrackerInfos.currentSteps);
                sendBroadcast(intent);
                String time = "Current Time : " + hour + ":" + minute + " " ;
                System.out.println(time);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    void updateSteps() {
        String url= String.format("%ssteptracker.php",DataFromDatabase.ipConfig);
        StringRequest request = new StringRequest(Request.Method.POST,url, response -> {
            if (response.equals("updated")){
//                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                Log.d("Response",response);
            }
            else{
                Toast.makeText(getApplicationContext(), "Not working", Toast.LENGTH_SHORT).show();
                Log.d("Response",response);
            }
        },error -> {
            Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String distance = String.format("%.2f",(FetchTrackerInfos.currentSteps/1312.33595801f));
                String calories = (String.format("%.2f",(0.04f*FetchTrackerInfos.currentSteps)));
                Date dateSpeed = new Date();

                SimpleDateFormat hour = new SimpleDateFormat("HH");
                SimpleDateFormat mins = new SimpleDateFormat("mm");

                int h = Integer.parseInt(hour.format(dateSpeed));
                int m = Integer.parseInt(mins.format(dateSpeed));

                int time = h+(m/60);

                String speed = (String.format("%.2f",(FetchTrackerInfos.currentSteps/1312.33595801f)/time));

                System.out.println(calories + " "+distance+" "+speed);

                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                sdf.format(date);
                Map<String,String> data = new HashMap<>();
                data.put("userID",DataFromDatabase.clientuserID);
                data.put("dateandtime", String.valueOf(date));
                data.put("distance", distance);
                data.put("avgspeed", speed);
                data.put("calories",calories);
                data.put("steps", String.valueOf(FetchTrackerInfos.currentSteps));
                data.put("goal", "5000");
                return data;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(request);
    }

}