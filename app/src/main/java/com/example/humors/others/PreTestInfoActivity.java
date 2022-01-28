package com.example.humors.others;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.humors.R;
import com.example.humors.connect.SearchingActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PreTestInfoActivity extends AppCompatActivity {

    private ImageButton backButton;
    private FloatingActionButton nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_test_info);

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
        backButton = findViewById(R.id.pre_test_info_back_button);
        nextButton = findViewById(R.id.pre_test_info_next_button);

    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {
        backButton.setOnClickListener(view -> onBackPressed());
        nextButton.setOnClickListener(view -> startActivity(SearchingActivity.newInstance(this)));

    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, PreTestInfoActivity.class);
    }
}