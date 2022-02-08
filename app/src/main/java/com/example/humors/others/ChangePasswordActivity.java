package com.example.humors.others;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.api.ApiClient;
import com.example.humors.utils.Constants;
import com.example.humors.utils.SharedPrefs;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class ChangePasswordActivity extends AppCompatActivity {

    private ImageButton backButton;
    private TextView oldPasswordTextView, newPasswordTextView, confirmPasswordTextView;
    private Button changePasswordButton;

    private String oldPassword = "", newPassword = "", confirmPassword = "";

    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

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
        backButton = findViewById(R.id.change_password_back_button);
        oldPasswordTextView = findViewById(R.id.old_passowrd);
        newPasswordTextView = findViewById(R.id.new_password);
        confirmPasswordTextView = findViewById(R.id.new_password_verify);
        changePasswordButton = findViewById(R.id.change_password_button);

        sharedPrefs = new SharedPrefs(this);
    }
    
    private void fetchData() {

    }

    private void setViews() {

    }

    private void changePassword() {
        oldPassword = oldPasswordTextView.getText().toString();
        newPassword = newPasswordTextView.getText().toString();
        confirmPassword = confirmPasswordTextView.getText().toString();

        if (newPassword.equals(confirmPassword)) {
            String url = "user_change_password.php?p?user_email=" + sharedPrefs.getUserEmail() + "&password=" + oldPassword + "&new_password=" + newPassword;

            ApiClient.getRequest(url, null, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.e("TAG", "There is a error: " + throwable.getMessage());
                    if (throwable.getMessage().equals(Constants.NO_INTERNET_STRING)) {
                        Toast.makeText(ChangePasswordActivity.this, "Please connect with wifi/data", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(ChangePasswordActivity.this, "There is a error in interacting with API", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString.equals(Constants.MAIN_SUCCESS)) {
                        Toast.makeText(ChangePasswordActivity.this, "Password changed", Toast.LENGTH_SHORT).show();
                    } else if (responseString.equals(Constants.WRONG_PASSWORD)) {
                        oldPasswordTextView.setError("Wrong password");
                    } else {
                        Toast.makeText(ChangePasswordActivity.this, "There is some error", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        } else if(oldPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")) {
            oldPasswordTextView.setError("Please enter old password");
        }
        else {
            confirmPasswordTextView.setError("Password don't match");
        }
    }

    private void setListeners() {
        backButton.setOnClickListener(view -> onBackPressed());
        changePasswordButton.setOnClickListener(view -> changePassword());
    }

    private void setObservers() {

    }

    public static Intent newInstance(Context con) {
        return new Intent(con, ChangePasswordActivity.class);
    }
}