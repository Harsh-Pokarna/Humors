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

import com.example.humors.R;
import com.example.humors.connect.ResultsActivity;
import com.example.humors.connect.SearchingActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private ImageButton profileImage, takeReading;
    private LineChart lineChart;
    private BottomNavigationView dashboardNavBar;

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
        lineChart.setDrawGridBackground(false);
        lineChart.invalidate();
    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {

        profileImage.setOnClickListener(view -> startActivity(ProfileActivity.newInstance(requireContext())));

        dashboardNavBar.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.health_item) {
                setGraph("Health Status");
            }
            if (item.getItemId() == R.id.sleep_item) {
                setGraph("Sleep Status");
            }
            if (item.getItemId() == R.id.metabolism_item) {
                setGraph("Metabolism Status");
            }
            return true;
        });

        takeReading.setOnClickListener(view -> startActivity(SearchingActivity.newInstance(requireContext())));

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