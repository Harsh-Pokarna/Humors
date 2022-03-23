package com.example.humors.home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.usage.ConfigurationStats;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.humors.R;
import com.example.humors.newUser.AddDataActivity;
import com.example.humors.newUser.MedicalHistoryActivity;
import com.example.humors.newUser.ShareHabitsActivity;
import com.example.humors.newUser.SleepScheduleActivity;
import com.example.humors.others.AboutActivity;
import com.example.humors.utils.Constants;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton backButton;
    private FrameLayout myDetailsButton, myHabitsButton, myMedicalHistoryButton, testHistoryButton, settingsButton, aboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
        backButton = findViewById(R.id.profile_back_button);
        myDetailsButton = findViewById(R.id.add_details_button);
        myHabitsButton = findViewById(R.id.my_habits_button);
        myMedicalHistoryButton = findViewById(R.id.medical_history_button);
        testHistoryButton = findViewById(R.id.test_histry_button);
        settingsButton = findViewById(R.id.settings_button);
        aboutButton = findViewById(R.id.about_button_profile_page);

    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {
        backButton.setOnClickListener(view -> onBackPressed());
        myDetailsButton.setOnClickListener(view -> startActivity(AddDataActivity.newInstance(this, Constants.UPDATE_DATA)));
        myHabitsButton.setOnClickListener(view -> startActivity(SleepScheduleActivity.newInstance(this, Constants.UPDATE_DATA)));
        myMedicalHistoryButton.setOnClickListener(view -> startActivity(MedicalHistoryActivity.newInstance(this, Constants.UPDATE_DATA)));
        testHistoryButton.setOnClickListener(view -> HomeActivity.newInstance(this));
        aboutButton.setOnClickListener(view -> AboutActivity.newInstance(this));

    }

    private void setObservers() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public static Intent newInstance(Context context) {
        return new Intent(context, ProfileActivity.class);
    }
}