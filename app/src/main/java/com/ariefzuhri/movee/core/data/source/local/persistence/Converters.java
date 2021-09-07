package com.ariefzuhri.movee.core.data.source.local.persistence;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {

    private static final Gson gson = new Gson();

    @TypeConverter
    public static List<String> stringToStrings(String value) {
        Type stringsType = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(value, stringsType);
    }

    @TypeConverter
    public static String stringsToString(List<String> value) {
        Type stringType = new TypeToken<String>() {
        }.getType();
        return gson.toJson(value, stringType);
    }
}
