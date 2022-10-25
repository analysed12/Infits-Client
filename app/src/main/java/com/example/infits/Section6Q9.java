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
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Section6Q9#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Section6Q9 extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, textView77;
    RadioButton daily,never,oneWeek,twWeek,thrWeek,fifteen,monthly;
    String fats="";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Section6Q9() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section6Q9.
     */
    // TODO: Rename and change types and number of parameters
    public static Section6Q9 newInstance(String param1, String param2) {
        Section6Q9 fragment = new Section6Q9();
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
        View view = inflater.inflate(R.layout.fragment_section6_q9, container, false);

        imgBack = view.findViewById(R.id.imgback);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        daily = view.findViewById(R.id.daily);
        never = view.findViewById(R.id.never);
        oneWeek = view.findViewById(R.id.oneWeek);
        twWeek = view.findViewById(R.id.twWeek);
        thrWeek = view.findViewById(R.id.thrWeek);
        fifteen = view.findViewById(R.id.fifteen);
        monthly = view.findViewById(R.id.monthly);

        textView77 = view.findViewById(R.id.textView77);

        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daily.setBackgroundResource(R.drawable.radiobtn_on);
                never.setBackgroundResource(R.drawable.radiobtn_off);
                oneWeek.setBackgroundResource(R.drawable.radiobtn_off);
                twWeek.setBackgroundResource(R.drawable.radiobtn_off);
                thrWeek.setBackgroundResource(R.drawable.radiobtn_off);
                fifteen.setBackgroundResource(R.drawable.radiobtn_off);
                monthly.setBackgroundResource(R.drawable.radiobtn_off);

                daily.setTextColor(Color.WHITE);
                never.setTextColor(Color.BLACK);
                oneWeek.setTextColor(Color.BLACK);
                twWeek.setTextColor(Color.BLACK);
                thrWeek.setTextColor(Color.BLACK);
                fifteen.setTextColor(Color.BLACK);
                monthly.setTextColor(Color.BLACK);

                fats="Daily";
            }
        });

        never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                never.setBackgroundResource(R.drawable.radiobtn_on);
                daily.setBackgroundResource(R.drawable.radiobtn_off);
                oneWeek.setBackgroundResource(R.drawable.radiobtn_off);
                twWeek.setBackgroundResource(R.drawable.radiobtn_off);
                thrWeek.setBackgroundResource(R.drawable.radiobtn_off);
                fifteen.setBackgroundResource(R.drawable.radiobtn_off);
                monthly.setBackgroundResource(R.drawable.radiobtn_off);

                never.setTextColor(Color.WHITE);
                daily.setTextColor(Color.BLACK);
                oneWeek.setTextColor(Color.BLACK);
                twWeek.setTextColor(Color.BLACK);
                thrWeek.setTextColor(Color.BLACK);
                fifteen.setTextColor(Color.BLACK);
                monthly.setTextColor(Color.BLACK);

                fats="Never";
            }
        });

        oneWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneWeek.setBackgroundResource(R.drawable.radiobtn_on);
                never.setBackgroundResource(R.drawable.radiobtn_off);
                daily.setBackgroundResource(R.drawable.radiobtn_off);
                twWeek.setBackgroundResource(R.drawable.radiobtn_off);
                thrWeek.setBackgroundResource(R.drawable.radiobtn_off);
                fifteen.setBackgroundResource(R.drawable.radiobtn_off);
                monthly.setBackgroundResource(R.drawable.radiobtn_off);

                oneWeek.setTextColor(Color.WHITE);
                never.setTextColor(Color.BLACK);
                daily.setTextColor(Color.BLACK);
                twWeek.setTextColor(Color.BLACK);
                thrWeek.setTextColor(Color.BLACK);
                fifteen.setTextColor(Color.BLACK);
                monthly.setTextColor(Color.BLACK);

                fats="Once in a week";
            }
        });

        twWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twWeek.setBackgroundResource(R.drawable.radiobtn_on);
                never.setBackgroundResource(R.drawable.radiobtn_off);
                oneWeek.setBackgroundResource(R.drawable.radiobtn_off);
                daily.setBackgroundResource(R.drawable.radiobtn_off);
                thrWeek.setBackgroundResource(R.drawable.radiobtn_off);
                fifteen.setBackgroundResource(R.drawable.radiobtn_off);
                monthly.setBackgroundResource(R.drawable.radiobtn_off);

                twWeek.setTextColor(Color.WHITE);
                never.setTextColor(Color.BLACK);
                oneWeek.setTextColor(Color.BLACK);
                daily.setTextColor(Color.BLACK);
                thrWeek.setTextColor(Color.BLACK);
                fifteen.setTextColor(Color.BLACK);
                monthly.setTextColor(Color.BLACK);

                fats="Twice in a week";
            }
        });

        thrWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thrWeek.setBackgroundResource(R.drawable.radiobtn_on);
                never.setBackgroundResource(R.drawable.radiobtn_off);
                oneWeek.setBackgroundResource(R.drawable.radiobtn_off);
                twWeek.setBackgroundResource(R.drawable.radiobtn_off);
                daily.setBackgroundResource(R.drawable.radiobtn_off);
                fifteen.setBackgroundResource(R.drawable.radiobtn_off);
                monthly.setBackgroundResource(R.drawable.radiobtn_off);

                thrWeek.setTextColor(Color.WHITE);
                never.setTextColor(Color.BLACK);
                oneWeek.setTextColor(Color.BLACK);
                twWeek.setTextColor(Color.BLACK);
                daily.setTextColor(Color.BLACK);
                fifteen.setTextColor(Color.BLACK);
                monthly.setTextColor(Color.BLACK);

                fats="3-4 times in a week";
            }
        });

        fifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fifteen.setBackgroundResource(R.drawable.radiobtn_on);
                never.setBackgroundResource(R.drawable.radiobtn_off);
                oneWeek.setBackgroundResource(R.drawable.radiobtn_off);
                twWeek.setBackgroundResource(R.drawable.radiobtn_off);
                thrWeek.setBackgroundResource(R.drawable.radiobtn_off);
                daily.setBackgroundResource(R.drawable.radiobtn_off);
                monthly.setBackgroundResource(R.drawable.radiobtn_off);

                fifteen.setTextColor(Color.WHITE);
                never.setTextColor(Color.BLACK);
                oneWeek.setTextColor(Color.BLACK);
                twWeek.setTextColor(Color.BLACK);
                thrWeek.setTextColor(Color.BLACK);
                daily.setTextColor(Color.BLACK);
                monthly.setTextColor(Color.BLACK);

                fats="Once in 15 days";
            }
        });

        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthly.setBackgroundResource(R.drawable.radiobtn_on);
                never.setBackgroundResource(R.drawable.radiobtn_off);
                oneWeek.setBackgroundResource(R.drawable.radiobtn_off);
                twWeek.setBackgroundResource(R.drawable.radiobtn_off);
                thrWeek.setBackgroundResource(R.drawable.radiobtn_off);
                fifteen.setBackgroundResource(R.drawable.radiobtn_off);
                daily.setBackgroundResource(R.drawable.radiobtn_off);

                monthly.setTextColor(Color.WHITE);
                never.setTextColor(Color.BLACK);
                oneWeek.setTextColor(Color.BLACK);
                twWeek.setTextColor(Color.BLACK);
                thrWeek.setTextColor(Color.BLACK);
                fifteen.setTextColor(Color.BLACK);
                daily.setTextColor(Color.BLACK);

                fats="Monthly";
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getContext(),employment, Toast.LENGTH_SHORT).show();

                DataSectionSix.fats = fats;
                DataSectionSix.s6q9 = textView77.getText().toString();
                if (fats.equals(""))
                    Toast.makeText(getContext(), "Select atleast one of the given options", Toast.LENGTH_SHORT).show();
                else {
                    ConsultationFragment.psection6 += 1;

                    Navigation.findNavController(v).navigate(R.id.action_section6Q9_to_section6Q10);
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ConsultationFragment.psection6>0)
                    ConsultationFragment.psection6-=1;
                Navigation.findNavController(v).navigate(R.id.action_section6Q9_to_section6Q8);
            }
        });

        imgBack.setOnClickListener(v -> requireActivity().onBackPressed());

        return view;
    }
}