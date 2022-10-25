package com.example.infits;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FAQFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FAQFragment extends Fragment {

    ImageButton imgback;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FAQFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FAQFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FAQFragment newInstance(String param1, String param2) {
        FAQFragment fragment = new FAQFragment();
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

        /*  it made kind of an infinite loop of dashboard screen -->  */
//        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//            @Override
//            public void handleOnBackPressed() {
//                startActivity(new Intent(getActivity(),DashBoardMain.class));
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_a_q, container, false);
        List<Questions> questionslist = new ArrayList<>();

        questionslist.add(new Questions("Q1. Question 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        questionslist.add(new Questions("Q2. Question 2", "Answer 2"));
        questionslist.add(new  Questions("Q3. Question 3", "Answer 3"));
        questionslist.add(new Questions("Q4. Question 4", "Answer 4"));
        questionslist.add(new Questions("Q5. Question 5", "Answer 5"));
        questionslist.add(new Questions("Q6. Question 6", "Answer 6"));
        questionslist.add(new Questions("Q7. Question 7", "Answer 7"));
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        QuestionsAdapter questionsAdapter = new QuestionsAdapter(questionslist);
        recyclerView.setAdapter(questionsAdapter);
        recyclerView.setHasFixedSize(true);


        imgback = view.findViewById(R.id.imgback);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_FAQFragment_to_helpFragment);
            }
        });

        return view;
    }
}