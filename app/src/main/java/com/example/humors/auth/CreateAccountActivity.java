package com.example.humors.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.api.ApiClient;
import com.example.humors.newUser.NewUserHomeActivity;
import com.example.humors.utils.Constants;
import com.example.humors.utils.SharedPrefs;
import com.loopj.android.http.TextHttpResponseHandler;

import java.net.UnknownHostException;

import cz.msebera.android.httpclient.Header;


public class CreateAccountActivity extends AppCompatActivity {

    private SharedPrefs sharedPrefs;

    private Button createAccountButton;
    private EditText userNameEditText, userEmailEditText, userPasswordEditText, confirmPasswordEditText;

    private String userName, userEmail, userPassword, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        
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
        createAccountButton = findViewById(R.id.create_account_button);
        userNameEditText = findViewById(R.id.user_name_edittext);
        userEmailEditText = findViewById(R.id.user_email_edittext_create_account);
        userPasswordEditText = findViewById(R.id.user_password_edittext_create_account);
        confirmPasswordEditText = findViewById(R.id.confirm_password_edittext);

        sharedPrefs = new SharedPrefs(this);
    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {

        createAccountButton.setOnClickListener(view -> {
            userName = userNameEditText.getText().toString();
            userEmail = userEmailEditText.getText().toString();
            userPassword = userPasswordEditText.getText().toString();
            confirmPassword = confirmPasswordEditText.getText().toString();

            checkAndRegisterUser();
        });

    }

    private void checkAndRegisterUser() {
        if (userPassword.equals(confirmPassword)) {

            String url = "user_register.php?user_first_name=" + userName + "&user_last_name=null&user_email=" + userEmail + "&user_password=" + userPassword;
            Log.e("TAG", "url hitted:" + url);

            try {
                ApiClient.getRequest(url, null, new TextHttpResponseHandler() {

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.e("TAG", "There is a error: " + throwable.getMessage());
                        if (throwable.getMessage().equals(Constants.NO_INTERNET_STRING)) {
                            Toast.makeText(CreateAccountActivity.this, "Please connect with wifi/data", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(CreateAccountActivity.this, "There is a error in interacting with API", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {

                        if (responseString.equals(Constants.EMAIL_EXIST)) {
                            userEmailEditText.setError("Email already exists");
                            startActivity(UserLoginActivity.newInstance(CreateAccountActivity.this));
//                            sendOtp();
                        } else if (responseString.equals(Constants.SUCCESS)) {
                            sendOtp();
                        } else {
                            Toast.makeText(CreateAccountActivity.this, Constants.TRY_LATER, Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            } catch (Exception e) {
                Log.e("TAG", "Couldn't register User" + e.getMessage());
                Toast.makeText(this, "Couldn't register User", Toast.LENGTH_SHORT).show();
            }
        } else {
            confirmPasswordEditText.setError("Passwords don't match");
        }

    }

    private void sendOtp() {

        String url = "email_confirmation.php?email=" + userEmail;
        Log.e("TAG", "hitted url: " + url);

        ApiClient.getRequest(url, null, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                Log.e("TAG", "There is a error: " + throwable.getMessage());

                if (throwable.getMessage().equals(Constants.NO_INTERNET_STRING)) {
                    Toast.makeText(CreateAccountActivity.this, "Please connect with wifi/data", Toast.LENGTH_SHORT).show();
                    return;
                } else if (throwable.getMessage().equals(Constants.NOT_FOUND)) {
                    userEmailEditText.setError("Enter valid email");
                } else {
                    Toast.makeText(CreateAccountActivity.this, "There is a error in interacting with API", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                sharedPrefs.setUserEmail(userEmail);
                Log.e("TAG", "email verify response is: " + responseString);
                Log.e("TAG", "size of string is:" + responseString.length());
                if (responseString.equals(Constants.ALREADY_VERIFIED)) {
                    Toast.makeText(CreateAccountActivity.this, "Your email is already verified", Toast.LENGTH_SHORT).show();
                    startActivity(NewUserHomeActivity.newInstance(CreateAccountActivity.this, Constants.ADD_DATA));
                } else if (responseString.equals(Constants.MAIL_SUCCESS)) {
                    startActivity(EmailVerificationActivity.newInstance(CreateAccountActivity.this));
                } else {
                    Log.e("TAG", "A third case is running in create fragment");
                }
            }

        });
    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, CreateAccountActivity.class);
    }
}