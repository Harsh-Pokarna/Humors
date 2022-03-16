package com.example.humors.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;

import com.example.humors.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button receiveOtpButton;
    private EditText userEmailEditText;

    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        
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
        receiveOtpButton = findViewById(R.id.receive_otp_button);
        userEmailEditText = findViewById(R.id.user_email_forgot_password);
    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {
        receiveOtpButton.setOnClickListener(view -> {
            userEmail = userEmailEditText.getText().toString();
            // TODO: CHECK USER AND GIVE OTP
            setCurrentFragment(new ForgotPassVerificationFragment());
        });

    }

    private void setObservers() {

    }
}