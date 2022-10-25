package com.example.infits;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Section5Q1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Section5Q1 extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, textView77;
    RadioButton veg,nonveg,ovo,vegan,gluten;
    EditText oth;
    String preference="";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Section5Q1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section5Q1.
     */
    // TODO: Rename and change types and number of parameters
    public static Section5Q1 newInstance(String param1, String param2) {
        Section5Q1 fragment = new Section5Q1();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_section5_q1, container, false);

        //veg,nonveg,ovo,vegan,gluten

        imgBack = view.findViewById(R.id.imgback);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        veg = view.findViewById(R.id.veg);
        nonveg = view.findViewById(R.id.nonveg);
        ovo = view.findViewById(R.id.ovo);
        vegan = view.findViewById(R.id.vegan);
        gluten = view.findViewById(R.id.gluten);
        oth = view.findViewById(R.id.oth);

        textView77 = view.findViewById(R.id.textView77);


        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veg.setBackgroundResource(R.drawable.radiobtn_on);
                nonveg.setBackgroundResource(R.drawable.radiobtn_off);
                ovo.setBackgroundResource(R.drawable.radiobtn_off);
                vegan.setBackgroundResource(R.drawable.radiobtn_off);
                gluten.setBackgroundResource(R.drawable.radiobtn_off);
                oth.setBackgroundResource(R.drawable.radiobtn_off);

                veg.setTextColor(Color.WHITE);
                nonveg.setTextColor(Color.BLACK);
                ovo.setTextColor(Color.BLACK);
                vegan.setTextColor(Color.BLACK);
                gluten.setTextColor(Color.BLACK);
                oth.setTextColor(Color.BLACK);

                preference="Vegetarian";
            }
        });

        nonveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nonveg.setBackgroundResource(R.drawable.radiobtn_on);
                veg.setBackgroundResource(R.drawable.radiobtn_off);
                ovo.setBackgroundResource(R.drawable.radiobtn_off);
                vegan.setBackgroundResource(R.drawable.radiobtn_off);
                gluten.setBackgroundResource(R.drawable.radiobtn_off);
                oth.setBackgroundResource(R.drawable.radiobtn_off);

                nonveg.setTextColor(Color.WHITE);
                veg.setTextColor(Color.BLACK);
                ovo.setTextColor(Color.BLACK);
                vegan.setTextColor(Color.BLACK);
                gluten.setTextColor(Color.BLACK);
                oth.setTextColor(Color.BLACK);

                preference="Non-vegetarian";
            }
        });

        ovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ovo.setBackgroundResource(R.drawable.radiobtn_on);
                nonveg.setBackgroundResource(R.drawable.radiobtn_off);
                veg.setBackgroundResource(R.drawable.radiobtn_off);
                vegan.setBackgroundResource(R.drawable.radiobtn_off);
                gluten.setBackgroundResource(R.drawable.radiobtn_off);
                oth.setBackgroundResource(R.drawable.radiobtn_off);

                ovo.setTextColor(Color.WHITE);
                nonveg.setTextColor(Color.BLACK);
                veg.setTextColor(Color.BLACK);
                vegan.setTextColor(Color.BLACK);
                gluten.setTextColor(Color.BLACK);
                oth.setTextColor(Color.BLACK);

                preference="Ovo-vegetarian";
            }
        });

        vegan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegan.setBackgroundResource(R.drawable.radiobtn_on);
                nonveg.setBackgroundResource(R.drawable.radiobtn_off);
                ovo.setBackgroundResource(R.drawable.radiobtn_off);
                veg.setBackgroundResource(R.drawable.radiobtn_off);
                gluten.setBackgroundResource(R.drawable.radiobtn_off);
                oth.setBackgroundResource(R.drawable.radiobtn_off);

                vegan.setTextColor(Color.WHITE);
                nonveg.setTextColor(Color.BLACK);
                ovo.setTextColor(Color.BLACK);
                veg.setTextColor(Color.BLACK);
                gluten.setTextColor(Color.BLACK);
                oth.setTextColor(Color.BLACK);

                preference="Vegan";
            }
        });

        gluten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gluten.setBackgroundResource(R.drawable.radiobtn_on);
                nonveg.setBackgroundResource(R.drawable.radiobtn_off);
                ovo.setBackgroundResource(R.drawable.radiobtn_off);
                vegan.setBackgroundResource(R.drawable.radiobtn_off);
                veg.setBackgroundResource(R.drawable.radiobtn_off);
                oth.setBackgroundResource(R.drawable.radiobtn_off);

                gluten.setTextColor(Color.WHITE);
                nonveg.setTextColor(Color.BLACK);
                ovo.setTextColor(Color.BLACK);
                vegan.setTextColor(Color.BLACK);
                veg.setTextColor(Color.BLACK);
                oth.setTextColor(Color.BLACK);

                preference="Gluten-free";
            }
        });

        oth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oth.setBackgroundResource(R.drawable.radiobtn_on);
                nonveg.setBackgroundResource(R.drawable.radiobtn_off);
                ovo.setBackgroundResource(R.drawable.radiobtn_off);
                vegan.setBackgroundResource(R.drawable.radiobtn_off);
                gluten.setBackgroundResource(R.drawable.radiobtn_off);
                veg.setBackgroundResource(R.drawable.radiobtn_off);

                oth.setTextColor(Color.WHITE);
                nonveg.setTextColor(Color.BLACK);
                ovo.setTextColor(Color.BLACK);
                vegan.setTextColor(Color.BLACK);
                veg.setTextColor(Color.BLACK);
                gluten.setTextColor(Color.BLACK);

                preference=oth.getText().toString();
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getContext(),employment, Toast.LENGTH_SHORT).show();

                DataSectionFive.preference = preference;
                DataSectionFive.s5q1 = textView77.getText().toString();
                if (preference.equals(""))
                    Toast.makeText(getContext(), "Select atleast one of the given options", Toast.LENGTH_SHORT).show();
                else {
                    ConsultationFragment.psection5 += 1;

                    Navigation.findNavController(v).navigate(R.id.action_section5Q1_to_section5Q2);
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ConsultationFragment.psection5>0)
                    ConsultationFragment.psection5-=1;
                requireActivity().onBackPressed();
            }
        });

        imgBack.setOnClickListener(v -> requireActivity().onBackPressed());

        return view;
    }
}