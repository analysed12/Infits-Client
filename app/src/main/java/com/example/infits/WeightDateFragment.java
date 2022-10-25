package com.example.infits;

import static android.content.Context.MODE_PRIVATE;


import org.threeten.bp.LocalDate;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.drawable.Drawable;
import android.icu.text.CaseMap;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kevalpatel2106.rulerpicker.RulerValuePicker;

import com.kevalpatel2106.rulerpicker.RulerValuePickerListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.tistory.dwfox.dwrulerviewlibrary.view.ScrollingValuePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;

public class WeightDateFragment extends DialogFragment {

    RulerValuePicker rulerValuePicker;
    Button btnadd;
    TextView tv_weight, tv_weight2;
    int cur_weight;

    String curDate;

    MaterialCalendarView mcv;

    final ArrayList<String> pinkDateList = new ArrayList<>();
    final ArrayList<String> grayDateList = new ArrayList<>();

    final String DATE_FORMAT = "yyyy-MM-dd";

    int pink = 0;
    int green = 1;
    int blue = 2;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView rv;
    PickerAdapter adapter;

    public WeightDateFragment() {

    }

    public static WeightDateFragment newInstance(String param1, String param2) {
        WeightDateFragment fragment = new WeightDateFragment();
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

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(getActivity(),DashBoardMain.class));
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weight_date, container, false);

        mcv = view.findViewById(R.id.calendarView);

        rv =  view.findViewById(R.id.rv);

        Calendar cal = Calendar.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mcv.state().edit()
                    .setMinimumDate(CalendarDay.from(2022, 4, 3))
                    .setMaximumDate(CalendarDay.from(2100, 12, 31))
                    .setCalendarDisplayMode(CalendarMode.MONTHS)
                    .commit();
            mcv.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
        }

        markGreen();
        markRed();

        List<CalendarDay> independent = new ArrayList<>();
        mcv.setWeekDayLabels(new String[]{"S","M","T","W","T","F","S"});
//        mcv.setTitleFormatter(titleFormatter);

        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Toast.makeText(getContext(),"Selected date "+simpleDateFormat.format(date.getDate()),Toast.LENGTH_LONG).show();
            }
        });

//        mcv.setOnMonthChangedListener(this);
//        rulerValuePicker = view.findViewById(R.id.rulerValuePicker);
        btnadd = view.findViewById(R.id.btnadd);
        tv_weight = view.findViewById(R.id.tv_weight);

//        rulerValuePicker.setValuePickerListener(new RulerValuePickerListener() {
//            @Override
//            public void onValueChange(int selectedValue) {
//
//                tv_weight.setText(selectedValue+" KG");
//                cur_weight = selectedValue;
//
//            }
//
//            @Override
//            public void onIntermediateValueChange(int selectedValue) {
//
//            }
//        });
//
//
//        final DateData[] olddate = {new DateData(2022, 06, 11)};
//
//        calendarView.setOnDateClickListener(new OnDateClickListener() {
//            @Override
//            public void onDateClick(View view, DateData date) {
//                Toast.makeText(getActivity(), String.format("%d-%d", date.getMonth(), date.getDay()), Toast.LENGTH_SHORT).show();
//
//                calendarView.unMarkDate(olddate[0].getYear(),olddate[0].getMonth(),olddate[0].getDay());
//
//                calendarView.markDate(date.getYear(), date.getMonth(), date.getDay()).setMarkedStyle(MarkStyle.BACKGROUND, Color.BLUE);
//
//                olddate[0] = new DateData(date.getYear(),date.getMonth(),date.getDay());
//            }
//        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("weight", String.valueOf(cur_weight));
//                bundle.putString("weightChangeDate", String.valueOf(curDate));

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Weight", MODE_PRIVATE);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                System.out.println(sharedPreferences.getString("Weight", "0"));
                myEdit.putString("weight", String.valueOf(cur_weight));
                myEdit.putString("weightChangeDate", String.valueOf(curDate));

                myEdit.apply();

