package com.example.humors.home;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.database.SQLiteDatabaseHandler;
import com.example.humors.services.StepCounterService;
import com.example.humors.services.StepsServiceJobScheduler;
import com.example.humors.utils.GlobalVariables;
import com.example.humors.utils.SharedPrefs;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;

import java.lang.reflect.Array;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import kotlinx.coroutines.Job;

@RequiresApi(api = Build.VERSION_CODES.O)
public class StepCounterFragment extends Fragment implements SensorEventListener {

    private BarChart stepsChart, caloriesChart;
    private FrameLayout fl;
    private TextView stepCountTextView, distanceCountTextView, caloriesCountTextView;
    private LinearLayout dataLayout;

    private List<BarEntry> stepsEntries = new ArrayList<>();
    private List<String> xAxisLabels = new ArrayList<>();

    private SensorManager sensorManager;
    private float totalSteps = 0f, previousSteps;
    private Sensor stepSensor = null;

    private SharedPrefs sharedPrefs;

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Intent intent;

    private SQLiteDatabaseHandler sqLiteDatabaseHandler;

    private static long ONE_DAY_INTERVAL = 24 * 60 * 60 * 1000L;

    private int JOB_ID = 1;

    FitnessOptions
            fitnessOptions = FitnessOptions.builder()
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ).build();

    LocalDateTime end = LocalDateTime.now();
    LocalDateTime start = end.minusMonths(1);
    long endSeconds = end.atZone(ZoneId.systemDefault()).toEpochSecond();
    long startSeconds = start.atZone(ZoneId.systemDefault()).toEpochSecond();

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
//        setService();
        setViews();
        setListeners();
        setObservers();
    }

    private void initialiseVariables() {
        stepsChart = requireView().findViewById(R.id.steps_chart);
        caloriesChart = requireView().findViewById(R.id.calories_chart);
        stepCountTextView = requireView().findViewById(R.id.step_count);
        dataLayout = requireView().findViewById(R.id.data_layout);
        fl = requireView().findViewById(R.id.fl);
        distanceCountTextView = requireView().findViewById(R.id.distance_count);
        caloriesCountTextView = requireView().findViewById(R.id.calorie_count);
        sqLiteDatabaseHandler = new SQLiteDatabaseHandler(requireContext());

        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

//        if (stepSensor == null) {
//            Toast.makeText(requireContext(), "No sensor detected", Toast.LENGTH_SHORT).show();
//            dataLayout.setVisibility(View.GONE);
//            stepCountTextView.setText("--");
//        } else {
//            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_FASTEST);
//        }

        sharedPrefs = new SharedPrefs(requireContext());
//        previousSteps = sharedPrefs.getPreviousSteps();
//        Log.e("TAG", "Your previous day steps were: " + previousSteps);
    }



//    private void backgroundUsingJobScheduler() {
////        val jobInfo = new JobInfo.Builder(123, new ComponentName(requireContext(), StepCounterService.class));
//        JobInfo jobInfo = new JobInfo.Builder(JOB_ID, new ComponentName(requireContext(), StepsServiceJobScheduler.class))
//                .setPeriodic(ONE_DAY_INTERVAL).setPersisted(true).build();
//
//    }

    private void setService() {
        alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(requireContext(), StepCounterService.class);
//        pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, 0);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast
                    (requireContext(), 0, intent, PendingIntent.FLAG_MUTABLE);
        } else {
            pendingIntent = PendingIntent.getBroadcast
                    (requireContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 21);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent), pendingIntent);
//        } else {
//            alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//        }
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
        barChart.setScaleEnabled(false);
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
        getSteps();
//        stepCountTextView.setText("" + (sharedPrefs.getNewSteps() - previousSteps));
    }

    private void getSteps() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DataReadRequest readRequest = new DataReadRequest.Builder()
                    .aggregate(DataType.AGGREGATE_STEP_COUNT_DELTA)
                    .aggregate(DataType.AGGREGATE_CALORIES_EXPENDED)
                    .aggregate(DataType.TYPE_DISTANCE_DELTA)
                    .setTimeRange(startSeconds, endSeconds, TimeUnit.SECONDS)
                    .bucketByTime(1, TimeUnit.DAYS)
                    .build();

            GoogleSignInAccount account = GoogleSignIn.getAccountForExtension(requireContext(), fitnessOptions);
            Fitness.getHistoryClient(requireContext(), account).readData(readRequest)
                    .addOnSuccessListener(dataReadResponse -> {
                        Log.e("TAG", "response data was success");
//                        responseDataTv.setText(dataReadResponse.getBuckets().toString());
                        List<Bucket> buckets = dataReadResponse.getBuckets();
                        for (int i = 0; i < buckets.size(); i++) {
                            Bucket bucket = buckets.get(i);
                            List<DataSet> dataSets = bucket.getDataSets();
                            for (int j = 0; j < dataSets.size(); j++) {
                                DataSet dataSet = dataSets.get(j);
                                dumpDataSet(dataSet);
                            }
                        }

                    }).addOnFailureListener(e -> {
                Log.e("TAG", "Fetching Data failed: " + e.getMessage());
//                responseDataTv.setText("Error: " + e.getMessage());
            });

        }
    }

    private void dumpDataSet(DataSet dataSet) {
        List<DataPoint> dataPoints = dataSet.getDataPoints();
        Log.e("TAG", "the size of datapoints is :" + dataPoints.size());

        for (DataPoint dp : dataSet.getDataPoints()) {
            Log.e("TAG","Data point:");
            Log.e("TAG","Type: " + dp.getDataType().getName());
            Log.e("TAG","Start: " + getStartTimeString(dp));
            Log.e("TAG","End: " + getEndTimeString(dp));
            for (Field field : dp.getDataType().getFields()) {
                Log.e("TAG","Field: " + field.getName() + "Value: " + dp.getValue(field));
            }
        }

    }

    private String getStartTimeString(DataPoint dataPoint) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Instant.ofEpochSecond(dataPoint.getStartTime(TimeUnit.SECONDS))
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime().toString();
        }
        return "";
    }

    private String getEndTimeString(DataPoint dataPoint) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Instant.ofEpochSecond(dataPoint.getEndTime(TimeUnit.SECONDS))
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime().toString();
        }
        return "";
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
//        totalSteps = sensorEvent.values[0];
//        GlobalVariables.totalSteps = totalSteps;
//        stepCountTextView.setText("" + (totalSteps - previousSteps));
//        changeDistanceAndCalories(totalSteps - previousSteps);
    }

    private void changeDistanceAndCalories(Float steps) {

        double weight = Double.parseDouble(sharedPrefs.getUserWeight()); // kg

        double height = Double.parseDouble(sharedPrefs.getUserHeight()); // cm

        double stepsCount = steps;

//Don't edit below this


        final double walkingFactor = 0.57;

        double CaloriesBurnedPerMile;

        double strip;

        double stepCountMile; // step/mile

        double conversationFactor;

        double CaloriesBurned;

        double distance;

        CaloriesBurnedPerMile = walkingFactor * (weight * 2.2);

        strip = height * 0.415;

        stepCountMile = 160934.4 / strip;

        conversationFactor = CaloriesBurnedPerMile / stepCountMile;

        CaloriesBurned = stepsCount * conversationFactor;

        distance = (stepsCount * strip) / 100000;

        caloriesCountTextView.setText(new DecimalFormat("#.00").format(CaloriesBurned));

        distanceCountTextView.setText(new DecimalFormat("#.00").format(distance));

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