package com.example.humors.newUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.example.humors.R;
import com.example.humors.home.HomeActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MedicalHistoryActivity extends AppCompatActivity {

    private CheckBox diabetesCheckbox, respiratoryCheckbox, heartCheckbox, liverCheckbox, appendixCheckbox, otherCheckbox;
    private RadioGroup diabetesRadioGroup, respiratoryRadioGroup, heartRadioGroup, liverRadioGroup, appendixRadioGroup;
    private EditText otherDiseaseEditText;

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
        respiratoryCheckbox = findViewById(R.id.respiratory_checkbox);
        respiratoryRadioGroup = findViewById(R.id.respiratory_radio_grp);
        heartCheckbox = findViewById(R.id.heart_checkbox);
        heartRadioGroup = findViewById(R.id.heart_radio_grp);
        liverCheckbox = findViewById(R.id.liver_checkbox);
        liverRadioGroup = findViewById(R.id.liver_radio_grp);
        appendixCheckbox = findViewById(R.id.appendix_checkbox);
        appendixRadioGroup = findViewById(R.id.appendix_radio_grp);
        otherCheckbox = findViewById(R.id.other_checkbox);
        otherDiseaseEditText = findViewById(R.id.other_disease_edit_text);
    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setCheckboxListener(CheckBox checkBox, RadioGroup radioGroup) {
        checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                radioGroup.setVisibility(View.VISIBLE);
            } else {
                radioGroup.clearCheck();
                radioGroup.setVisibility(View.GONE);
            }
        });
    }

    private void setListeners() {

        setCheckboxListener(diabetesCheckbox, diabetesRadioGroup);
        setCheckboxListener(respiratoryCheckbox, respiratoryRadioGroup);
        setCheckboxListener(appendixCheckbox, appendixRadioGroup);
        setCheckboxListener(heartCheckbox, heartRadioGroup);
        setCheckboxListener(liverCheckbox, liverRadioGroup);
        otherCheckbox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                otherDiseaseEditText.setVisibility(View.VISIBLE);
            } else {
                otherDiseaseEditText.setText("");
                otherDiseaseEditText.setVisibility(View.GONE);
            }
        });

        backButton.setOnClickListener(view -> onBackPressed());
        nextButton.setOnClickListener(view -> startActivity(HomeActivity.newInstance(this)));

    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, MedicalHistoryActivity.class);
    }

}