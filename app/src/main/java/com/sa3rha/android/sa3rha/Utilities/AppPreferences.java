package com.sa3rha.android.sa3rha.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    static AppPreferences PrefInstance;
    static SharedPreferences AppPrefs;
    final static String SharedPrefName = "Sa3erhaPref";

    private AppPreferences() {
    }

    /**
     * This method return an instance of {@link AppPreferences} to implement the singleton puttern
     */
    public static AppPreferences getInstance(Context context) {
        if (PrefInstance == null) {
            PrefInstance = new AppPreferences();
            AppPrefs = context.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);
        }
        return PrefInstance;
    }
}
