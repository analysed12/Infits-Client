package com.example.infits;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    TextView name,qualification,location,aboutMe;
    DataFromDatabase dataFromDatabase;
    RecyclerView recyclerView1;
    ImageView pic;
    ImageButton btnBack;
    String reviewer_name[]={"Martha Finch", "Martha Finch","Martha Finch","Martha Finch"};
    String reviwer_ratings[]={"4.8","4.8","4.8","4.8"};
    String reviwer_review[]={"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temporLorem ipsum dolor sit amet... ",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temporLorem ipsum dolor sit amet... ",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temporLorem ipsum dolor sit amet... ",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temporLorem ipsum dolor sit amet... "};
    String reviewer_image[]={"app/src/main/res/drawable-v24/review_profile.png"
            ,"app/src/main/res/drawable-v24/review_profile.png", "app/src/main/res/drawable-v24/review_profile.png",
            "app/src/main/res/drawable-v24/review_profile.png"};

    List<Dietician_review> obj= new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
        View v= inflater.inflate(R.layout.fragment_profile, container, false);
        btnBack = v.findViewById(R.id.imgback);
        name = v.findViewById(R.id.dietician_profile_name);
        qualification = v.findViewById(R.id.qualificationProfile);
        pic=v.findViewById(R.id.dietician_profile_image);
        location = v.findViewById(R.id.dieitician_location);
        name.setText(DataFromDatabase.dietitianuserID);
        pic.setImageBitmap(DataFromDatabase.dtPhoto);
//        qualification.setText("DataFromDatabase");
//        location.setText("dataFromDatabase.location");
        aboutMe=v.findViewById(R.id.dieitician_about_me);
//        aboutMe.setText("About me: \n"+"dataFromDatabase.about_me");

        RecyclerView r1=v.findViewById(R.id.dietician_reviews);
        for (int i=0;i<reviewer_image.length;i++)
        {
            Dietician_review object=new Dietician_review(reviewer_name[i],reviewer_image[i],reviwer_review[i],reviwer_ratings[i]);
            obj.add(object);
        }
        Dietician_review_adapter adap=new Dietician_review_adapter(getContext(),obj);
        r1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        r1.setAdapter(adap);

        btnBack.setOnClickListener(it -> {
            Navigation.findNavController(it).navigate(R.id.action_profile2_to_settingsFragment);
        });

        return v;


    }
}