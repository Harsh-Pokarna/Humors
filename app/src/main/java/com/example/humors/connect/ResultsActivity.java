package com.example.humors.connect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.humors.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;

public class ResultsActivity extends AppCompatActivity {

    private List<DonutSection> healthStatus = new ArrayList<>();
    private DonutProgressView donutProgressView;
    private FrameLayout bottomSheet;

    private BottomSheetBehavior bottomSheetBehavior = new BottomSheetBehavior();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

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

        donutProgressView = findViewById(R.id.health_status_your_results);
        bottomSheet = findViewById(R.id.bottom_sheet_your_results);

        healthStatus.add(new DonutSection("user_health", Color.parseColor("#FAFF00"), 75.0F));
        donutProgressView.setCap(100);
        donutProgressView.submitData(healthStatus);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels - 400);
        bottomSheetBehavior.setPeekHeight(100);

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
        return new Intent(context, ResultsActivity.class);
    }
}