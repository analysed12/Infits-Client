package com.example.infits;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tenclouds.gaugeseekbar.GaugeSeekBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StepTrackerFragment extends Fragment {

    Button setgoal;
    ImageButton imgback;
    TextView steps_label,goal_step_count,distance,calories,speed;

GaugeSeekBar  progressBar;

    float goalVal = 5000;

    float goalPercent = 0;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public StepTrackerFragment() {

    }

    public static StepTrackerFragment newInstance(String param1, String param2) {
        StepTrackerFragment fragment = new StepTrackerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireActivity(), R.id.imgback).navigate(R.id.action_stepTrackerFragment_to_dashBoardFragment);
                FragmentManager manager = requireActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_step_tracker, container, false);

        steps_label = view.findViewById(R.id.steps_label);
        setgoal = view.findViewById(R.id.setgoal);
        imgback = view.findViewById(R.id.imgback);
        goal_step_count = view.findViewById(R.id.goal_step_count);
        RecyclerView pastActivity = view.findViewById(R.id.past_activity);

        progressBar = view.findViewById(R.id.progressBar);
        speed = view.findViewById(R.id.speed);
        distance = view.findViewById(R.id.distance);
        calories = view.findViewById(R.id.calories);

        progressBar.setProgress(0);

        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> datas = new ArrayList<>();

//        if (DataFromDatabase.stepsGoal.equals(null)){
//            goal_step_count.setText("5000");
//        }
//        else{
//            goal_step_count.setText(DataFromDatabase.stepsGoal+" ml");
//            try {
//                 = Integer.parseInt(DataFromDatabase.waterGoal);
//            }catch (NumberFormatException ex){
//                goal = 1800;
//                waterGoal.setText(1800+" ml");
//                System.out.println(ex);
//            }
//        }
//
//        if (DataFromDatabase.stepsStr.equals(null)|| DataFromDatabase.stepsStr.equals("null")){
//            steps_label.setText("0");
//        }
//        else{
//            steps_label.setText(DataFromDatabase.waterStr+" ml");
//            try {
//                 = Integer.parseInt(DataFromDatabase.waterStr);
//                waterGoalPercent.setText(String.valueOf(calculateGoal()));
//            }catch (NumberFormatException ex){
//                consumedInDay = 0;
//                waterGoalPercent.setText(String.valueOf(calculateGoal()));
//            }
//        }

        String url = String.format("%spastActivity.php",DataFromDatabase.ipConfig);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("steps");
                for (int i = 0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String data = object.getString("steps");
                    String date = object.getString("date");
                    dates.add(date);
                    datas.add(data);
                    System.out.println(datas.get(i));
                    System.out.println(dates.get(i));
                }
                AdapterForPastActivity ad = new AdapterForPastActivity(getContext(),dates,datas, Color.parseColor("#FF9872"));
                pastActivity.setLayoutManager(new LinearLayoutManager(getContext()));
                pastActivity.setAdapter(ad);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },error -> {
            Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Error",error.toString());
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("clientID",DataFromDatabase.clientuserID);
                return data;
            }
        };

        Volley.newRequestQueue(getActivity()).add(stringRequest);

        PowerManager powerManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            powerManager = (PowerManager) getActivity().getSystemService(getActivity().POWER_SERVICE);
        }
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyApp::MyWakelockTag");
        wakeLock.acquire();

        setgoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.setgoaldialog);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                EditText goal = dialog.findViewById(R.id.goal);
                Button save = dialog.findViewById(R.id.save_btn_steps);
//                progressBar.setProgress(0);
//                steps_label.setText(String.valueOf(0));
                save.setOnClickListener(v->{
//                    FetchTrackerInfos.previousStep = FetchTrackerInfos.totalSteps;
                    goal_step_count.setText(goal.getText().toString());
                    goalVal = Integer.parseInt(goal.getText().toString());

                    dialog.dismiss();
                });
                dialog.show();
            }
        });

//        Intent serviceIntent = new Intent(getActivity(), MyService.class);
//        serviceIntent.putExtra("goal",goalVal);
//        if (true /*!foregroundServiceRunning()*/){
//            ContextCompat.startForegroundService(getActivity(), serviceIntent);
//        }

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_stepTrackerFragment_to_dashBoardFragment);
                FragmentManager manager = getActivity().getSupportFragmentManager();
//                FragmentTransaction trans = manager.beginTransaction();
//                trans.remove(StepTrackerFragment.this);
//                trans.commit();
                manager.popBackStack();
            }
        });
//        final Handler handler = new Handler();
//        final int delay = 1000; // 1000 milliseconds == 1 second
//
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                System.out.println(FetchTrackerInfos.stepsWalked);
//                if (!FetchTrackerInfos.stepsWalked.isEmpty()){
//                        steps_label.setText(FetchTrackerInfos.currentSteps);
//                }
//                handler.postDelayed(this, delay);
//            }
//        }, delay);
        return view;
    }
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent);
        }
    };
    public boolean foregroundServiceRunning(){
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)){
         if (MyService.class.getName().equals(service.service.getClassName())){
             return true;
         }
     }
        return false;
    }
    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            float steps = intent.getIntExtra("steps",0);
            Log.i("StepTracker","Countdown seconds remaining:" + steps);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goalPercent = ((steps/goalVal)*100)/100;
                    System.out.println(goalPercent);
                    progressBar.setProgress(goalPercent);
                    steps_label.setText(String.valueOf((int) steps));
                    distance.setText(String.format("%.2f",(steps/1312.33595801f)));
                    calories.setText(String.format("%.2f",(0.04f*steps)));
                    Date date = new Date();

                    SimpleDateFormat hour = new SimpleDateFormat("HH");
                    SimpleDateFormat mins = new SimpleDateFormat("mm");

                    int h = Integer.parseInt(hour.format(date));
                    int m = Integer.parseInt(mins.format(date));

                    int time = h+(m/60);

                    speed.setText(String.format("%.2f",(steps/1312.33595801f)/time));
                    System.out.println(0.04f*steps);
                    System.out.println((steps/1312.33595801f)/time);
                }
            },20000);
//            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
//            sharedPreferences.edit().putInt("steps",steps).apply();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(broadcastReceiver,new IntentFilter("com.example.infits"));
        Log.i("Steps","Registered broadcast receiver");
    }
}