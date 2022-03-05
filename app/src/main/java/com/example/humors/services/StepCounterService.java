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

        SQLiteDatabaseHandler sqLiteDatabaseHandler = new SQLiteDatabaseHandler(context);

        sharedPrefs = new SharedPrefs(context);

        Float previousDaySteps = sharedPrefs.getPreviousSteps();
        sharedPrefs.setPreviousSteps(sharedPrefs.getNewSteps());
        Float todaySteps = sharedPrefs.getPreviousSteps() - previousDaySteps;

        String date = getPreviousDate(getTodayDate());
        sqLiteDatabaseHandler.addSteps(date, todaySteps);

        Log.e("TAG", "Steps are added" );

    }

    private String getTodayDate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
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
            Log.d("asd", "selected date : "+inputDate);

            System.out.println(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            inputDate ="";
        }
        return inputDate;
    }

}
