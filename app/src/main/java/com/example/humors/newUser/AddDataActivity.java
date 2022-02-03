package com.example.humors.newUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.utils.Constants;
import com.example.humors.utils.SharedPrefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.PrimitiveIterator;

public class AddDataActivity extends AppCompatActivity {

    private ImageButton backButton;
    private EditText userNameEditText, userDOB, userHeightEditText, userWeightEditText;
    private FloatingActionButton nextButton;
    private RadioGroup genderRadioGroup;
    private RadioButton male, female, other;

    private SharedPrefs sharedPrefs;

    private String userGender;

    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

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
            || genderRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, Constants.ALL_DETAILS, Toast.LENGTH_SHORT).show();
            return;
        }

        sharedPrefs.setUserName(userNameEditText.getText().toString());
        sharedPrefs.setUserDob(userDOB.getText().toString());
        sharedPrefs.setUserGender(((RadioButton)findViewById(genderRadioGroup.getCheckedRadioButtonId())).getText().toString());
        sharedPrefs.setUserHeight(userHeightEditText.getText().toString());
        sharedPrefs.setUserWeight(userWeightEditText.getText().toString());

        startActivity(NewUserHomeActivity.newInstance(this));
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

    public static Intent newInstance(Context context) {
        return new Intent(context, AddDataActivity.class);
    }
}