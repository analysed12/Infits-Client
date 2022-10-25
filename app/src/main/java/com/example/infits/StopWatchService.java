package com.example.infits;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class StopWatchService extends Service {

    int hours;
    int minutes;
    int secs;

    String timerTime;

    private int seconds;
    private boolean running = true;

      Handler handler = new Handler();

      Runnable run ;

      Intent intent = new Intent("com.example.infits.sleep");

    public StopWatchService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timerTime = "";

        seconds = 0;

        hours = 0;
        minutes = 0;
        secs = 0;

        intent = this.intent;

        Intent finalIntent = intent;

        run = new Runnable() {
            @Override
            public void run() {
                        hours = seconds / 3600;
                        minutes = (seconds % 3600) / 60;
                        secs = seconds % 60;

                        timerTime = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

                        Log.d("TimerBro",timerTime);

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            NotificationChannel chan = new NotificationChannel(
                                    "MyChannelId",
                                    "My Foreground Service",
                                    NotificationManager.IMPORTANCE_LOW);
                            chan.setLightColor(Color.BLUE);
                            chan.setLockscreenVisibility(android.app.Notification.VISIBILITY_SECRET);

                            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            assert manager != null;
                            manager.createNotificationChannel(chan);

                            Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
                            PendingIntent pendingIntent =
                                    PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,0);

                            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), "MyChannelId");
                            Notification notification = notificationBuilder.setOngoing(true)
                                    .setSmallIcon(R.mipmap.logo)
                                    .setContentTitle(timerTime)
                                    .setContentIntent(pendingIntent)
                                    .setChannelId("MyChannelId")
                                    .build();
                            startForeground(1, notification);
                            finalIntent.putExtra("sleep", timerTime);
                            sendBroadcast(finalIntent);
                        }
                        if(running) {
                            seconds++;
                        }
                        handler.postDelayed(this,1000);
                    }
                };
        handler.post(run);

//        run = new Runnable() {
//            @Override
//            public void run() {
//
//                hours = seconds / 3600;
//                minutes = (seconds % 3600) / 60;
//                secs = seconds % 60;
//
//                timerTime = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
//
//                Log.d("TimerBro",timerTime);
//
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                    NotificationChannel chan = new NotificationChannel(
//                            "MyChannelId",
//                            "My Foreground Service",
//                            NotificationManager.IMPORTANCE_LOW);
//                    chan.setLightColor(Color.BLUE);
//                    chan.setLockscreenVisibility(android.app.Notification.VISIBILITY_SECRET);
//
//                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                    assert manager != null;
//                    manager.createNotificationChannel(chan);
//
//                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), "MyChannelId");
//                    android.app.Notification notification = notificationBuilder.setOngoing(true)
//                            .setSmallIcon(R.mipmap.ic_launcher)
//                            .setContentTitle(timerTime)
//                            .setPriority(NotificationManager.IMPORTANCE_LOW)
//                            .setCategory(Notification.CATEGORY_SERVICE)
//                            .setChannelId("MyChannelId")
//                            .build();
//                    startForeground(1, notification);
//                    finalIntent.putExtra("sleep", timerTime);
//                    sendBroadcast(finalIntent);
//                }
//                if(running) {
//                    seconds++;
//                }
//                handler.postDelayed(this, 1000);
//            }
//        };
//        Thread thread = new Thread(run);
//        thread.start();
////        handler.post(run);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        handler.removeCallbacks(run);

        intent.putExtra("sleep", timerTime);
        sendBroadcast(intent);
    }
}