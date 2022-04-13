package com.example.humors.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.example.humors.database.SQLiteDatabaseHandler;
import com.example.humors.utils.SharedPrefs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StepsServiceJobScheduler extends JobService {

    private SharedPrefs sharedPrefs;
    private float newSteps = 1f;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        Log.e("TAG", "Background service is started in job scheduler");

        SQLiteDatabaseHandler sqLiteDatabaseHandler = new SQLiteDatabaseHandler(StepsServiceJobScheduler.this);

        sharedPrefs = new SharedPrefs(StepsServiceJobScheduler.this);

        float previousDaySteps = sharedPrefs.getPreviousSteps();
        sharedPrefs.setPreviousSteps(sharedPrefs.getNewSteps());
        float todaySteps = sharedPrefs.getPreviousSteps() - previousDaySteps;

        String date = getTodayDate();
        sqLiteDatabaseHandler.addSteps(date, todaySteps);

        return true;
    }

    private String getTodayDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate;

    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.e("TAG", "Job is finished in Job Scheduler");
        return true;
    }
}
