package com.example.infits;
import android.content.Context;
import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.HashSet;
import java.util.List;

public class EventDecorator implements DayViewDecorator {
    private String date;
    Context context;
    private int drawable;
    private HashSet<CalendarDay> dates;

    public EventDecorator(Context context, int drawable, List<CalendarDay> calendarDays1) {
        this.context = context;
        this.drawable = drawable;
        this.dates = new HashSet<>(calendarDays1);
    }

    public EventDecorator(Context context, int drawable, String date) {
        this.context = context;
        this.drawable = drawable;
        this.date = date;
    }


    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        // apply drawable to dayView
        view.setSelectionDrawable(context.getResources().getDrawable(drawable));
        // white text color
        view.addSpan(new ForegroundColorSpan(Color.WHITE));
    }
}