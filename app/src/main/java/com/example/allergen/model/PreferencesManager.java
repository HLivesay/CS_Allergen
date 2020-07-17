package com.example.allergen.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private static SharedPreferences prefs;

    public static void init(Activity act) {
        prefs = act.getSharedPreferences("com.example.allergen", Context.MODE_PRIVATE);
    }

    public static void storeString(String key, String value) {
        prefs.edit().putString(key, value).apply();
    }

    public static String getString(String key) {
        System.out.println(key);
        System.out.println(prefs.getString(key, "Null"));
        return prefs.getString(key, "Null");
    }

}
