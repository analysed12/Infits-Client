package com.example.infits;

import static androidx.fragment.app.FragmentManager.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Section2Q5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Section2Q5 extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, diagtv;
    CheckBox dia,hyperthy,hypothy,hyperten,pcod,fattyl,lactose;
    EditText oth;
    ArrayList<String> diagnosed;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Section2Q5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section2Q5.
     */
    // TODO: Rename and change types and number of parameters
    public static Section2Q5 newInstance(String param1, String param2) {
        Section2Q5 fragment = new Section2Q5();
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
        View view = inflater.inflate(R.layout.fragment_section2_q5, container, false);

        //dia,hyperthy,hypothy,hyperten,pcod,fattyl,lactose

        imgBack = view.findViewById(R.id.imgback);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        dia = view.findViewById(R.id.dia);
        hyperthy = view.findViewById(R.id.hyperthy);
        hypothy = view.findViewById(R.id.hypothy);
        hyperten = view.findViewById(R.id.hyperten);
        pcod = view.findViewById(R.id.pcod);
        fattyl = view.findViewById(R.id.fattyl);
        lactose = view.findViewById(R.id.lactose);
        oth = view.findViewById(R.id.oth);

        diagnosed = new ArrayList<>();

        diagtv = view.findViewById(R.id.textView77);

        dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dia.isChecked())
                    diagnosed.add("Diabetes");
                else
                    diagnosed.remove("Diabetes");
            }
        });

        hyperthy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hyperthy.isChecked())
                    diagnosed.add("Hyperthyroidism");
                else
                    diagnosed.remove("Hyperthyroidism");
            }
        });

        hypothy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hypothy.isChecked())
                    diagnosed.add("Hypothyroidism");
                else
                    diagnosed.remove("Hypothyroidism");
            }
        });

        hyperten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hyperten.isChecked())
                    diagnosed.add("Hypertension");
                else
                    diagnosed.remove("Hypertension");
            }
        });

        pcod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pcod.isChecked())
                    diagnosed.add("PCOD/PCOS");
                else
                    diagnosed.remove("PCOD/PCOS");
            }
        });

        fattyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fattyl.isChecked())
                    diagnosed.add("Fatty liver");
                else
                    diagnosed.remove("Fatty liver");
            }
        });

        lactose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fattyl.isChecked())
                    diagnosed.add("Lactose intolerance");
                else
                    diagnosed.remove("Lactose intolerance");
            }
        });

        /*

        for(int i=0; i<diagnosed.size();i++) {
            Log.d(TAG,"Diagnosed with: " + diagnosed.get(i));
        }

         */


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String other = oth.getText().toString();

                DataSectionTwo.s2q5 = diagtv.getText().toString();
                if ((!dia.isChecked()) && (!hyperten.isChecked()) && (!hyperthy.isChecked())
                && (!hypothy.isChecked()) && (!pcod.isChecked()) && (!fattyl.isChecked()) &&
                        (!lactose.isChecked())){
                    Toast.makeText(getContext(), "Select atleast one of the given options", Toast.LENGTH_SHORT).show();
                }
                else {
                    DataSectionTwo.diagnosed = diagnosed;
                    ConsultationFragment.psection2 += 1;
                    Navigation.findNavController(v).navigate(R.id.action_section2Q5_to_section2Q6);
                }
                if (!other.isEmpty()){
                    diagnosed.add(other);
                    DataSectionTwo.diagnosed = diagnosed;
                    ConsultationFragment.psection2 += 1;
                    Navigation.findNavController(v).navigate(R.id.action_section2Q5_to_section2Q6);
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ConsultationFragment.psection2>0)
                    ConsultationFragment.psection2-=1;
                requireActivity().onBackPressed();
            }
        });

        imgBack.setOnClickListener(v -> requireActivity().onBackPressed());

        return view;

    }
}