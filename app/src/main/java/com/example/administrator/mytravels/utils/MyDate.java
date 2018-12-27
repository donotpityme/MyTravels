package com.example.administrator.mytravels.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
    public static final String DATE_PATTERN_DEFAULT = "yyyy/MM/dd";
    private static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_PATTERN_DEFAULT);

    public static String getString(Date date) {
        return SDF.format(date);
    }

    public static String getString(long date) {
        return getString(new Date(date));
    }
}
