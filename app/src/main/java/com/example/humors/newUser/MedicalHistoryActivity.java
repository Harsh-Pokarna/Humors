package com.example.humors.newUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.api.ApiClient;
import com.example.humors.auth.EmailVerificationFragment;
import com.example.humors.home.HomeActivity;
import com.example.humors.utils.Constants;
import com.example.humors.utils.SharedPrefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MedicalHistoryActivity extends AppCompatActivity {

    private CheckBox diabetesCheckbox, respiratoryCheckbox, heartCheckbox, liverCheckbox, appendixCheckbox, otherCheckbox;
    private RadioGroup diabetesRadioGroup, respiratoryRadioGroup, heartRadioGroup, liverRadioGroup, appendixRadioGroup;
    private EditText otherDiseaseEditText;

    private ImageButton backButton;
    private FloatingActionButton nextButton;

    private String userDisease = "", userDiseaseLevel = "";

    private SharedPrefs sharedPrefs;

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

        sharedPrefs = new SharedPrefs(this);
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

    private void saveUserDetails() {

        if (diabetesCheckbox.isChecked()) {
            userDisease = userDisease + "diabetes";
            userDiseaseLevel = userDiseaseLevel + ((RadioButton)findViewById(diabetesRadioGroup.getCheckedRadioButtonId())).getText().toString().replace(" ", "_");
        } if (respiratoryCheckbox.isChecked()) {
            userDisease = userDisease + "respiratory";
            userDiseaseLevel = userDiseaseLevel + ((RadioButton)findViewById(respiratoryRadioGroup.getCheckedRadioButtonId())).getText().toString().replace(" ", "_");
        }if (heartCheckbox.isChecked()) {
            userDisease = userDisease + "heart";
            userDiseaseLevel = userDiseaseLevel + ((RadioButton)findViewById(heartRadioGroup.getCheckedRadioButtonId())).getText().toString().replace(" ", "_");
        } if (liverCheckbox.isChecked()) {
            userDisease = userDisease + "liver";
            userDiseaseLevel = userDiseaseLevel + ((RadioButton)findViewById(liverRadioGroup.getCheckedRadioButtonId())).getText().toString().replace(" ", "_");
        } if (appendixCheckbox.isChecked()) {
            userDisease = userDisease + "appendix";
            userDiseaseLevel = userDiseaseLevel + ((RadioButton)findViewById(appendixRadioGroup.getCheckedRadioButtonId())).getText().toString().replace(" ", "_");
        }

        sharedPrefs.setUserDisease(userDisease);
        sharedPrefs.setUserDiseaseLevel(userDiseaseLevel);
        sharedPrefs.setUserOtherDisease(otherDiseaseEditText.getText().toString());

        callApi();

    }

    private void callApi() {
        String url = "insert_new_user_data.php?user_id=" + sharedPrefs.getUserId()+ "&email="  + sharedPrefs.getUserEmail() + "&gender=" + sharedPrefs.getUserGender() +
                "&dob=" + sharedPrefs.getUserDob() + "&age=" + sharedPrefs.getUserAge() + "&sleep_time=" + sharedPrefs.getUerSleepDuration() +
                "&alcohol=" + sharedPrefs.getUserAlcoholStatus() + "&smoking=" + sharedPrefs.getUserSmokingStatus() + "&height=" + sharedPrefs.getUserHeight() +
                "&weight=" + sharedPrefs.getUserWeight() + "&excercise=" + sharedPrefs.getUserExerciseStatus() + "&non_veg=" + sharedPrefs.getUserVegStatus() +
                "&junk_food=" + sharedPrefs.getUserJunkFoodStatus() + "&water=" + sharedPrefs.getUserWaterStatus() + "&existing_disease=" + sharedPrefs.getUserDisease() +
                "&existing_disease_level=" + sharedPrefs.getUserDiseaseLevel() + "&existing_disease_other=";

        Log.e("TAG", "The hitted main url is:" + url);

        ApiClient.getRequest(url, null, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                Log.e("TAG", "There is a error: " + throwable.getMessage());

                if (throwable.getMessage().equals(Constants.NO_INTERNET_STRING)) {
                    Toast.makeText(MedicalHistoryActivity.this, "Please connect to wifi", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MedicalHistoryActivity.this, "There is a error in interacting with API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.e("TAG", "main response is: " + responseString);
                Log.e("TAG", "size of main string is:" + responseString.length());
                if (responseString.equals(Constants.EXIST)) {
                    Log.e("TAG", "into the exist block");
                    Toast.makeText(MedicalHistoryActivity.this, "Data already exists", Toast.LENGTH_SHORT).show();
                } else if (responseString.equals(Constants.MAIN_SUCCESS)) {
                    Log.e("TAG", "into the sucess block");
                    startActivity(HomeActivity.newInstance(MedicalHistoryActivity.this));
                } else if (responseString.equals(Constants.MAIN_ERROR)) {
                    Log.e("TAG", "into the error block");
                    Toast.makeText(MedicalHistoryActivity.this, "There is a error", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "The error in main success is:" + responseString);
                }
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
        nextButton.setOnClickListener(view -> saveUserDetails());

    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, MedicalHistoryActivity.class);
    }

}