package com.example.myapplicationn;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateValueFormatter extends ValueFormatter {

    private final SimpleDateFormat mFormat = new SimpleDateFormat("MM-dd", Locale.KOREA);

    @Override
    public String getFormattedValue(float value) {
        Date date = new Date();
        long millis = date.getTime() + TimeUnit.HOURS.toMillis((long) value);
        return mFormat.format(new Date(millis));
    }
}
