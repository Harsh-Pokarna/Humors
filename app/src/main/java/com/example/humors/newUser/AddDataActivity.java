package com.example.humors.newUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.api.ApiClient;
import com.example.humors.home.HomeActivity;
import com.example.humors.home.ProfileActivity;
import com.example.humors.utils.Constants;
import com.example.humors.utils.SharedPrefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.TextHttpResponseHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.PrimitiveIterator;

import cz.msebera.android.httpclient.Header;
import kotlin.text.UStringsKt;

public class AddDataActivity extends AppCompatActivity {

    private ImageButton backButton;
    private EditText userNameEditText, userDOB, userHeightEditText, userWeightEditText, userAgeEditText;
    private FloatingActionButton nextButton;
    private RadioGroup genderRadioGroup;
    private RadioButton male, female, other;

    private SharedPrefs sharedPrefs;

    private String userGender;

    private String extras;

    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

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
        extras = getIntent().getStringExtra(Constants.ADD_DATA);
    }

    private void initialiseVariables() {
        backButton = findViewById(R.id.add_details_back_button);
        userNameEditText = findViewById(R.id.user_name_edittext_add_data);
        nextButton = findViewById(R.id.add_data_next_button);
        userDOB = findViewById(R.id.user_dob);
        genderRadioGroup = findViewById(R.id.gender_radio_grp);
        male = findViewById(R.id.male_radio_button);
        female = findViewById(R.id.female_radio_button);
        other = findViewById(R.id.other_gender_radio_button);
        userHeightEditText = findViewById(R.id.user_height);
        userWeightEditText = findViewById(R.id.user_weight);
        userAgeEditText = findViewById(R.id.user_age_edittext_add_data);

        sharedPrefs = new SharedPrefs(this);
        userGender = sharedPrefs.getUserGender();
    }

    private void fetchData() {

    }

    private void setViews() {
        userNameEditText.setText(sharedPrefs.getUserName());
        userDOB.setText(sharedPrefs.getUserDob());
        userHeightEditText.setText(sharedPrefs.getUserHeight());
        userWeightEditText.setText(sharedPrefs.getUserWeight());
        userAgeEditText.setText(sharedPrefs.getUserAge());

        if (userGender.equalsIgnoreCase("male")) {
            male.setChecked(true);
        } else if (userGender.equalsIgnoreCase("female")) {
            female.setChecked(true);
        } else if (userGender.equalsIgnoreCase("other")) {
            other.setChecked(true);
        }
    }

    private void saveUserData() {

        if (userNameEditText.getText().toString().equals("") || userDOB.getText().toString().equals("") ||
            userHeightEditText.getText().toString().equals("") || userWeightEditText.getText().toString().equals("")
            || genderRadioGroup.getCheckedRadioButtonId() == -1 || userAgeEditText.getText().toString().equals("")) {
            Toast.makeText(this, Constants.ALL_DETAILS, Toast.LENGTH_SHORT).show();
            return;
        }

        sharedPrefs.setUserName(userNameEditText.getText().toString());
        sharedPrefs.setUserDob(userDOB.getText().toString());
        sharedPrefs.setUserAge(userAgeEditText.getText().toString());
        sharedPrefs.setUserGender(((RadioButton)findViewById(genderRadioGroup.getCheckedRadioButtonId())).getText().toString());
        sharedPrefs.setUserHeight(userHeightEditText.getText().toString());
        sharedPrefs.setUserWeight(userWeightEditText.getText().toString());

        if (extras.equals(Constants.UPDATE_DATA)) {
//            Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
            callApi();
        } else {
            startActivity(NewUserHomeActivity.newInstance(this, Constants.SLEEP_SCHEDULE));
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

                Log.e("TAG", "There is a error: " + throwable.getMessage());

                if (throwable.getMessage().equals(Constants.NO_INTERNET_STRING)) {
                    Toast.makeText(AddDataActivity.this, "Please connect to wifi", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddDataActivity.this, "There is a error in interacting with API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.e("TAG", "main response is: " + responseString);
                Log.e("TAG", "size of main string is:" + responseString.length());

                if (responseString.equals(Constants.MAIN_ERROR)) {
                    Toast.makeText(AddDataActivity.this, "There is a error, Please try again later", Toast.LENGTH_LONG).show();
                    Log.e("TAG", "The error in main success is:" + responseString);
                } else if (responseString.equals(Constants.UPDATED)) {
                    Toast.makeText(AddDataActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                    startActivity(ProfileActivity.newInstance(AddDataActivity.this));
                }
            }
        });
    }

    private void setListeners() {
        backButton.setOnClickListener(view -> onBackPressed());
        nextButton.setOnClickListener(view -> saveUserData());

        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy", Locale.US);
            userDOB.setText(dateFormat.format(myCalendar.getTime()));
        };
        userDOB.setOnClickListener(view -> new DatePickerDialog(AddDataActivity.this, date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show());
    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context, String extras) {
        Intent intent = new Intent(context, AddDataActivity.class);
        intent.putExtra(Constants.ADD_DATA, extras);
        return intent;
    }
}