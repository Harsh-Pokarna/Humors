package com.example.humors.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import com.example.humors.Humors;

import java.util.prefs.Preferences;

public class SharedPrefs {

    private Context context;
    private final SharedPreferences sharedPreferences;

    public SharedPrefs(@NonNull Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getUserName() {
        return sharedPreferences.getString(Constants.USER_NAME, "");
    }

    public void setUserName(String userName) {
        sharedPreferences.edit().putString(Constants.USER_NAME, userName).apply();
    }

    public int getUserStatus() {
        return sharedPreferences.getInt(Constants.USER_STATUS, -1);
    }

    public void setUserStatus(int userStatus) {
        sharedPreferences.edit().putInt(Constants.USER_STATUS, userStatus).apply();
    }

    public String getUserEmail() {
        return sharedPreferences.getString(Constants.USER_EMAIL, "");
    }

    public void setUserEmail(String userEmail) {
        sharedPreferences.edit().putString(Constants.USER_EMAIL, userEmail).apply();
    }

    public int getUserId() {
        return sharedPreferences.getInt(Constants.USER_ID, -1);
    }

    public void setUserId(int userId) {
        sharedPreferences.edit().putInt(Constants.USER_ID, userId).apply();
    }
}
