package com.example.humors.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.api.ApiClient;
import com.example.humors.newUser.NewUserHomeActivity;
import com.example.humors.utils.Constants;
import com.example.humors.utils.ExtFunctions;
import com.example.humors.utils.SharedPrefs;
import com.loopj.android.http.TextHttpResponseHandler;
import com.mukesh.OtpView;

import cz.msebera.android.httpclient.Header;

public class EmailVerificationActivity extends AppCompatActivity {

    private SharedPrefs sharedPrefs;

    private TextView resendOtpButton;
    private OtpView otpView;
    private Button confirmOtp;

    private String userEmail;

    private String otp;

    private Handler handler = new Handler();
    private int maxTime = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        
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
        resendOtpButton = findViewById(R.id.resend_otp_textview_email_verify);
        otpView = findViewById(R.id.otp_input_email_verify);
        confirmOtp = findViewById(R.id.confirm_otp_button_email_verify);

        sharedPrefs = new SharedPrefs(this);
        userEmail = sharedPrefs.getUserEmail();
    }

    private void fetchData() {

    }


    private void setViews() {
        ExtFunctions.underlineText(resendOtpButton);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000);

    }

    private void setListeners() {

        confirmOtp.setOnClickListener(view -> {
            otp = otpView.getText().toString();
            verifyOtp(otp);
        });

        otpView.setOtpCompletionListener(otp -> ExtFunctions.hideKeyboard(this));

    }

    private void verifyOtp(String otp) {

        String url = "confirm_email_match_otp.php?email=" + userEmail +  "&otp=" + otp;
        Log.e("TAG", "the hitted url is: " + url);
//        String url = "confirm_email_match_otp.php?email=sagarhosur814@gmail.com&otp=109002";

        ApiClient.getRequest(url, null, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("TAG", "There is a error: " + throwable.getMessage());
                if (throwable.getMessage().equals(Constants.NO_INTERNET_STRING)) {
                    Toast.makeText(EmailVerificationActivity.this, "Please connect with wifi/data", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(EmailVerificationActivity.this, "There is a error in interacting with API", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.e("TAG", "the response from otp api is: " + responseString);
                if (responseString.equals(Constants.WRONG_OTP)) {
                    otpView.setText(null);
                    Toast.makeText(EmailVerificationActivity.this, "Invalid OTP Entered", Toast.LENGTH_SHORT).show();
                } else if(responseString.equals(Constants.SUCCESS_OTP)){
                    sharedPrefs.setUserStatus(1);
                    startActivity(NewUserHomeActivity.newInstance(EmailVerificationActivity.this));
                    finish();
                } else {
                    Toast.makeText(EmailVerificationActivity.this, Constants.TRY_LATER, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, EmailVerificationActivity.class);
    }
}