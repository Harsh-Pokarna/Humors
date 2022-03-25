package com.example.humors.newUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.api.ApiClient;
import com.example.humors.home.ProfileActivity;
import com.example.humors.utils.Constants;
import com.example.humors.utils.SharedPrefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import nl.joery.timerangepicker.TimeRangePicker;

public class    ShareHabitsActivity extends AppCompatActivity {

    private ImageButton backButton;
    private FloatingActionButton nextButton;
    private RadioGroup alcoholRg, smokingRg, exerciseRg;

    private String alcoholStatus, smokingStatus, exerciseStatus;

    private SharedPrefs sharedPrefs;

    private String extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_habits);

        init();
    }

    private void init() {
        getExtras();
        initialiseVariables();
        fetchData();
        setViews();
        setListeners();
        setObservers();
    }

    private void getExtras() {
        extras = getIntent().getStringExtra(Constants.SHARE_HABITS);
    }

    private void initialiseVariables() {
        backButton = findViewById(R.id.share_habits_back_button);
        nextButton = findViewById(R.id.share_habits_next_button);
        alcoholRg = findViewById(R.id.alcohol_radio_grp);
        smokingRg = findViewById(R.id.smoke_radio_grp);
        exerciseRg = findViewById(R.id.exercise_radio_grp);

        sharedPrefs = new SharedPrefs(this);
        alcoholStatus = sharedPrefs.getUserAlcoholStatus();
        smokingStatus = sharedPrefs.getUserSmokingStatus();
        exerciseStatus = sharedPrefs.getUserExerciseStatus();
    }

    private void fetchData() {

    }

    private void setViews() {

        if (alcoholStatus.equalsIgnoreCase("never")) {
            alcoholRg.check(R.id.never_alcohol);
        } else if (alcoholStatus.equalsIgnoreCase("sometimes")) {
            alcoholRg.check(R.id.sometimes_alcohol);
        } else if (alcoholStatus.equalsIgnoreCase("weekly")) {
            alcoholRg.check(R.id.weekly_alcohol);
        } else if (alcoholStatus.equalsIgnoreCase("monthly")) {
            alcoholRg.check(R.id.monthly_alcohol);
        } else if (alcoholStatus.equalsIgnoreCase("daily")) {
            alcoholRg.check(R.id.daily_alcohol);
        }

        if (smokingStatus.equalsIgnoreCase("no")) {
            smokingRg.check(R.id.no_smoke);
        } else if (smokingStatus.equalsIgnoreCase("yes")) {
            smokingRg.check(R.id.yes_smoke);
        }

        if (exerciseStatus.equalsIgnoreCase("never")) {
            exerciseRg.check(R.id.never_exercise);
        } else if (exerciseStatus.equalsIgnoreCase("daily")) {
            exerciseRg.check(R.id.daily_exercise);
        } else if (exerciseStatus.equalsIgnoreCase("thrice_a_week")) {
            exerciseRg.check(R.id.thrice_exercise);
        }

    }

    public void saveUserDetails() {

        if (alcoholRg.getCheckedRadioButtonId() == -1 || smokingRg.getCheckedRadioButtonId() == -1
                || exerciseRg.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, Constants.ALL_DETAILS, Toast.LENGTH_SHORT).show();
            return;
        }

        sharedPrefs.setUserAlcoholStatus(((RadioButton)findViewById(alcoholRg.getCheckedRadioButtonId())).getText().toString());
        sharedPrefs.setUserSmokingStatus(((RadioButton)findViewById(smokingRg.getCheckedRadioButtonId())).getText().toString());
        sharedPrefs.setUserExerciseStatus(((RadioButton)findViewById(exerciseRg.getCheckedRadioButtonId())).getText().toString().replace(" ", "_"));

        if (extras.equals(Constants.UPDATE_DATA)) {
            callApi();
        } else {
            startActivity(NewUserHomeActivity.newInstance(this, Constants.MEDICAL_HISTORY));
        }
    }

    private void callApi() {
        String url = "insert_new_user_data.php?user_id=" + sharedPrefs.getUserId()+ "&email="  + sharedPrefs.getUserEmail() + "&gender=" + sharedPrefs.getUserGender() +
                "&dob=" + sharedPrefs.getUserDob() + "&age=" + sharedPrefs.getUserAge() + "&sleep_time=" + sharedPrefs.getUerSleepDuration() +
                "&alcohol=" + sharedPrefs.getUserAlcoholStatus() + "&smoking=" + sharedPrefs.getUserSmokingStatus() + "&height=" + sharedPrefs.getUserHeight() +
                "&weight=" + sharedPrefs.getUserWeight() + "&excercise=" + sharedPrefs.getUserExerciseStatus() + "&non_veg=" + sharedPrefs.getUserVegStatus() +
                "&junk_food=" + sharedPrefs.getUserJunkFoodStatus() + "&water=" + sharedPrefs.getUserWaterStatus() + "&existing_disease=" + sharedPrefs.getUserDisease() +
                "&existing_disease_level=" + sharedPrefs.getUserDiseaseLevel() + "&existing_disease_other=" + sharedPrefs.getUserOtherDisease();

        ApiClient.getRequest(url, null, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (throwable.getMessage().equals(Constants.NO_INTERNET_STRING)) {
                    Toast.makeText(ShareHabitsActivity.this, "Please connect to wifi", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ShareHabitsActivity.this, "There is a error in interacting with API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (responseString.equals(Constants.MAIN_ERROR)) {
                    Toast.makeText(ShareHabitsActivity.this, "There is a error, Please try again later", Toast.LENGTH_LONG).show();
                    Log.e("TAG", "The error in main success is:" + responseString);
                } else if (responseString.equals(Constants.UPDATED)) {
                    Toast.makeText(ShareHabitsActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                    startActivity(ProfileActivity.newInstance(ShareHabitsActivity.this));
                }
            }
        });
    }

    private void setListeners() {
        backButton.setOnClickListener(view -> onBackPressed());
        nextButton.setOnClickListener(view -> saveUserDetails());
    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context, String extras) {
        Intent intent = new Intent(context, ShareHabitsActivity.class);
        intent.putExtra(Constants.SHARE_HABITS, extras);
        return intent;
    }

}