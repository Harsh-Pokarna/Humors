package com.example.humors.home;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.services.StepCounterService;
import com.example.humors.utils.GlobalVariables;
import com.example.humors.utils.SharedPrefs;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StepCounterFragment extends Fragment implements SensorEventListener {

    private BarChart stepsChart, caloriesChart;
    private FrameLayout fl;
    private TextView stepCountTextView;

    private List<BarEntry> stepsEntries = new ArrayList<>();
    private List<String> xAxisLabels = new ArrayList<>();

    private SensorManager sensorManager;
    private float totalSteps = 0f, previousSteps;
    private Sensor stepSensor = null;

    private SharedPrefs sharedPrefs;

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Intent intent;

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
        stepCountTextView = requireView().findViewById(R.id.step_count);
        fl = requireView().findViewById(R.id.fl);

        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (stepSensor == null) {
            Toast.makeText(requireContext(), "No sensor detected", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("TAG", "" + sensorManager);
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }

        sharedPrefs = new SharedPrefs(requireContext());
        previousSteps = sharedPrefs.getPreviousSteps();
        Log.e("TAG", "Your previous steps were: " + previousSteps);

        setService();
    }

    private void setService() {
        alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(requireContext(), StepCounterService.class);
        pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 15);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
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
        stepCountTextView.setText("" + (sharedPrefs.getNewSteps() - previousSteps));
    }

    private void setListeners() {

    }

    private void setObservers() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sharedPrefs.setNewSteps(GlobalVariables.totalSteps);
        Log.e("TAG", "get new steps in on destroy: " + sharedPrefs.getNewSteps());

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        totalSteps = sensorEvent.values[0];
        GlobalVariables.totalSteps = totalSteps;
        stepCountTextView.setText("" + (totalSteps - previousSteps));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_step_counter, container, false);
    }
}