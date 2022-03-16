package com.example.humors.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.humors.R;
import com.example.humors.api.ApiClient;
import com.example.humors.home.HomeActivity;
import com.example.humors.newUser.NewUserHomeActivity;
import com.example.humors.utils.Constants;
import com.example.humors.utils.ExtFunctions;
import com.example.humors.utils.SharedPrefs;
import com.loopj.android.http.TextHttpResponseHandler;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cz.msebera.android.httpclient.Header;


public class UserLoginActivity extends AppCompatActivity {

    private TextView registerNowButton;
    private TextView forgotPasswordButton;
    private EditText userEmailTextView, userPasswordTextView;
    private Button loginButton;

    private String userEmail, userPassword;

    private SharedPrefs sharedPrefs;

    private int userId, userStatus;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

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
        registerNowButton = findViewById(R.id.register_now_button_login);
        forgotPasswordButton = findViewById(R.id.forgot_password_button);
        userEmailTextView = findViewById(R.id.user_email_edittext_login);
        userPasswordTextView = findViewById(R.id.user_password_edittext_login);
        loginButton = findViewById(R.id.login_button);

        sharedPrefs = new SharedPrefs(this);
    }

    private void fetchData() {

    }

    private void setViews() {
        ExtFunctions.underlineText(registerNowButton);
    }

    private void setListeners() {

        registerNowButton.setOnClickListener(view -> {
            setCurrentFragment(new CreateAccountFragment());
        });

        loginButton.setOnClickListener(view -> {
            userEmail = userEmailTextView.getText().toString();
            userPassword = userPasswordTextView.getText().toString();
            loginUser(userEmail, userPassword);
            // TODO: LOGIN USER
        });

        forgotPasswordButton.setOnClickListener(view -> setCurrentFragment(new ForgotPasswordFragment()));

    }

    private void loginUser(String email, String password){

        String url = "user_login.php?user_email=" + email + "&user_password=" + password;

        try {
            ApiClient.getRequest(url, null, new TextHttpResponseHandler() {

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.e("TAG", "There is a error: " + throwable.getMessage());
                    if (throwable.getMessage().equals(Constants.NO_INTERNET_STRING)) {
                        Toast.makeText(UserLoginActivity.this, "Please connect with wifi/data", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(UserLoginActivity.this, "There is a error in interacting with API", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    Log.e("TAG", "The response string in login user is: " + responseString);
                    if (responseString.equals(Constants.EMAIL_NOT_EXIST)) {
                        userEmailTextView.setError("Invalid email");
                    } else if (responseString.equals(Constants.INVALID_PASSWORD)) {
                        userPasswordTextView.setError("Invalid Password");
                    } else {
                        checkUser(responseString);
                    }
                }

            });

        } catch (Exception e) {
            Log.e("TAG", "couldn't login user");
        }
    }

    private void checkUser(String response) {

        userId = Integer.parseInt(response.split(",")[0]);
        Log.e("TAG", "userId:" + userId);
        userName = response.split(",")[2];
        Log.e("TAG", "userName:" + userName);
        userStatus = Integer.parseInt(response.split(",")[3]);
        Log.e("TAG", "userStatus:" + userStatus);

        if (userStatus == 0) {
            setCurrentFragment(new EmailVerificationFragment());
        } else if (userStatus == 1) {
            sharedPrefs.setUserStatus(1);
            sharedPrefs.setUserEmail(userEmail);
            sharedPrefs.setUserId(userId);
            sharedPrefs.setUserName(userName);

            if (sharedPrefs.getUserHeight().equals("")) {
                startActivity(NewUserHomeActivity.newInstance(this));
            } else {
                startActivity(HomeActivity.newInstance(this));
            }
            this.finish();
        }
    }

    private void setObservers() {

    }
}