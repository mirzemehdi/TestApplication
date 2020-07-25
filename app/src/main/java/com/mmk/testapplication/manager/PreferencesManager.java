package com.mmk.testapplication.manager;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class PreferencesManager {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @SuppressLint("CommitPrefEdits")
    @Inject
    public PreferencesManager(SharedPreferences preferences) {
        this.preferences = preferences;
        editor=preferences.edit();
    }

    public void saveString(String key,String value){
        editor.putString(key,value).apply();
    }
    public String getSavedString(String key){
        return preferences.getString(key,"");
    }

}
