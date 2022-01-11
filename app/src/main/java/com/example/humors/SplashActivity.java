package com.example.humors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.humors.auth.LoginActivity;
import com.example.humors.auth.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    private void init() {
        startActivity(WelcomeActivity.newInstance(getApplicationContext()));
//        startActivity(WelcomeActivity.newInstance(getApplicationContext()));
        finish();
    }

}