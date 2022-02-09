package com.example.humors.home;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.humors.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class StepCounterFragment extends Fragment {

    private BarChart stepsChart, caloriesChart;
    private FrameLayout fl;

    private List<BarEntry> stepsEntries = new ArrayList<>();
    private List<String> xAxisLabels = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        stepsChart = requireView().findViewById(R.id.steps_chart);
        caloriesChart = requireView().findViewById(R.id.calories_chart);
        fl = requireView().findViewById(R.id.fl);

    }

    private void setCommonChartAttributes(BarChart barChart) {

        barChart.setPinchZoom(false);
        barChart.setDragEnabled(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getXAxis().setDrawAxisLine(false);
        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);
    }

    private void setCharts() {

        setCommonChartAttributes(stepsChart);
        setCommonChartAttributes(caloriesChart);

        xAxisLabels.add("11/1");
        xAxisLabels.add("11/1");
        xAxisLabels.add("12/1");
        xAxisLabels.add("13/1");
        xAxisLabels.add("14/1");
        xAxisLabels.add("15/1");
        xAxisLabels.add("16/1");
        xAxisLabels.add("17/1");

        stepsEntries.add(new BarEntry(1, 1000));
        stepsEntries.add(new BarEntry(2, 7000));
        stepsEntries.add(new BarEntry(3, 4000));
        stepsEntries.add(new BarEntry(4, 5000));
        stepsEntries.add(new BarEntry(5, 100));
        stepsEntries.add(new BarEntry(6, 3000));
        stepsEntries.add(new BarEntry(7, 10000));

        BarDataSet barDataSet1 = new BarDataSet(stepsEntries, "");
        barDataSet1.setColor(Color.parseColor("#1B55EC"));
        barDataSet1.setLabel(null);
        BarData barData = new BarData(barDataSet1);
        barData.setBarWidth((float) 0.3);

        BarDataSet barDataSet2 = new BarDataSet(stepsEntries, "");
        barDataSet2.setColor(getResources().getColor(R.color.red));
        barDataSet2.setLabel(null);
        BarData barData2 = new BarData(barDataSet2);
        barData2.setBarWidth((float) 0.3);

        stepsChart.setData(barData);
        stepsChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));
        stepsChart.setBackground(getResources().getDrawable(R.drawable.data_input_bg));
        stepsChart.invalidate();

        caloriesChart.setData(barData2);
        caloriesChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));
        caloriesChart.setBackground(getResources().getDrawable(R.drawable.calorie_chart_bg));
        caloriesChart.invalidate();
    }

    private void fetchData() {

    }

    private void setViews() {
        setCharts();

    }

    private void setListeners() {

    }

    private void setObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_step_counter, container, false);
    }
}