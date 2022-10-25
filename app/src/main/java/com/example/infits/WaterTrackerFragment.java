package com.example.infits;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.slider.Slider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WaterTrackerFragment extends Fragment {

    ImageView imgback, addliq;
    TextView waterGoalPercent, wgoal3, textViewsleep, consumed;
    TextView waterGoal;
    String liqType = "water", liqAmt;
    Button setgoal;
    float goalWater;
    int goal = 1800;
    int consumedInDay;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public WaterTrackerFragment() {

    }

    public static WaterTrackerFragment newInstance(String param1, String param2) {
        WaterTrackerFragment fragment = new WaterTrackerFragment();
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
                startActivity(new Intent(getActivity(),DashBoardMain.class));
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_water_tracker, container, false);

        addliq = view.findViewById(R.id.addliq);
        imgback = view.findViewById(R.id.imgback);
        waterGoalPercent = view.findViewById(R.id.water_goal_percent);
        wgoal3 = view.findViewById(R.id.wgoal3);
        setgoal = view.findViewById(R.id.setgoal_watertracker);
        textViewsleep = view.findViewById(R.id.textviewsleep);
        consumed = view.findViewById(R.id.water_consumed);
        waterGoal = view.findViewById(R.id.water_goal);

        if (DataFromDatabase.waterGoal.equals(null)) {
            waterGoal.setText("0 ml");
        } else {
            waterGoal.setText(DataFromDatabase.waterGoal + " ml");
            try {
                goal = Integer.parseInt(DataFromDatabase.waterGoal);
            } catch (NumberFormatException ex) {
                goal = 1800;
                waterGoal.setText(1800 + " ml");
                System.out.println(ex);
            }
        }

        if (DataFromDatabase.waterStr.equals(null) || DataFromDatabase.waterStr.equals("null")) {
            consumed.setText("0 ml");
        } else {
            consumed.setText(DataFromDatabase.waterStr + " ml");
            try {
                consumedInDay = Integer.parseInt(DataFromDatabase.waterStr);
                waterGoalPercent.setText(String.valueOf(calculateGoal()));
            } catch (NumberFormatException ex) {
                consumedInDay = 0;
                waterGoalPercent.setText(String.valueOf(calculateGoal()));
            }
        }

        waterGoalPercent.setText(String.valueOf(calculateGoal()));

        RecyclerView rc = view.findViewById(R.id.past_activity);

        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> datas = new ArrayList<>();

        String url = String.format("%spastActivityWater.php", DataFromDatabase.ipConfig);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                System.out.println(response);
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("water");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String data = object.getString("water");
                    String date = object.getString("date");
                    dates.add(date);
                    datas.add(data);
                    System.out.println(datas.get(i));
                    System.out.println(dates.get(i));
                }
                AdapterForPastActivity ad = new AdapterForPastActivity(getContext(), dates, datas, Color.parseColor("#76A5FF"));
                rc.setLayoutManager(new LinearLayoutManager(getContext()));
                rc.setAdapter(ad);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Error", error.toString());
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("clientID", DataFromDatabase.clientuserID);
                return data;
            }
        };

        Volley.newRequestQueue(getActivity()).add(stringRequest);

        setgoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.watergoaldialog);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                final EditText goaltxt = dialog.findViewById(R.id.goal_water);
                Button setGoalBtn = dialog.findViewById(R.id.set_water_goal);
                setGoalBtn.setOnClickListener(v -> {
                    if (!goaltxt.getText().toString().equals("")) {
                        goal = Integer.parseInt(goaltxt.getText().toString());
                        waterGoal.setText(goaltxt.getText().toString() + " ml");
                        waterGoalPercent.setText(String.valueOf(calculateGoal()));
//                            String url = String.format("%swatertracker.php",DataFromDatabase.ipConfig);
//                            StringRequest request = new StringRequest(Request.Method.POST,url, response -> {
//                                if (response.equals("updated")){
//                                    consumed.setText(String.valueOf(consumedInDay)+" ml");
//                                    waterGoalPercent.setText(String.valueOf(calculateGoal()));
//                                }
//                                else{
//                                    Toast.makeText(getActivity(), "Not working", Toast.LENGTH_SHORT).show();
//                                }
//                            },error -> {
//                                Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show();
//                            }){
//                                @Nullable
//                                @Override
//                                protected Map<String, String> getParams() throws AuthFailureError {
//                                    Date date = new Date();
//                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                                    SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
//                                    sdf.format(date);
//                                    Map<String,String> data = new HashMap<>();
//                                    data.put("userID",DataFromDatabase.clientuserID);
//                                    data.put("date", String.valueOf(date));
//                                    data.put("consumed", String.valueOf(consumedInDay));
//                                    data.put("goal", String.valueOf(goal));
//                                    data.put("time",stf.format(date));
//                                    data.put("type",liqType);
//                                    return data;
//                                }
//                            };
//                            Volley.newRequestQueue(getActivity().getApplicationContext()).add(request);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Enter Goal", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                waterGoalPercent.setText(String.valueOf(calculateGoal()));
            }
        });

        addliq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.fragment_add_liquid);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                Button addDrank = dialog.findViewById(R.id.addbtn);

                Slider slider = dialog.findViewById(R.id.slider);

                TextView choosed = dialog.findViewById(R.id.liqamt);

                final int[] value = new int[1];


                RadioGroup typeOfLiquid = dialog.findViewById(R.id.radioGroup);
                typeOfLiquid.setOnCheckedChangeListener((group, checkedId) -> {
                    if (checkedId == R.id.soda) {
                        dialog.findViewById(checkedId).setForeground(getActivity().getDrawable(R.drawable.outline_liq));
                        dialog.findViewById(R.id.coffee).setForeground(null);
                        dialog.findViewById(R.id.water).setForeground(null);
                        dialog.findViewById(R.id.juice).setForeground(null);
                        liqType = "soda";
                    }
                    if (checkedId == R.id.water) {
                        dialog.findViewById(checkedId).setForeground(getActivity().getDrawable(R.drawable.outline_liq));
                        dialog.findViewById(R.id.coffee).setForeground(null);
                        dialog.findViewById(R.id.soda).setForeground(null);
                        dialog.findViewById(R.id.juice).setForeground(null);
                        liqType = "water";
                    }
                    if (checkedId == R.id.juice) {
                        dialog.findViewById(checkedId).setForeground(getActivity().getDrawable(R.drawable.outline_liq));
                        dialog.findViewById(R.id.coffee).setForeground(null);
                        dialog.findViewById(R.id.water).setForeground(null);
                        dialog.findViewById(R.id.soda).setForeground(null);
                        liqType = "juice";
                    }
                    if (checkedId == R.id.coffee) {
                        dialog.findViewById(checkedId).setForeground(getActivity().getDrawable(R.drawable.outline_liq));
                        dialog.findViewById(R.id.soda).setForeground(null);
                        dialog.findViewById(R.id.water).setForeground(null);
                        dialog.findViewById(R.id.juice).setForeground(null);
                        liqType = "coffee";
                    }

                });

                slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
                    @Override
                    public void onStartTrackingTouch(@NonNull Slider slider) {

                    }

                    @Override
                    public void onStopTrackingTouch(@NonNull Slider slider) {

                        value[0] = (int) slider.getValue();
                        choosed.setText(String.valueOf((float) value[0]));
                        Log.d("Water", String.valueOf(value[0]));

                    }
                });

                choosed.setText(String.valueOf(slider.getValue()));

                addDrank.setOnClickListener(v1 -> {
                    consumedInDay += (int) Float.parseFloat(choosed.getText().toString());
//                    consumed.setText(String.valueOf(consumedInDay));
                    dialog.dismiss();
                    String url = String.format("%swatertracker.php", DataFromDatabase.ipConfig);
                    StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
                        if (response.equals("updated")) {
                            consumed.setText(String.valueOf(consumedInDay) + " ml");
                            waterGoalPercent.setText(String.valueOf(calculateGoal()));
                        } else {
                            Toast.makeText(getActivity(), "Not working", Toast.LENGTH_SHORT).show();
                        }
                    }, error -> {
                        Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Date date = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            sdf.format(date);
                            Map<String, String> data = new HashMap<>();
                            SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
                            data.put("userID", DataFromDatabase.clientuserID);
                            data.put("date", String.valueOf(date));
                            data.put("consumed", String.valueOf(consumedInDay));
                            data.put("goal", String.valueOf(goal));
                            data.put("time", stf.format(date));
                            data.put("type", liqType);
                            data.put("amount", String.valueOf(value[0]));
                            return data;
                        }
                    };
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Log.d("date", sdf.format(date));
                    Volley.newRequestQueue(getActivity().getApplicationContext()).add(request);
                });

                dialog.show();

            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_waterTrackerFragment_to_dashBoardFragment);
            }
        });


        getParentFragmentManager().setFragmentResultListener("liquidData", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                liqType = result.getString("liquidType");
                liqAmt = result.getString("liquidAmt");
            }
        });

        /*
        public void onClick(DialogInterface dialog,
                                int id) {
                            Dialog f = (Dialog) dialog;
                            //This is the input I can't get text from
                            EditText inputTemp = (EditText) f.findViewById(R.id.search_input_text);
                            query = inputTemp.getText().toString();
                           ...
                        }
         */

