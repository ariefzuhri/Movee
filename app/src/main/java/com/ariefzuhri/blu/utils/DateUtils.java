package com.ariefzuhri.blu.utils;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.ariefzuhri.blu.utils.AppUtils.locale;

public class DateUtils {
    public static final String DATE_FORMAT = "yyyy/MM/dd";

    public static String getDateWithoutYear(String date){
        if (isValidDateFormat(date)){
            int[] arrayDate = getArrayDate(date);
            String month = null;
            if (arrayDate != null) {
                month = new DateFormatSymbols().getMonths()[(arrayDate[1])];
                return arrayDate[2] + " " + month;
            } else return "-1";
        } else return "-1";
    }

    public static String getYearOfDate(String date){
        if (isValidDateFormat(date)){
            int[] arrayDate = getArrayDate(date);
            if (arrayDate != null) return String.valueOf(arrayDate[0]);
            else return "-1";
        } else return "-1";
    }

    public static int[] getArrayDate(String date){
        if (isValidDateFormat(date)){
            String[] stringArrayDate = date.split("/");
            int[] integerArrayDate = new int[3];
            for (int i = 0; i < 3; i++) integerArrayDate[i] = Integer.parseInt(stringArrayDate[i]);
            // Karena bulan di mulai dari 0, jadi dikurangi 1
            return new int[] {integerArrayDate[0], integerArrayDate[1]-1, integerArrayDate[2]};
        } else return null;
    }

    private static boolean isValidDateFormat(String date){
        if (date != null){
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, locale);
                dateFormat.parse(date);
                return true;
            } catch (ParseException e) {
                return false;
            }
        } else return false;
    }
}
