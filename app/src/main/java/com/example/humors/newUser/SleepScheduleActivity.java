package com.example.humors.newUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.utils.Constants;
import com.example.humors.utils.SharedPrefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import nl.joery.timerangepicker.TimeRangePicker;

public class SleepScheduleActivity extends AppCompatActivity {

    private ImageButton backButton;
    private FloatingActionButton nextButton;
    private TextView sleepTime, wakeTime, sleepDuration;

    private TimeRangePicker timeRangePicker;

    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_schedule);

        init();
    }

    private void init() {
        initialiseVariables();
        fetchData();
        setViews();
        setListeners();
        setObservers();
    }

    private void initialiseVariables() {
        backButton = findViewById(R.id.sleep_schedule_back_button);
        nextButton = findViewById(R.id.sleep_schedule_next_button);
        sleepTime = findViewById(R.id.user_start_sleep_time);
        wakeTime = findViewById(R.id.user_end_sleep_time);
        sleepDuration = findViewById(R.id.user_sleep_duration);

        timeRangePicker = findViewById(R.id.time_range_picker);

        sharedPrefs = new SharedPrefs(this);
    }

    private void fetchData() {

    }

    private void setViews() {
        sleepDuration.setText(sharedPrefs.getUerSleepDuration());

    }

    public void saveUserDetails() {
        if (sleepDuration.getText().equals("") ||
                sleepTime.getText().equals("--") || wakeTime.getText().equals("--")) {
            Toast.makeText(this, Constants.ALL_DETAILS, Toast.LENGTH_SHORT).show();
            return;
        }

        sharedPrefs.setUserSleepDuration(sleepDuration.getText().toString());

        startActivity(ShareHabitsActivity.newInstance(this));
    }

    private void setListeners() {

        backButton.setOnClickListener(view -> onBackPressed());
        nextButton.setOnClickListener(view -> saveUserDetails());

        timeRangePicker.setOnTimeChangeListener(new TimeRangePicker.OnTimeChangeListener() {
            @Override
            public void onStartTimeChange(@NonNull TimeRangePicker.Time time) {
                sleepTime.setText(time.toString());
            }

            @Override
            public void onEndTimeChange(@NonNull TimeRangePicker.Time time) {
                wakeTime.setText(time.toString());
            }

            @Override
            public void onDurationChange(@NonNull TimeRangePicker.TimeDuration timeDuration) {
                sleepDuration.setText(timeDuration.toString());
            }
        });
    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, SleepScheduleActivity.class);
    }

}