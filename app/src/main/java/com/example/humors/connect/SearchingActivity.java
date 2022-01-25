package com.example.humors.connect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.humors.R;

public class SearchingActivity extends AppCompatActivity {

    private MutableLiveData<Boolean> isConnected = new MutableLiveData<>();

    private Handler handler = new Handler();
    private int num = 0;

    private TextView searchingText, title, ownHumors;
    private AppCompatButton buyHumors;
    private LottieAnimationView searchingAnimation;

    private Runnable run = new Runnable() {
        @Override
        public void run() {
            if(!isConnected.getValue()) {
                if (num == 5) {
                    isConnected.setValue(true);
                    return;
                }
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

    private void stopCounting() {
        handler.post(null);
    }

    private void initialiseVariables() {
        isConnected.setValue(false);

        searchingText = findViewById(R.id.searching_text);
        title = findViewById(R.id.searching_activity_title);
        searchingAnimation = findViewById(R.id.searching_animation);
        ownHumors = findViewById(R.id.dont_own_humors);
        buyHumors = findViewById(R.id.buy_humors);
        startCounting();
    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {

        buyHumors.setOnClickListener(view -> {
            if (isConnected.getValue()) {
                startActivity(BreatheTestActivity.newInstance(this));
            } else {
                Toast.makeText(this, "Please wait for 5 sec", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setObservers() {
        isConnected.observe(this, this::changeData);
    }

    private void changeData(Boolean connectedStatus) {
        if (connectedStatus) {
            title.setText("Connected");
            searchingText.setText("Connected Successfully!!!");
            searchingText.setTextSize(16);
            searchingAnimation.animate().scaleX(0f).scaleY(0f).setDuration(1000);
            ownHumors.setVisibility(View.GONE);
            buyHumors.setText("START TEST");
            stopCounting();
        }
    }

    public static Intent newInstance(Context context) {
        return new Intent(context, SearchingActivity.class);
    }
}