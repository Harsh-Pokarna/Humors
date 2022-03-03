package com.example.humors.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.humors.database.SQLiteDatabaseHandler;
import com.example.humors.utils.SharedPrefs;

public class StepCounterService extends BroadcastReceiver {

    private SQLiteDatabaseHandler databaseHandler;

    private SharedPrefs sharedPrefs;
    private float newSteps = 1f;

    @Override
    public void onReceive(Context context, Intent intent) {

        databaseHandler = new SQLiteDatabaseHandler(context);
        sharedPrefs = new SharedPrefs(context);
        newSteps = sharedPrefs.getNewSteps();
        Log.e("TAG", "new steps in services are: " + newSteps);
        sharedPrefs.setPreviousSteps(newSteps);

    }
}
