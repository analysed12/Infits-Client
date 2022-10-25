package com.example.infits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class FAQ extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Questions> questionslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        recyclerView = findViewById(R.id.recyclerView);

        initData();
        setRecyclerView();
    }

    private void setRecyclerView() {

        QuestionsAdapter questionsAdapter = new QuestionsAdapter(questionslist);
        recyclerView.setAdapter(questionsAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {

        questionslist = new ArrayList<>();

        questionslist.add(new Questions("Q1. Question 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        questionslist.add(new Questions("Q2. Question 2", "Answer 2"));
        questionslist.add(new  Questions("Q3. Question 3", "Answer 3"));
        questionslist.add(new Questions("Q4. Question 4", "Answer 4"));
        questionslist.add(new Questions("Q5. Question 5", "Answer 5"));
        questionslist.add(new Questions("Q6. Question 6", "Answer 6"));
        questionslist.add(new Questions("Q7. Question 7", "Answer 7"));

    }
}