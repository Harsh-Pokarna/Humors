package com.example.humors.home;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.connect.BreatheTestActivity;
import com.example.humors.connect.ResultsActivity;
import com.example.humors.connect.SearchingActivity;
import com.example.humors.utils.GlobalVariables;
import com.example.humors.utils.SharedPrefs;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;

public class DashboardFragment extends Fragment {

    private ImageButton profileImage;
    private LinearLayout takeReading;
    private LineChart lineChart;
    private BottomNavigationView dashboardNavBar;
    private TextView dashboardButton, connectionStatusTv;

    private List<DonutSection> healthStatus = new ArrayList<>();
    private List<DonutSection> sleepStatus = new ArrayList<>();
    private List<DonutSection> metabolismStatus = new ArrayList<>();

    private DonutProgressView healthProgressView, sleepProgressView, metabolismProgressView;
    private LinearLayout outerRing1, outerRing2, outerRing3;

    private List<Entry> entries = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("TAG", "onViewCreated");
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

        profileImage = requireView().findViewById(R.id.profile_button_dashboard);
        lineChart = requireView().findViewById(R.id.line_chart);
        dashboardNavBar = requireView().findViewById(R.id.dashboard_bottom_nav_bar);
        takeReading = requireView().findViewById(R.id.take_reading_button);
        healthProgressView = requireView().findViewById(R.id.health_status_graph);
        sleepProgressView = requireView().findViewById(R.id.sleep_statistics_graph);
        metabolismProgressView = requireView().findViewById(R.id.metabolism_statistics_graph);
        dashboardButton = requireView().findViewById(R.id.dashboard_button);
        outerRing1 = requireView().findViewById(R.id.outerRing);
        outerRing2 = requireView().findViewById(R.id.outerRing2);
        outerRing3 = requireView().findViewById(R.id.outerRing3);
        connectionStatusTv = requireView().findViewById(R.id.connection_status_tv);

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

    private void setGraph(String text) {

        entries.clear();
        entries.add(new Entry(1, 65));
        entries.add(new Entry(2, 75));
        entries.add(new Entry(3, 90));
        entries.add(new Entry(4, 85));
        entries.add(new Entry(5, 95));
        LineDataSet lineDataSet = new LineDataSet(entries, text);
        lineChart.setData(new LineData(lineDataSet));
        lineChart.setDescription(null);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawAxisLine(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.invalidate();
    }

    private void fetchData() {
    }

    private void setViews() {

        if (GlobalVariables.connectedStatus == 0) {
            connectionStatusTv.setText("Connection Status: Disconnected");
        } else if (GlobalVariables.connectedStatus == 1) {
            connectionStatusTv.setText("Connection Status: Connected");
        }

        Log.e("TAG", "The value of connected is: " + GlobalVariables.connectedStatus);

        lineChart.setPinchZoom(false);
        lineChart.setScaleEnabled(false);

        dashboardButton.setVisibility(View.GONE);

        ColorStateList iconColorStates = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked}
                },
                new int[]{
                        Color.parseColor("#000000"),
                        Color.parseColor("#FFFFFF")
                });

        dashboardNavBar.setItemIconTintList(iconColorStates);
        dashboardNavBar.setItemTextColor(iconColorStates);
        dashboardNavBar.setSelectedItemId(R.id.health_item);

        setGraph("Health Status");
        setProgressViews();
    }

    private void setListeners() {

        profileImage.setOnClickListener(view -> startActivity(ProfileActivity.newInstance(requireContext())));

        dashboardNavBar.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.health_item) {
                setGraph("Health Status");
            }
//            if (item.getItemId() == R.id.sleep_item) {
//                setGraph("Sleep Status");
//            }
            if (item.getItemId() == R.id.metabolism_item) {
                setGraph("Metabolism Status");
            }
            return true;
        });

        takeReading.setOnClickListener(view -> {
            if (GlobalVariables.connectedStatus == 1) {
                startActivity(BreatheTestActivity.newInstance(requireContext()));
            } else {
                Toast.makeText(requireContext(), "Please connect a device first", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
}