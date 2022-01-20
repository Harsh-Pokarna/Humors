    package com.example.humors.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.humors.R;

public class BreatheTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe_test);

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
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(ResultsActivity.newInstance(this));
        }, 5000);

    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {

    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return  new Intent(context, BreatheTestActivity.class);
    }
}