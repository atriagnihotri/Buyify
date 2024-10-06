package com.example.buyify.SharedPreferences;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    private static final String PREF_NAME = "MyPrefs";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public SharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }


    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }


    public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }


    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }


    public void clearAll() {
        editor.clear();
        editor.apply();
    }
}
