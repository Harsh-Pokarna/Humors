package com.example.humors.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.humors.R;

public class SearchingActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private int num = 0;

    private TextView searchingText;

    private Runnable run = new Runnable() {
        @Override
        public void run() {
            num++;
            if (num % 4 == 0)
                searchingText.setText("Searching");
            else if (num % 4 == 1)
                searchingText.setText("Searching.");
            else if (num % 4 == 2)
                searchingText.setText("Searching..");
            else if (num % 4 == 3)
                searchingText.setText("Searching...");
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        init();
    }

    private void init() {
        initialiseVariables();
        fetchData();
        setViews();
        setListeners();
        setObservers();
    }

    private void startCounting() {
        handler.post(run);
    }

    private void initialiseVariables() {
        searchingText = findViewById(R.id.searching_text);
        startCounting();
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
        return new Intent(context, SearchingActivity.class);
    }
}