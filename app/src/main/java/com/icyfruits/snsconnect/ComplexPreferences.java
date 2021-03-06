package com.icyfruits.snsconnect;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by DEV.C on 2016-12-05.
 */

public class ComplexPreferences {

    private static ComplexPreferences complexPreferences;
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static Gson GSON = new Gson();
    private Type typeOfObject = new TypeToken<Object>() {
    }.getType();

    private ComplexPreferences(Context context, String namePreferences, int mode) {
        this.context = context;
        if (namePreferences == null || namePreferences.equals("")) {
            namePreferences = "complex_preferences";
        }
        preferences = context.getSharedPreferences(namePreferences, mode);
        editor = preferences.edit();
    }

    public static ComplexPreferences getComplexPreferences(Context context, String namePreferences, int mode) {

        complexPreferences = new ComplexPreferences(context, namePreferences, mode);

        return complexPreferences;


    }

    public void putObject(String key, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }
        if (key.equals("") || key == null) {
            throw new IllegalArgumentException("key is empty or null");
        }

        editor.putString(key, GSON.toJson(object));
    }

    public void commit() {
        editor.commit();
    }

    public void clearObject() {
        editor.clear();
    }

    public <T> T getObject(String key, Class<T> a) {
        String gson = preferences.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return GSON.fromJson(gson, a);

            } catch (Exception e) {
                throw new IllegalArgumentException("Object storagedwith key" + key + "is instance of other class");
            }
        }
    }

}
