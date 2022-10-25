package com.example.infits;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kevalpatel2106.rulerpicker.RulerValuePicker;
import com.kevalpatel2106.rulerpicker.RulerValuePickerListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BmiFragment extends DialogFragment {

    String url = String.format("%sweighttracker.php",DataFromDatabase.ipConfig);

    RecyclerView rv;
    PickerAdapter adapter;

    ImageView male, female;
    Button btnadd;
    com.shawnlin.numberpicker.NumberPicker numPicker;
    public String g;
    TextView textView31, textView43, textView94;
    RulerValuePicker rulerValuePickerh, rulerValuePickerw;

    int cur_weight= 0;

    int height = 0;

    Bundle bundle = new Bundle();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BmiFragment() {

    }

    public static BmiFragment newInstance(String param1, String param2) {
        BmiFragment fragment = new BmiFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bmi, container, false);

        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);
        btnadd = view.findViewById(R.id.btnadd);
        textView31 = view.findViewById(R.id.textView31);
        rulerValuePickerh = view.findViewById(R.id.rulerValuePickerh);
        rulerValuePickerw = view.findViewById(R.id.rulerValuePickerw);

        textView43 = view.findViewById(R.id.textView43);
        textView94 = view.findViewById(R.id.textView94);

        numPicker = view.findViewById(R.id.numPicker);

        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.baloo2);

        numPicker.setMinValue(1);
        numPicker.setMaxValue(120);
        numPicker.setTypeface(typeface);
        numPicker.setSelectedTypeface(typeface);


//        rulerValuePickerh.set

        RecyclerView rv = view.findViewById(R.id.rv);

//        male.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                male.setBackground(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.femalemalefocus));
////                female.setBackground(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.femalemaleunfocus));
//
//                g="Male";
//
//                bundle.putString("gender",g);
//
//
//            }
//        });
//
//        female.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                female.setBackground(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.femalemalefocus));
////                male.setBackground(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.femalemaleunfocus));
//
//                g="Female";
//
//                bundle.putString("gender",g);
//            }
//        });


        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(getContext(), PickerLayoutManager.HORIZONTAL, false);
        pickerLayoutManager.setChangeAlpha(true);
        pickerLayoutManager.setScaleDownBy(0.1f);
        pickerLayoutManager.setScaleDownDistance(0.1f);

        adapter = new PickerAdapter(getContext(), getData(100), rv);
        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(rv);
//        rv.setLayoutManager(pickerLayoutManager);
//        rv.setAdapter(adapter);

        pickerLayoutManager.setOnScrollStopListener(new PickerLayoutManager.onScrollStopListener() {
            @Override
            public void selectedView(View view) {
//                Toast.makeText(MainActivity.this, ("Selected value : "+((TextView) view).getText().toString()), Toast.LENGTH_SHORT).show();
                textView31.setText(((TextView) view).getText());
            }
        });

//        numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//
//                //textView31.setText(String.valueOf(newVal));
//
//                bundle.putString("age", String.valueOf(newVal));
//
//            }
//        });

        rulerValuePickerh.setValuePickerListener(new RulerValuePickerListener() {
            @Override
            public void onValueChange(int selectedValue) {

                textView43.setText(selectedValue+"");

                height = selectedValue;

                bundle.putString("height", String.valueOf(selectedValue));

            }

            @Override
            public void onIntermediateValueChange(int selectedValue) {

            }
        });

        rulerValuePickerw.setValuePickerListener(new RulerValuePickerListener() {
            @Override
            public void onValueChange(int selectedValue) {

                textView94.setText(selectedValue+"");
                cur_weight = selectedValue;

                bundle.putString("weight", String.valueOf(selectedValue));

            }

            @Override
            public void onIntermediateValueChange(int selectedValue) {

            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getParentFragmentManager().setFragmentResult("personalData", bundle);

                StringRequest request = new StringRequest(Request.Method.POST,url, response -> {
                    System.out.println(response);
                    if (response.equals("updated")){
                        Navigation.findNavController(v).navigate(R.id.action_bmiFragment_to_weightTrackerFragment);
                    }
                    else{
                        Toast.makeText(getActivity(), "Not working", Toast.LENGTH_SHORT).show();
                    }
                },error -> {
                    Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        sdf.format(date);
                        float hsquare = height* height;
                        float bmi = cur_weight/(hsquare/10000);
                        System.out.println(bmi);
                        System.out.println(hsquare);
                        System.out.println(height);
                        Map<String,String> data = new HashMap<>();
                        data.put("userID","Azarudeen");
                        data.put("date", String.valueOf(sdf.format(date)));
                        data.put("weight", String.valueOf(cur_weight));
                        data.put("height",String.valueOf(height));
                        data.put("goal","70");
                        data.put("bmi",String.format("%.2f",bmi));
                        return data;
                    }
                };
                Volley.newRequestQueue(getActivity().getApplicationContext()).add(request);
            }
        });
        return view;
    }
    public List<String> getData(int count) {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            data.add(String.valueOf(i));
        }
        return data;
    }
}