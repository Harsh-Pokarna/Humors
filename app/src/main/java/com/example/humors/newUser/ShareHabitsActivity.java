package com.example.humors.newUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.example.humors.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import nl.joery.timerangepicker.TimeRangePicker;

public class ShareHabitsActivity extends AppCompatActivity {

    private ImageButton backButton;
    private FloatingActionButton nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_habits);

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
        backButton = findViewById(R.id.share_habits_back_button);
        nextButton = findViewById(R.id.share_habits_next_button);
    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {
        backButton.setOnClickListener(view -> onBackPressed());
        nextButton.setOnClickListener(view -> startActivity(FoodHabitsActivity.newInstance(this)));
    }

    private void setObservers() {

    }



    public static Intent newInstance(Context context) {
        return new Intent(context, ShareHabitsActivity.class);
    }

}