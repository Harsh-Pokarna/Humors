package com.example.humors.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.humors.R;

public class BreatheTestActivity extends AppCompatActivity {

    int maxTime = 5;
    int counter = 0;

    private TextView breatheTimerTextView, mainText, titleText;

    private LottieAnimationView blowAnimation, ringAnimation;

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
        blowAnimation = findViewById(R.id.blow_animation);
        mainText = findViewById(R.id.blow_text);
        ringAnimation = findViewById(R.id.ring_animation);
        titleText = findViewById(R.id.breath_test_title);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                counter++;
                if (counter == 5) {
                    mainText.setText("Blow out");
                    ringAnimation.reverseAnimationSpeed();
                    blowAnimation.reverseAnimationSpeed();
                    maxTime = 20;
                } if (counter == 20) {
                    blowAnimation.setVisibility(View.INVISIBLE);
                    mainText.setVisibility(View.INVISIBLE);
                    titleText.setText("Please wait 30 seconds while we are analysing your results");
                }
                if ((maxTime - counter) > 9) {
                    breatheTimerTextView.setText("00:" + (maxTime - counter));
                }
                if ((maxTime - counter) >=0 && (maxTime - counter) <= 9) {
                    breatheTimerTextView.setText("00:0" + (maxTime - counter));
                }
                if (counter == 30) {
                    startActivity(ResultsActivity.newInstance(BreatheTestActivity.this));
                }
                handler.postDelayed(this, 1000);
            }
        }, 1000);

    }

    private void fetchData() {

    }

    private void setViews() {
        ringAnimation.reverseAnimationSpeed();
        blowAnimation.reverseAnimationSpeed();
    }

    private void setListeners() {

    }

    private void setObservers() {

    }
}