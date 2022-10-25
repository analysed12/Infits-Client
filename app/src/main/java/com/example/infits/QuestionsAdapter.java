package com.example.infits;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsVH> {

    List<Questions> questionslist;

    public QuestionsAdapter(List<Questions> questionslist) {
        this.questionslist = questionslist;
    }

    @NonNull
    @Override
    public QuestionsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new QuestionsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsVH holder, int position) {

        Questions questions = questionslist.get(position);
        holder.cardnameTxt.setText(questions.getAnswers());
        holder.answerTxt.setText(questions.getCardname());

        boolean isExpandable = questionslist.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return questionslist.size();
    }

    public class QuestionsVH extends RecyclerView.ViewHolder {

        TextView cardnameTxt, answerTxt;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;


        public QuestionsVH(@NonNull View itemView) {
            super(itemView);

            cardnameTxt = itemView.findViewById(R.id.ans1);
            answerTxt = itemView.findViewById(R.id.card1);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);

            linearLayout.setOnClickListener(v -> {

                Questions questions = questionslist.get(getAdapterPosition());
                questions.setExpandable(!questions.isExpandable());
                expandableLayout.setVisibility(questions.isExpandable() ? View.VISIBLE : View.GONE);
                notifyItemChanged(getAdapterPosition());

            });

        }
    }
}
