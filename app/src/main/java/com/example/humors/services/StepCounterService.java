package com.example.humors.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.humors.database.SQLiteDatabaseHandler;
import com.example.humors.utils.SharedPrefs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StepCounterService extends BroadcastReceiver {


    private SharedPrefs sharedPrefs;
    private float newSteps = 1f;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("TAG", "Background service is started");

        SQLiteDatabaseHandler sqLiteDatabaseHandler = new SQLiteDatabaseHandler(context);

        sharedPrefs = new SharedPrefs(context);

        float previousDaySteps = sharedPrefs.getPreviousSteps();
        sharedPrefs.setPreviousSteps(sharedPrefs.getNewSteps());
        float todaySteps = sharedPrefs.getPreviousSteps() - previousDaySteps;

        String date = getTodayDate();
        sqLiteDatabaseHandler.addSteps(date, todaySteps);


    }

    private String getTodayDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate;

    }

    private String getPreviousDate(String inputDate) {

        SimpleDateFormat  format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = format.parse(inputDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.DATE, -1);
            inputDate = format.format(c.getTime());

            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
            inputDate ="";
        }
        return inputDate;
    }

}