//        waterGoal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final Dialog dialog = new Dialog(getContext());
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setCancelable(true);
//                dialog.setContentView(R.layout.watergoaldialog);
//
//                final EditText goal = dialog.findViewById(R.id.goal);
//                Button button = dialog.findViewById(R.id.button);
//
//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String wGoal = goal.getText().toString();
//
//                        //textViewsleep.setText("Goal: "+wGoal);
//
//
//                        float liqAmount = Float.parseFloat(liqAmt);
//                        //textViewsleep.setText("Goal: "+wGoal);
//
//
//                        float res = (liqAmount/Float.parseFloat(wGoal)) *100;
//
//                        wgoalperc.setText((int)res+ " %");
//
//                        goalWater = Float.parseFloat(wGoal);
//
//                        dialog.dismiss();
//
//                    }
//                });
//
//                dialog.show();
//
//                //Navigation.findNavController(v).navigate(R.id.action_waterTrackerFragment_to_addLiquidFragment);
//            }
//        });

        //textViewsleep.setText("Goal: "+goalWater);


        return view;
    }

    String calculateGoal() {
        int per = 0;
        try {
            per = consumedInDay * 100 / goal;
        } catch (ArithmeticException e) {
            Log.d("WaterTrackFrag", "Arithmetic Ex, consumedInDay, goal: " + consumedInDay + ", " + goal);
        }
        System.out.println(per);
        System.out.println(consumedInDay);
        System.out.println(goal);
        return String.valueOf(per) + " %";
    }
}