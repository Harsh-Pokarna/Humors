package com.example.humors.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.humors.SplashActivity;
import com.example.humors.database.SQLiteDatabaseHandler;
import com.example.humors.utils.SharedPrefs;

import com.example.humors.R;
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

//        showNotification(context, todaySteps, date);
    }

    private String getTodayDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate;

    }

    private void showNotification(Context context, float steps, String date) {
        String CHANNEL_ID = "your_name";// The id of the channel.
        CharSequence name = context.getResources().getString(R.string.app_name);// The user-visible name of the channel.
        NotificationCompat.Builder mBuilder;
        Intent notificationIntent = new Intent(context, SplashActivity.class);
        Bundle bundle = new Bundle();
        notificationIntent.putExtras(bundle);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= 26) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(mChannel);
            mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLights(Color.RED, 300, 300)
                    .setChannelId(CHANNEL_ID)
                    .setContentTitle("Steps added").setContentText("Steps Entry: " + date + ": " + steps);
        } else {
            mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentTitle("Steps added").setContentText("Steps Entry: " + date + ": " + steps);
        }

        mBuilder.setContentIntent(contentIntent);
        mBuilder.setContentText("Your Text");
        mBuilder.setAutoCancel(true);
        mNotificationManager.notify(1, mBuilder.build());
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
