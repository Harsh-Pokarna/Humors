package com.example.humors.connect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.humors.R;
import com.example.humors.home.HomeActivity;
import com.example.humors.home.ProfileActivity;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;

public class ResultsActivity extends AppCompatActivity {

    private List<DonutSection> healthStatus = new ArrayList<>();
    private List<DonutSection> sleepStatus = new ArrayList<>();
    private List<DonutSection> metabolismStatus = new ArrayList<>();

    private DonutProgressView healthProgressView, sleepProgressView, metabolismProgressView;
    private LinearLayout outerRing1, outerRing2, outerRing3;

    private FrameLayout bottomSheet;
    private ImageButton profileButton;
    private RecyclerView suggestionsRecyclerView;
    private TextView dashboardButton;

    private final SuggestionsAdapter suggestionsAdapter = new SuggestionsAdapter();

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
        healthProgressView = findViewById(R.id.health_status_graph);
        sleepProgressView = findViewById(R.id.sleep_statistics_graph);
        metabolismProgressView = findViewById(R.id.metabolism_statistics_graph);
        outerRing1 = findViewById(R.id.outerRing);
        outerRing2 = findViewById(R.id.outerRing2);
        outerRing3 = findViewById(R.id.outerRing3);
        bottomSheet = findViewById(R.id.bottom_sheet_your_results);
        profileButton = findViewById(R.id.profile_button);
        suggestionsRecyclerView = findViewById(R.id.suggestions_recycler_view);
        dashboardButton = findViewById(R.id.dashboard_button);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        Log.e("TAG", String.valueOf(Resources.getSystem().getDisplayMetrics().heightPixels));
        bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels - 750);

        setProgressViews();

    }

    private void setProgressViews() {

        healthStatus.add(new DonutSection("user_health", Color.parseColor("#FAFF00"), 75.0F));
        healthProgressView.setCap(100);
        healthProgressView.submitData(healthStatus);
        outerRing1.getBackground().setAlpha(70);

        sleepStatus.add(new DonutSection("user_sleep", Color.parseColor("#FAFF00"), 50.0F));
        sleepProgressView.setCap(100);
        sleepProgressView.submitData(sleepStatus);
        outerRing2.getBackground().setAlpha(70);

        metabolismStatus.add(new DonutSection("user_metabolism", Color.parseColor("#FAFF00"), 50.0F));
        metabolismProgressView.setCap(100);
        metabolismProgressView.submitData(metabolismStatus);
        outerRing3.getBackground().setAlpha(70);

    }

    private void fetchData() {

    }

    private void setViews() {

        suggestionsRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        suggestionsRecyclerView.setAdapter(suggestionsAdapter);

    }

    private void setListeners() {
        profileButton.setOnClickListener(view -> startActivity(ProfileActivity.newInstance(this)));
        dashboardButton.setOnClickListener(view -> startActivity(HomeActivity.newInstance(this)));
    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, ResultsActivity.class);
    }
}