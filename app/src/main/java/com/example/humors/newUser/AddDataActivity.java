package com.example.humors.newUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.humors.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddDataActivity extends AppCompatActivity {

    private ImageButton backButton;
    private EditText userNameEditText, userDOB;
    private FloatingActionButton nextButton;

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
    }

    private void fetchData() {

    }

    private void setViews() {
        userNameEditText.setError("Please enter a valid email");

    }

    private void setListeners() {
        backButton.setOnClickListener(view -> onBackPressed());
        nextButton.setOnClickListener(view -> startActivity(NewUserHomeActivity.newInstance(this)));

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                userDOB.setText(dateFormat.format(myCalendar.getTime()));
            }
        };
        userDOB.setOnClickListener(view -> new DatePickerDialog(AddDataActivity.this, R.style.DialogTheme, date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show());
    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, AddDataActivity.class);
    }
}