package com.example.infits;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.slider.Slider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddLiquidFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddLiquidFragment extends Fragment {

    String url = "http://192.168.1.6/infits/watertracker.php";

    RadioButton water, soda, juice, coffee, radioButton;
    Slider slider;
    TextView liqamt;
    Button addbtn;
    RadioGroup radioGroup;
    public float value;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddLiquidFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddLiquidFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddLiquidFragment newInstance(String param1, String param2) {
        AddLiquidFragment fragment = new AddLiquidFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_liquid, container, false);

        water = view.findViewById(R.id.water);
        soda = view.findViewById(R.id.soda);
        juice = view.findViewById(R.id.juice);
        coffee = view.findViewById(R.id.coffee);
        slider = view.findViewById(R.id.slider);
        liqamt = view.findViewById(R.id.liqamt);
        addbtn = view.findViewById(R.id.addbtn);

        radioGroup = view.findViewById(R.id.radioGroup);

        liqamt.setText(String.valueOf(slider.getValue()));

        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {

                value = slider.getValue();
                liqamt.setText(value + " ml");

            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioID);

//                String liquid = radioButton.getText().toString();

                Bundle bundle = new Bundle();
//                bundle.putString("liquidType", liquid);
                bundle.putString("liquidAmt", String.valueOf(value));

                getParentFragmentManager().setFragmentResult("liquidData", bundle);

                StringRequest request = new StringRequest(Request.Method.POST,url, response -> {
                            if (response.equals("updated")){
                                Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getActivity(), "Some Error", Toast.LENGTH_SHORT).show();
                            }
                },error -> {
                    Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> data = new HashMap<>();
                        data.put("userID",DataFromDatabase.clientuserID);
                        data.put("goal","7000");
                        data.put("date", sdf.format(date));
                        data.put("consumed",String.valueOf(value));
                        return data;
                    }
                };

                Navigation.findNavController(v).navigate(R.id.action_addLiquidFragment_to_waterTrackerFragment);

            }
        });

        return view;
    }
}