//                getParentFragmentManager().setFragmentResult("weightData", bundle);

                Navigation.findNavController(v).navigate(R.id.action_weightDateFragment_to_weightTrackerFragment);
            }
        });
        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(getContext(), PickerLayoutManager.HORIZONTAL, false);
        pickerLayoutManager.setChangeAlpha(true);
        pickerLayoutManager.setScaleDownBy(0.1f);
        pickerLayoutManager.setScaleDownDistance(0.1f);

        adapter = new PickerAdapter(getContext(), getData(200), rv);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rv);
        rv.setLayoutManager(pickerLayoutManager);
        rv.setAdapter(adapter);

        pickerLayoutManager.setOnScrollStopListener(new PickerLayoutManager.onScrollStopListener() {
            @Override
            public void selectedView(View view) {
//                Toast.makeText(MainActivity.this, ("Selected value : "+((TextView) view).getText().toString()), Toast.LENGTH_SHORT).show();
                tv_weight.setText(((TextView) view).getText());
                if (((TextView) view).getText() == "5"){
//                    ((TextView) view).setPadding(0,0,0,0);
//                    ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.interval,0,0);
                }
                else{
//                    ((TextView) view).setPadding(0,10,0,10);
//                    ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.lines,0,0);
                }

            }
        });
        return view;
    }

    void setEvent(ArrayList<String> dateList, int color) {

        ArrayList<LocalDate> localDateList = new ArrayList<>();

        for (String string : dateList) {
            LocalDate calendar = getLocalDate(string);
            if (calendar != null) {
                localDateList.add(calendar);
            }
        }

        ArrayList<CalendarDay> datesLeft = new ArrayList<>();
        ArrayList<CalendarDay> datesCenter = new ArrayList<>();
        ArrayList<CalendarDay> datesRight = new ArrayList<>();
        ArrayList<CalendarDay> datesIndependent = new ArrayList<>();

        for (LocalDate localDate : localDateList) {

            boolean right = false;
            boolean left = false;

            for (LocalDate day1 : localDateList) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (localDate.isEqual(day1.plusDays(1))) {
                        left = true;
                    }
                    if (day1.isEqual(localDate.plusDays(1))) {
                        right = true;
                    }
                }
            }

            if (left && right) {
                datesCenter.add(CalendarDay.from(localDate.getYear(),localDate.getMonthValue()-1,localDate.getDayOfMonth()));
            } else if (left) {
                datesLeft.add(CalendarDay.from(localDate.getYear(),localDate.getMonthValue()-1,localDate.getDayOfMonth()));
            } else if (right) {
                datesRight.add(CalendarDay.from(localDate.getYear(),localDate.getMonthValue()-1,localDate.getDayOfMonth()));
            } else {
                datesIndependent.add(CalendarDay.from(localDate.getYear(),localDate.getMonthValue()-1,localDate.getDayOfMonth()));
            }
        }

        if (color == pink) {
            setDecor(datesCenter, R.drawable.p_center);
            setDecor(datesLeft, R.drawable.p_left);
            setDecor(datesRight, R.drawable.p_right);
            setDecor(datesIndependent, R.drawable.p_independent);
        } else if (color == green){
            setDecor(datesCenter, R.drawable.g_center);
            setDecor(datesLeft, R.drawable.g_left);
            setDecor(datesRight, R.drawable.g_right);
            setDecor(datesIndependent, R.drawable.g_independent);
        }
    }

    void setDecor(List<CalendarDay> calendarDayList, int drawable) {
        mcv.addDecorators(new EventDecorator(getActivity()
                , drawable
                , calendarDayList));
    }

    LocalDate getLocalDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        try {
            Date input = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(input);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return LocalDate.of(cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH) + 1,
                        cal.get(Calendar.DAY_OF_MONTH));
            }
        } catch (NullPointerException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
        return null;
    }

    void markRed(){

        new Thread(() -> {
            String urlUnMark = String.format("%sunUpdated.php",DataFromDatabase.ipConfig);

            StringRequest stringRequestCalUn = new StringRequest(Request.Method.POST,urlUnMark,response -> {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray weight = object.getJSONArray("dates");
                    ArrayList<String> dates = new ArrayList<>();
                    for (int i = 0;i<weight.length();i++){
//                    JSONObject date = weight.getJSONObject(i);
                        String dateStr = weight.getString(i);
                        dates.add(dateStr);
                        String[] array = dateStr.split("-");
                        System.out.println(dateStr);
                        setEvent(dates, pink);
                        mcv.invalidateDecorators();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            },error -> {
                Toast.makeText(getContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
//                Log.d("Fragment","clientuserID = " + DataFromDatabase.clientuserID);
                    data.put("userID", "Azarudeen");
                    return data;
                }
            };
            Volley.newRequestQueue(getContext()).add(stringRequestCalUn);
        });
    }

    void markGreen(){
        String urlMark = String.format("%sweightDate.php",DataFromDatabase.ipConfig);

        StringRequest stringRequestCal = new StringRequest(Request.Method.POST,urlMark,response -> {

            try {
                JSONObject object = new JSONObject(response);
                JSONArray weight = object.getJSONArray("weight");
                ArrayList<String> dates = new ArrayList<>();
                for (int i = 0;i<weight.length();i++) {
                    JSONObject date = weight.getJSONObject(i);
                    String dateStr = date.getString("date");
                    dates.add(dateStr);
                    String[] array = dateStr.split("-");
                    System.out.println(dateStr);
                    setEvent(dates, green);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },error -> {
            Toast.makeText(getContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("userID", "Azarudeen");
                return data;
            }
        };
        Volley.newRequestQueue(getContext()).add(stringRequestCal);
    }
    public List<String> getData(int count) {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            data.add(String.valueOf(i));
        }
        return data;
    }
}