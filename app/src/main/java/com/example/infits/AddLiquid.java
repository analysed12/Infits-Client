package com.example.infits;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.slider.Slider;

public class AddLiquid extends DialogFragment {

    RadioButton water, soda, juice, coffee;
    Slider slider;
    TextView liqamt;
    Button addbtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addliquiddialog, container, false);

        water = view.findViewById(R.id.water);
        soda = view.findViewById(R.id.soda);
        juice = view.findViewById(R.id.juice);
        coffee = view.findViewById(R.id.coffee);
        slider = view.findViewById(R.id.slider);
        liqamt = view.findViewById(R.id.liqamt);
        addbtn = view.findViewById(R.id.addbtn);

        String liquid = "Hello";


        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liquid = "Water";
            }
        });

        soda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liquid = "Soda";
            }
        });

        juice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liquid = "Juice";
            }
        });

        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String liquid = "Coffee";
            }
        });

        liqamt.setText("Liquid: ");

        return view;


    }

}
