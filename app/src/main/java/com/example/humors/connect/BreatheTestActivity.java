package com.example.humors.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.humors.R;

public class BreatheTestActivity extends AppCompatActivity {

    int maxTime = 5;
    int counter = 0;

    private TextView breatheTimerTextView;

    public static Intent newInstance(Context context) {
        return new Intent(context, BreatheTestActivity.class);
    }

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

        breatheTimerTextView = findViewById(R.id.timer_breathe_test);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                counter++;
                if (counter == 6) {
                    startActivity(ResultsActivity.newInstance(BreatheTestActivity.this));
                    return;
                }
                breatheTimerTextView.setText("00:0" + (maxTime - counter));
                handler.postDelayed(this, 1000);
            }
        }, 1000);

    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {

    }

    private void setObservers() {

    }
}