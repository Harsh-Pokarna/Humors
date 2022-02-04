package com.example.humors.newUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.utils.Constants;
import com.example.humors.utils.SharedPrefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class FoodHabitsActivity extends AppCompatActivity {

    private ImageButton backButton;
    private FloatingActionButton nextButton;
    private RadioGroup vegRg, foodRg, waterRg;

    private String vegStatus, foodStatus, waterStatus;

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
        vegStatus = sharedPrefs.getUserVegStatus();
        foodStatus = sharedPrefs.getUserJunkFoodStatus();
        waterStatus = sharedPrefs.getUserWaterStatus();
    }

    private void fetchData() {

    }

    private void setViews() {

        switch (vegStatus.toLowerCase()) {
            case "never":
                vegRg.check(R.id.never_non_veg);
                break;
            case "once_a_week":
                vegRg.check(R.id.once_non_veg);
                break;
            case "thrice_a_week":
                vegRg.check(R.id.thrice_non_veg);
                break;
            case "daily":
                vegRg.check(R.id.daily_non_veg);
                break;
        }

        switch (foodStatus.toLowerCase()) {
            case "never":
                foodRg.check(R.id.never_junk_food);
                break;
            case "once_a_week":
                foodRg.check(R.id.once_junk_food);
                break;
            case "thrice_a_week":
                foodRg.check(R.id.thrice_junk_food);
                break;
            case "daily":
                foodRg.check(R.id.daily_junk_food);
                break;
        }

        switch (waterStatus.toLowerCase()) {
            case "1-2l":
                foodRg.check(R.id.op1_water);
                break;
            case "2-3l":
                foodRg.check(R.id.op2_water);
                break;
            case "more_than_4l":
                foodRg.check(R.id.op3_water);
                break;
        }

    }

    private void saveUserDetails() {
        if (vegRg.getCheckedRadioButtonId() == -1 || foodRg.getCheckedRadioButtonId() == -1
         || waterRg.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, Constants.ALL_DETAILS, Toast.LENGTH_SHORT).show();
            return;
        }

        sharedPrefs.setUserVegStatus(((RadioButton)findViewById(vegRg.getCheckedRadioButtonId())).getText().toString().replace(" ", "_"));
        sharedPrefs.setUserJunkFoodStatus(((RadioButton)findViewById(foodRg.getCheckedRadioButtonId())).getText().toString().replace(" ", "_"));
        sharedPrefs.setUserWaterStatus(((RadioButton)findViewById(waterRg.getCheckedRadioButtonId())).getText().toString().replace(" ", "_"));

        startActivity(NewUserHomeActivity.newInstance(this));
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