package com.example.infits;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphFragment extends Fragment {

    LineChart line;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public GraphFragment() {
    }


    public static GraphFragment newInstance(String param1, String param2) {
        GraphFragment fragment = new GraphFragment();
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
        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        line = view.findViewById(R.id.chart);
//        line.setOnChartGestureListener(this);
//        line.setOnChartValueSelectedListener(this);

//        line.setDragEnabled(true);
//        line.setScaleEnabled(false);

        ArrayList<Entry> NoOfEmp = new ArrayList<>();

        NoOfEmp.add(new Entry( 0, 945f));
        NoOfEmp.add(new Entry( 1,1040f));
        NoOfEmp.add(new Entry( 2,1133f));
        NoOfEmp.add(new Entry( 3,1240f));
        NoOfEmp.add(new Entry( 4,1369f));
        NoOfEmp.add(new Entry( 5,1487f));
        NoOfEmp.add(new Entry( 6,1501f));
        NoOfEmp.add(new Entry( 7,1645f));
        NoOfEmp.add(new Entry( 8,1578f));
        NoOfEmp.add(new Entry( 9,1695f));

        LineDataSet dataSet = new LineDataSet(NoOfEmp, "Number Of Employees");

//        dataSet.setFillAlpha(110);
        ArrayList<ILineDataSet> year = new ArrayList<>();
        year.add(dataSet);

        dataSet.setColor(Color.parseColor("#123456"));
        dataSet.setCircleColor(Color.parseColor("#FF0000"));
        Typeface tf = ResourcesCompat.getFont(getContext(),R.font.nats_regular);
        LineData data = new LineData(dataSet);
        data.setValueTypeface(tf);

        line.setData(data);

        return view;
    }
}