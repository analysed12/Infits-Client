package com.example.infits;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedHashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SectionOneQThree#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectionOneQThree extends Fragment {

    ImageButton imgBack;
    Button nextbtn;
    TextView backbtn, agetv;
    EditText eTextAge;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SectionOneQThree() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SectionOneQThree.
     */
    // TODO: Rename and change types and number of parameters
    public static SectionOneQThree newInstance(String param1, String param2) {
        SectionOneQThree fragment = new SectionOneQThree();
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
        View view = inflater.inflate(R.layout.fragment_section_one_q_three, container, false);

        imgBack = view.findViewById(R.id.imgback);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        eTextAge = view.findViewById(R.id.eTextAge);

        agetv = view.findViewById(R.id.textView80);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_age = eTextAge.getText().toString();
                //Toast.makeText(getContext(),user_age, Toast.LENGTH_SHORT).show();

                DataSectionOne.age = user_age;
                DataSectionOne.s1q3 = agetv.getText().toString();
                if(user_age.equals("")|| user_age.equals(" "))
                    Toast.makeText(getContext(),"Add your age",Toast.LENGTH_SHORT).show();
                else{
                    ConsultationFragment.psection1+=1;
                Navigation.findNavController(v).navigate(R.id.action_sectionOneQThree_to_sectionOneQFour);
            }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConsultationFragment.psection1>0)
                    ConsultationFragment.psection1-=1;
                requireActivity().onBackPressed();
            }
        });

        imgBack.setOnClickListener(v -> requireActivity().onBackPressed());

        return view;
    }
}