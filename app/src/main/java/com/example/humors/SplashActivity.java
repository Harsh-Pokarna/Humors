package com.example.humors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

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
        sharedPrefs = new SharedPrefs(this);
//        startActivity(WelcomeActivity.newInstance(getApplicationContext()));
//        startActivity(WelcomeActivity.newInstance(getApplicationContext()));

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (!sharedPrefs.getUserDisease().equals("")) {
                startActivity(HomeActivity.newInstance(SplashActivity.this));
            } else {
                startActivity(WelcomeActivity.newInstance(SplashActivity.this));
            }
            finish();
        }, 1500);
    }

}