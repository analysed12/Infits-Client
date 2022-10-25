package com.example.infits;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedHashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SectionOneQSix#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectionOneQSix extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, jobtv;
    RadioButton emp, unEmp, pTime;
    String employment;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SectionOneQSix() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SectionOneQSix.
     */
    // TODO: Rename and change types and number of parameters
    public static SectionOneQSix newInstance(String param1, String param2) {
        SectionOneQSix fragment = new SectionOneQSix();
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
        View view = inflater.inflate(R.layout.fragment_section_one_q_six, container, false);

        imgBack = view.findViewById(R.id.imgback);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        emp = view.findViewById(R.id.emp);
        unEmp = view.findViewById(R.id.unEmp);
        pTime = view.findViewById(R.id.pTime);
        RadioGroup r1=view.findViewById(R.id.radioGroup);
        jobtv = view.findViewById(R.id.textView77);


        emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emp.setBackgroundResource(R.drawable.radiobtn_on);
                unEmp.setBackgroundResource(R.drawable.radiobtn_off);
                pTime.setBackgroundResource(R.drawable.radiobtn_off);

                emp.setTextColor(Color.WHITE);
                unEmp.setTextColor(Color.BLACK);
                pTime.setTextColor(Color.BLACK);

                employment="Employed";
            }
        });

        unEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emp.setBackgroundResource(R.drawable.radiobtn_off);
                unEmp.setBackgroundResource(R.drawable.radiobtn_on);
                pTime.setBackgroundResource(R.drawable.radiobtn_off);

                unEmp.setTextColor(Color.WHITE);
                emp.setTextColor(Color.BLACK);
                pTime.setTextColor(Color.BLACK);

                employment="Un employed";
            }
        });

        pTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emp.setBackgroundResource(R.drawable.radiobtn_off);
                unEmp.setBackgroundResource(R.drawable.radiobtn_off);
                pTime.setBackgroundResource(R.drawable.radiobtn_on);

                pTime.setTextColor(Color.WHITE);
                emp.setTextColor(Color.BLACK);
                unEmp.setTextColor(Color.BLACK);

                employment="Part-time";
            }
        });


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getContext(),employment, Toast.LENGTH_SHORT).show();

                DataSectionOne.employment = employment;
                DataSectionOne.s1q6 = jobtv.getText().toString();
                if (r1.getCheckedRadioButtonId() == -1)
                    Toast.makeText(getContext(), "Select your employment status", Toast.LENGTH_SHORT).show();
                else {
                    ConsultationFragment.psection1 += 1;
                    Navigation.findNavController(v).navigate(R.id.action_sectionOneQSix_to_sectionOneQSeven);
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ConsultationFragment.psection1>0)
                    ConsultationFragment.psection1-=1;
                requireActivity().onBackPressed();
            }
        });

        imgBack.setOnClickListener(v -> requireActivity().onBackPressed());

        return view;
    }
}