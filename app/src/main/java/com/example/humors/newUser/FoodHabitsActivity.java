package com.example.humors.newUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.example.humors.R;
import com.example.humors.utils.SharedPrefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FoodHabitsActivity extends AppCompatActivity {

    private ImageButton backButton;
    private FloatingActionButton nextButton;
    private RadioGroup vegRg, foodRg, waterRg;

    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_habits);

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
        backButton = findViewById(R.id.food_habits_back_button);
        nextButton = findViewById(R.id.food_habits_next_button);
        vegRg = findViewById(R.id.non_veg_radio_grp);
        foodRg = findViewById(R.id.junk_food_radio_grp);
        waterRg = findViewById(R.id.water_radio_grp);

        sharedPrefs = new SharedPrefs(this);
    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void saveUserDetails() {

    }

    private void setListeners() {
        backButton.setOnClickListener(view -> onBackPressed());
        nextButton.setOnClickListener(view -> saveUserDetails());

    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, FoodHabitsActivity.class);
    }

}