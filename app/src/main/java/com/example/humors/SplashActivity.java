package com.example.humors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.humors.auth.WelcomeActivity;
import com.example.humors.home.HomeActivity;
import com.example.humors.utils.SharedPrefs;

public class SplashActivity extends AppCompatActivity {

    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    private void init() {
//        startActivity(WelcomeActivity.newInstance(getApplicationContext()));
////        startActivity(WelcomeActivity.newInstance(getApplicationContext()));

        if (!sharedPrefs.getUserDisease().equals("")) {
            startActivity(HomeActivity.newInstance(this));
        } else {
            startActivity(WelcomeActivity.newInstance(this));
        }
        finish();
    }

}