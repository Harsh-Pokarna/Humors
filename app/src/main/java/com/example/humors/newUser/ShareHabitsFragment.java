package com.example.humors.newUser;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.example.humors.R;

import nl.joery.timerangepicker.TimeRangePicker;

public class ShareHabitsFragment extends Fragment {

    private ImageButton backButton;
    private TimeRangePicker userSleepSchedule;
    private ScrollView scrollView;

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
        backButton = requireView().findViewById(R.id.share_habits_back_button);
        userSleepSchedule = requireView().findViewById(R.id.user_sleep_schedule);
        scrollView = requireView().findViewById(R.id.share_habits_scroll_view);
    }

    private void fetchData() {

    }

    private void setViews() {

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {
        backButton.setOnClickListener(view -> requireActivity().getSupportFragmentManager().popBackStack());


    }

    private void setObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share_habits, container, false);
    }
}