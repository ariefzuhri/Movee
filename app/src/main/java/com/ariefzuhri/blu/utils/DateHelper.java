package com.ariefzuhri.blu.utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getDateWithoutYear(String date){
        if (isValidDateFormat(date)){
            int[] arrayDate = getArrayDate(date);
            String month;
            if (arrayDate != null) {
                // Karena bulan di mulai dari 0, jadi dikurangi 1
                month = new DateFormatSymbols().getMonths()[(arrayDate[1]-1)];
                return arrayDate[2] + " " + month;
            } else return date;
        } else return date;
    }

    public static String getYearOfDate(String date){
        if (isValidDateFormat(date)){
            int[] arrayDate = getArrayDate(date);
            if (arrayDate != null) return String.valueOf(arrayDate[0]);
            else return date;
        } else return date;
    }

    public static int[] getArrayDate(String date){
        if (isValidDateFormat(date)){
            String[] stringArrayDate = date.split("-");
            int[] integerArrayDate = new int[3];
            for (int i = 0; i < 3; i++) integerArrayDate[i] = Integer.parseInt(stringArrayDate[i]);
            return new int[] {integerArrayDate[0], integerArrayDate[1], integerArrayDate[2]};
        } else return null;
    }

    private static boolean isValidDateFormat(String date){
        if (date != null){
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
                dateFormat.parse(date);
                return true;
            } catch (ParseException e) {
                return false;
            }
        } else return false;
    }
}
