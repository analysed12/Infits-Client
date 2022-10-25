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
 * Use the {@link Section5Q11#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Section5Q11 extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, textView77;
    RadioButton yes,no,occ;
    String smoking="";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Section5Q11() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section5Q11.
     */
    // TODO: Rename and change types and number of parameters
    public static Section5Q11 newInstance(String param1, String param2) {
        Section5Q11 fragment = new Section5Q11();
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
        View view = inflater.inflate(R.layout.fragment_section5_q11, container, false);

        imgBack = view.findViewById(R.id.imgback);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        yes = view.findViewById(R.id.yes);
        no = view.findViewById(R.id.no);
        occ = view.findViewById(R.id.occ);

        textView77 = view.findViewById(R.id.textView77);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes.setBackgroundResource(R.drawable.radiobtn_on);
                no.setBackgroundResource(R.drawable.radiobtn_off);
                occ.setBackgroundResource(R.drawable.radiobtn_off);

                yes.setTextColor(Color.WHITE);
                no.setTextColor(Color.BLACK);
                occ.setTextColor(Color.BLACK);

                smoking="Yes";
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no.setBackgroundResource(R.drawable.radiobtn_on);
                yes.setBackgroundResource(R.drawable.radiobtn_off);
                occ.setBackgroundResource(R.drawable.radiobtn_off);

                no.setTextColor(Color.WHITE);
                yes.setTextColor(Color.BLACK);
                occ.setTextColor(Color.BLACK);

                smoking="No";
            }
        });

        occ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                occ.setBackgroundResource(R.drawable.radiobtn_on);
                yes.setBackgroundResource(R.drawable.radiobtn_off);
                no.setBackgroundResource(R.drawable.radiobtn_off);

                occ.setTextColor(Color.WHITE);
                yes.setTextColor(Color.BLACK);
                no.setTextColor(Color.BLACK);

                smoking="Occasionally";
            }
        });


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getContext(),uGender, Toast.LENGTH_SHORT).show();

                DataSectionFive.smoking = smoking;
                DataSectionFive.s5q11 = textView77.getText().toString();

                if (smoking.equals(""))
                    Toast.makeText(getContext(), "Select atleast one of the given options", Toast.LENGTH_SHORT).show();
                else {
                    ConsultationFragment.psection5 += 1;

                    Navigation.findNavController(v).navigate(R.id.action_section5Q11_to_section5Q12);
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