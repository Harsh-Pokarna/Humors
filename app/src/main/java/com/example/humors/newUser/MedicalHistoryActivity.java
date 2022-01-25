package com.example.humors.newUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.example.humors.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MedicalHistoryActivity extends AppCompatActivity {

    private CheckBox diabetesCheckbox;
    private RadioGroup diabetesRadioGroup;

    private ImageButton backButton;
    private FloatingActionButton nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);

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
        backButton = findViewById(R.id.medical_history_back_button);
        nextButton = findViewById(R.id.med_history_next_button);

        diabetesCheckbox = findViewById(R.id.diabetes_checkbox);
        diabetesRadioGroup = findViewById(R.id.diabetes_radio_grp);
    }

    private void fetchData() {

    }

    private void setViews() {
    }

    private void setListeners() {

        diabetesCheckbox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                diabetesRadioGroup.setVisibility(View.VISIBLE);
            } else {
                diabetesRadioGroup.clearCheck();
                diabetesRadioGroup.setVisibility(View.GONE);
            }
        });

        backButton.setOnClickListener(view -> onBackPressed());
        nextButton.setOnClickListener(view -> startActivity(NewUserHomeActivity.newInstance(this)));

    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, MedicalHistoryActivity.class);
    }

}