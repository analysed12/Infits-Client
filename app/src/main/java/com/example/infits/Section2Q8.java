package com.example.infits;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Section2Q8#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Section2Q8 extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, famtv;
    CheckBox dia,hyperthy,hypothy,hyperten,pcod,fattyl;
    EditText oth;
    ArrayList<String> fam;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Section2Q8() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section2Q8.
     */
    // TODO: Rename and change types and number of parameters
    public static Section2Q8 newInstance(String param1, String param2) {
        Section2Q8 fragment = new Section2Q8();
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
        View view = inflater.inflate(R.layout.fragment_section2_q8, container, false);

        imgBack = view.findViewById(R.id.imgback);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        dia = view.findViewById(R.id.dia);
        hyperthy = view.findViewById(R.id.hyperthy);
        hypothy = view.findViewById(R.id.hypothy);
        hyperten = view.findViewById(R.id.hyperten);
        pcod = view.findViewById(R.id.pcod);
        fattyl = view.findViewById(R.id.fattyl);
        oth = view.findViewById(R.id.oth);

        fam = new ArrayList<>();

        famtv = view.findViewById(R.id.textView77);

        dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dia.isChecked())
                    fam.add("Diabetes");
                else
                    fam.remove("Diabetes");
            }
        });

        hyperthy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hyperthy.isChecked())
                    fam.add("Hyperthyroidism");
                else
                    fam.remove("Hyperthyroidism");
            }
        });

        hypothy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hypothy.isChecked())
                    fam.add("Hypothyroidism");
                else
                    fam.remove("Hypothyroidism");
            }
        });

        hyperten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hyperten.isChecked())
                    fam.add("Hypertension");
                else
                    fam.remove("Hypertension");
            }
        });

        pcod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pcod.isChecked())
                    fam.add("PCOD/PCOS");
                else
                    fam.remove("PCOD/PCOS");
            }
        });

        fattyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fattyl.isChecked())
                    fam.add("Fatty liver");
                else
                    fam.remove("Fatty liver");
            }
        });

        String other = oth.getText().toString();

        if(other!=null)
            fam.add(other);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataSectionTwo.familyHistory = fam;
                DataSectionTwo.s2q8 = famtv.getText().toString();


                //Toast.makeText(getContext(), "Data:" + DataSectionTwo.familyHistory, Toast.LENGTH_SHORT).show();
                if ((!dia.isChecked()) && (!hyperten.isChecked()) && (!hyperthy.isChecked())
                        && (!hypothy.isChecked()) && (!pcod.isChecked()) && (!fattyl.isChecked()) &&
                        (other.equals("") || other.equals(" ")))
                    Toast.makeText(getContext(), "Select atleast one of the given options", Toast.LENGTH_SHORT).show();
                else {
                    ConsultationFragment.psection2 += 1;
                    Navigation.findNavController(v).navigate(R.id.action_section2Q8_to_consultationFragment);
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