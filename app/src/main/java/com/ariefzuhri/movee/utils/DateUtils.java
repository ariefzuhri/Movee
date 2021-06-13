package com.ariefzuhri.movee.utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getDateWithoutYear(String date){
        Date formattedDate = stringToDate(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formattedDate);
        return String.format("%s %s",
                new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)],
                calendar.get(Calendar.DAY_OF_MONTH)
        );
    }

    public static String getYearOfDate(String date){
        Date formattedDate = stringToDate(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formattedDate);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public static Date stringToDate(String date) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
