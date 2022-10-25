package com.example.infits;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DashBoardFragment extends Fragment {

    String urlRefer = String.format("%sverify.php",DataFromDatabase.ipConfig);

    String url = String.format("%sDashboard.php",DataFromDatabase.ipConfig);
    DataFromDatabase dataFromDatabase;
    TextView stepstv,glassestv,glassesGoaltv,sleeptv,sleepGoaltv,weighttv,weightGoaltv,calorietv,
            calorieGoaltv,bpmtv,bpmUptv,bpmDowntv,meal_date,diet_date;
    RequestQueue queue;
    ImageButton sidemenu, notifmenu;
    CardView stepcard, heartcard, watercard, sleepcard, weightcard, caloriecard,dietcard,goProCard,mealTrackerCard,dietCardPro;
    Button btnsub, btnsub1;
    TextView name,date;
    ImageView profile;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private String urlDt = String.format("%sgetDietitianDetail.php",DataFromDatabase.ipConfig);

    public DashBoardFragment() {

    }

    public static DashBoardFragment newInstance(String param1, String param2) {
        DashBoardFragment fragment = new DashBoardFragment();
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

//        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//            @Override
//            public void handleOnBackPressed() {
//                startActivity(new Intent(getActivity(),DashBoardMain.class));
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DateForSteps", Context.MODE_PRIVATE);

        Date dateForSteps = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-M-yyyy");

        System.out.println(simpleDateFormat.format(dateForSteps));

        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putString("date", simpleDateFormat.format(dateForSteps));
        myEdit.putBoolean("verified",false);
        myEdit.commit();

        SharedPreferences prefs = requireContext().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        String clientuserID = prefs.getString("clientuserID", DataFromDatabase.clientuserID);

        name = view.findViewById(R.id.nameInDash);
        date = view.findViewById(R.id.date);
        profile = view.findViewById(R.id.profile);

        profile.setImageBitmap(DataFromDatabase.profile);

        stepstv = view.findViewById(R.id.steps);
        glassestv = view.findViewById(R.id.glasses);
        glassesGoaltv = view.findViewById(R.id.glassesGoal);
        sleeptv = view.findViewById(R.id.sleep);
        sleepGoaltv = view.findViewById(R.id.sleepgoal);
        weighttv = view.findViewById(R.id.weight);
        weightGoaltv = view.findViewById(R.id.weightGoal);
        calorietv = view.findViewById(R.id.calorie);
        calorieGoaltv = view.findViewById(R.id.calorieGoal);
        bpmtv = view.findViewById(R.id.bpm);
        bpmUptv = view.findViewById(R.id.bpmUp);
        bpmDowntv = view.findViewById(R.id.bpmDown);
        meal_date = view.findViewById(R.id.date_meal);

        Date dateToday = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("MMM dd,yyyy");

        stepcard = view.findViewById(R.id.stepcard);
        heartcard = view.findViewById(R.id.heartcard);
        watercard = view.findViewById(R.id.watercard);
        sleepcard = view.findViewById(R.id.sleepcard);
        weightcard = view.findViewById(R.id.weightcard);
        caloriecard = view.findViewById(R.id.caloriecard);
        dietcard = view.findViewById(R.id.dietcard);
        goProCard = view.findViewById(R.id.proCrad);
        mealTrackerCard = view.findViewById(R.id.meal_tracker);
        dietCardPro = view.findViewById(R.id.dietcardPro);
        diet_date = view.findViewById(R.id.date_diet);
        name.setText(DataFromDatabase.name);
        date.setText(sf.format(dateToday));

        meal_date.setText(sf.format(dateToday));
        diet_date.setText(sf.format(dateToday));

        Log.d("nya", String.valueOf(DataFromDatabase.proUser));

        if (DataFromDatabase.proUser){
            goProCard.setVisibility(View.GONE);
            mealTrackerCard.setVisibility(View.VISIBLE);
            dietCardPro.setVisibility(View.GONE);
            dietcard.setVisibility(View.VISIBLE);
        }
        if (!DataFromDatabase.proUser){
            goProCard.setVisibility(View.VISIBLE);
            mealTrackerCard.setVisibility(View.GONE);
            dietCardPro.setVisibility(View.VISIBLE);
            dietcard.setVisibility(View.GONE);
        }

        stepcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_dashBoardFragment_to_stepTrackerFragment);
            }
        });

        sleepcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_dashBoardFragment_to_sleepTrackerFragment);
            }
        });

        watercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_dashBoardFragment_to_waterTrackerFragment);
            }
        });

        weightcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_dashBoardFragment_to_weightTrackerFragment);
            }
        });

        caloriecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mealTrackerCard.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(),Meal_main.class);
            requireActivity().finish();
            startActivity(intent);
        });

        heartcard.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_dashBoardFragment_to_heartRate);
        });

        dietcard.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(),Diet_plan_main_screen.class);
            requireActivity().finish();
            startActivity(intent);
        });

        if (DataFromDatabase.proUser){
            StringRequest dietitianDetails = new StringRequest(Request.Method.POST,urlDt,response -> {
                System.out.println(response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject object = jsonArray.getJSONObject(0);
                    DataFromDatabase.flag = true;
                    DataFromDatabase.dietitianuserID = object.getString("dietitianuserID");
                    byte[] qrimage = Base64.decode(object.getString("profilePhoto"), 0);
                    DataFromDatabase.dtPhoto = BitmapFactory.decodeByteArray(qrimage, 0, qrimage.length);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            },error -> {

            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> data = new HashMap<>();

                    data.put("userID",prefs.getString("dietitianuserID", DataFromDatabase.dietitianuserID));

                    return data;
                }
            };

            Volley.newRequestQueue(getContext()).add(dietitianDetails);
        }

        goProCard.setOnClickListener(v->{
            showDialog();
        });

        dietCardPro.setOnClickListener(v->{
            showDialog();
        });

        queue = Volley.newRequestQueue(getContext());
        Log.d("ClientMetrics","before");

        StringRequest stringRequestHeart = new StringRequest(Request.Method.POST,String.format("%sheartrate.php",DataFromDatabase.ipConfig),response -> {

            try {
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray jsonArray = jsonResponse.getJSONArray("heart");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                bpmtv.setText(jsonObject.getString("avg"));
                bpmDowntv.setText(jsonObject.getString("min"));
                bpmUptv.setText(jsonObject.getString("max"));
            }catch (JSONException jsonException){
                System.out.println(jsonException);
            }
        },error -> {

        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("userID",clientuserID);
                return data;
            }
        };

        Volley.newRequestQueue(getContext()).add(stringRequestHeart);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, response -> {
            if (!response.equals("failure")){
                Log.d("ClientMetrics","success");
                Log.d("response",response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject object = jsonArray.getJSONObject(0);
                    String stepsStr = object.getString("steps");
                    String stepsGoal = object.getString("stepsgoal");
                    String waterStr = object.getString("waterConsumed");
                    DataFromDatabase.waterStr = waterStr;
                    String waterGoal = object.getString("watergoal");
                    DataFromDatabase.waterGoal = waterGoal;
                    String sleephrsStr = object.getString("sleephrs");
                    String sleepminsStr = object.getString("sleepmins");
                    String sleepGoal = object.getString("sleepgoal");
                    String weightStr = object.getString("weight");
                    DataFromDatabase.weightStr = weightStr;
                    String weightGoal = object.getString("weightgoal");
                    DataFromDatabase.weightGoal = weightGoal;
                    stepstv.setText(Html.fromHtml(String.format("<strong>%s</strong> steps",stepsStr)));
                    glassestv.setText(Html.fromHtml(String.format("<strong>%s</strong> ml",waterStr)));
                    glassesGoaltv.setText(Html.fromHtml(String.format("<strong>%s ml</strong>",waterGoal)));
                    sleeptv.setText(Html.fromHtml(String.format("<strong>%s</strong> hr <strong>%s</strong> mins",sleephrsStr,sleepminsStr)));
                    sleepGoaltv.setText(Html.fromHtml(String.format("<strong>%s Hours</strong>",sleepGoal)));
                    weighttv.setText(Html.fromHtml(String.format("<strong>%s </strong>KiloGrams",weightStr)));
                    weightGoaltv.setText(Html.fromHtml(String.format("<strong>%s KG</strong>",weightGoal)));

                    if (stepsStr.equals("null")){
                        stepstv.setText("no data available");
                    }if (waterStr.equals("null")){
                        glassestv.setText("no data available");
                    }if (waterGoal.equals("null")){
                        glassesGoaltv.setText("no data available");
                    }if (sleephrsStr.equals("null")){
                        sleeptv.setText("no data available");
                    }if (sleepGoal.equals("null")){
                        sleepGoaltv.setText("no data available");
                    }if (weightStr.equals("null")){
                        weighttv.setText("no data available");
                    }if (weightGoal.equals("null")){
                        weightGoaltv.setText("no data available");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            else if (response.equals("failure")){
                Log.d("clientMetrics","failure");
                Toast.makeText(getContext(), "ClientMetrics failed", Toast.LENGTH_SHORT).show();
            }
        },error -> Log.d("dashBoardFrag", error.toString()))
        {
            @NotNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("userID", clientuserID);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
//        Log.d("ClientMetrics","at end");

        return view;
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.referralcodedialog);
        final EditText referralCode = dialog.findViewById(R.id.referralcode);
        ImageView checkReferral = dialog.findViewById(R.id.checkReferral);
        checkReferral.setOnClickListener(vi->{

            StringRequest stringRequest = new StringRequest(Request.Method.POST,urlRefer,response->{

            },error->{

            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> data = new HashMap();

                    data.put("clientID",DataFromDatabase.clientuserID);
                    data.put("referal_code",referralCode.getText().toString());


                    return data;
                }
            };
             Volley.newRequestQueue(getContext()).add(stringRequest);
            dialog.dismiss();
        });
        dialog.show();
    }

}