package com.example.humors.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.humors.R;
import com.example.humors.newUser.NewUserHomeActivity;
import com.example.humors.utils.ExtFunctions;
import com.mukesh.OtpView;

public class ForgotPassVerifActivity extends AppCompatActivity {

    private TextView resendOtpButton;
    private OtpView otpView;
    private Button confirmOtpButton;

    private String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass_verif);

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
        resendOtpButton = findViewById(R.id.resend_otp_textview_forgot_pass);
        otpView = findViewById(R.id.otp_input_forgot_pass);
        confirmOtpButton = findViewById(R.id.confirm_otp_button_forgot_pass);
    }

    private void fetchData() {

    }

    private void setViews() {
        ExtFunctions.underlineText(resendOtpButton);
    }

    private void setListeners() {
        confirmOtpButton.setOnClickListener(view -> {
            otp = otpView.getText().toString();
            startActivity(NewUserHomeActivity.newInstance(this));
            // TODO: VERIFY OTP
        });

    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, ForgotPassVerifActivity.class);
    }
}