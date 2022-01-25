package com.example.humors.others;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.humors.R;

public class ChangePasswordActivity extends AppCompatActivity {

    private ImageButton backButton;

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
    }
    
    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {
        backButton.setOnClickListener(view -> onBackPressed());

    }

    private void setObservers() {

    }

    public static Intent newInstance(Context con) {
        return new Intent(con, ChangePasswordActivity.class);
    }
}