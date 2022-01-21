package com.example.humors.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.humors.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
        return new Intent(context, ProfileActivity.class);
    }
}