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
 * Use the {@link Section5Q9#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Section5Q9 extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, textView77;
    RadioButton home,hotel,onehome,hostel,twohome;
    EditText oth;
    String daily_food="";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Section5Q9() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section5Q9.
     */
    // TODO: Rename and change types and number of parameters
    public static Section5Q9 newInstance(String param1, String param2) {
        Section5Q9 fragment = new Section5Q9();
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
        View view = inflater.inflate(R.layout.fragment_section5_q9, container, false);

        imgBack = view.findViewById(R.id.imgback);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        home = view.findViewById(R.id.home);
        hotel = view.findViewById(R.id.hotel);
        onehome = view.findViewById(R.id.onehome);
        hostel = view.findViewById(R.id.hostel);
        twohome = view.findViewById(R.id.twohome);
        oth = view.findViewById(R.id.oth);

        textView77 = view.findViewById(R.id.textView77);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home.setBackgroundResource(R.drawable.radiobtn_on);
                hotel.setBackgroundResource(R.drawable.radiobtn_off);
                onehome.setBackgroundResource(R.drawable.radiobtn_off);
                hostel.setBackgroundResource(R.drawable.radiobtn_off);
                twohome.setBackgroundResource(R.drawable.radiobtn_off);
                oth.setBackgroundResource(R.drawable.radiobtn_off);

                home.setTextColor(Color.WHITE);
                hotel.setTextColor(Color.BLACK);
                onehome.setTextColor(Color.BLACK);
                hostel.setTextColor(Color.BLACK);
                twohome.setTextColor(Color.BLACK);
                oth.setTextColor(Color.BLACK);

                daily_food="Home";
            }
        });

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotel.setBackgroundResource(R.drawable.radiobtn_on);
                home.setBackgroundResource(R.drawable.radiobtn_off);
                onehome.setBackgroundResource(R.drawable.radiobtn_off);
                hostel.setBackgroundResource(R.drawable.radiobtn_off);
                twohome.setBackgroundResource(R.drawable.radiobtn_off);
                oth.setBackgroundResource(R.drawable.radiobtn_off);

                hotel.setTextColor(Color.WHITE);
                home.setTextColor(Color.BLACK);
                onehome.setTextColor(Color.BLACK);
                hostel.setTextColor(Color.BLACK);
                twohome.setTextColor(Color.BLACK);
                oth.setTextColor(Color.BLACK);

                daily_food="Hotel/canteen";
            }
        });

        onehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onehome.setBackgroundResource(R.drawable.radiobtn_on);
                hotel.setBackgroundResource(R.drawable.radiobtn_off);
                home.setBackgroundResource(R.drawable.radiobtn_off);
                hostel.setBackgroundResource(R.drawable.radiobtn_off);
                twohome.setBackgroundResource(R.drawable.radiobtn_off);
                oth.setBackgroundResource(R.drawable.radiobtn_off);

                onehome.setTextColor(Color.WHITE);
                hotel.setTextColor(Color.BLACK);
                home.setTextColor(Color.BLACK);
                hostel.setTextColor(Color.BLACK);
                twohome.setTextColor(Color.BLACK);
                oth.setTextColor(Color.BLACK);

                daily_food="1 meal home others in hotel/canteen";
            }
        });

        hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hostel.setBackgroundResource(R.drawable.radiobtn_on);
                hotel.setBackgroundResource(R.drawable.radiobtn_off);
                onehome.setBackgroundResource(R.drawable.radiobtn_off);
                home.setBackgroundResource(R.drawable.radiobtn_off);
                twohome.setBackgroundResource(R.drawable.radiobtn_off);
                oth.setBackgroundResource(R.drawable.radiobtn_off);

                hostel.setTextColor(Color.WHITE);
                hotel.setTextColor(Color.BLACK);
                onehome.setTextColor(Color.BLACK);
                home.setTextColor(Color.BLACK);
                twohome.setTextColor(Color.BLACK);
                oth.setTextColor(Color.BLACK);

                daily_food="Hostel";
            }
        });

        twohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twohome.setBackgroundResource(R.drawable.radiobtn_on);
                hotel.setBackgroundResource(R.drawable.radiobtn_off);
                onehome.setBackgroundResource(R.drawable.radiobtn_off);
                hostel.setBackgroundResource(R.drawable.radiobtn_off);
                home.setBackgroundResource(R.drawable.radiobtn_off);
                oth.setBackgroundResource(R.drawable.radiobtn_off);

                twohome.setTextColor(Color.WHITE);
                hotel.setTextColor(Color.BLACK);
                onehome.setTextColor(Color.BLACK);
                hostel.setTextColor(Color.BLACK);
                home.setTextColor(Color.BLACK);
                oth.setTextColor(Color.BLACK);

                daily_food="2 meal at home others in hotel/canteen";
            }
        });

        oth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oth.setBackgroundResource(R.drawable.radiobtn_on);
                hotel.setBackgroundResource(R.drawable.radiobtn_off);
                onehome.setBackgroundResource(R.drawable.radiobtn_off);
                hostel.setBackgroundResource(R.drawable.radiobtn_off);
                twohome.setBackgroundResource(R.drawable.radiobtn_off);
                home.setBackgroundResource(R.drawable.radiobtn_off);

                oth.setTextColor(Color.WHITE);
                hotel.setTextColor(Color.BLACK);
                onehome.setTextColor(Color.BLACK);
                hostel.setTextColor(Color.BLACK);
                twohome.setTextColor(Color.BLACK);
                home.setTextColor(Color.BLACK);

                daily_food=oth.getText().toString();;
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getContext(),employment, Toast.LENGTH_SHORT).show();

                DataSectionFive.daily_food = daily_food;
                DataSectionFive.s5q9 = textView77.getText().toString();

                if (daily_food.equals(""))
                    Toast.makeText(getContext(), "Select atleast one of the given options", Toast.LENGTH_SHORT).show();
                else {
                    ConsultationFragment.psection5 += 1;

                    Navigation.findNavController(v).navigate(R.id.action_section5Q9_to_section5Q10);
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