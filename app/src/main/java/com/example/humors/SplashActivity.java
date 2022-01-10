package com.example.humors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.humors.auth.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    private void init() {
        startActivity(LoginActivity.newInstance(getApplicationContext()));
        finish();
    }

}