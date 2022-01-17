package com.example.humors.newUser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.humors.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SleepScheduleFragment extends Fragment {

    private ImageButton backButton;
    private FloatingActionButton nextButton;

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
        backButton = requireView().findViewById(R.id.sleep_schedule_back_button);
        nextButton = requireView().findViewById(R.id.sleep_schedule_next_button);
    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setCurrentFragment(Fragment fragment) {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.new_user_fragment_container, fragment)
                    .disallowAddToBackStack().commit();
        }

    private void setListeners() {
        backButton.setOnClickListener(view -> requireActivity().getSupportFragmentManager().popBackStack());
        nextButton.setOnClickListener(view -> setCurrentFragment(new ShareHabitsFragment()));
    }

    private void setObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sleep_schedule, container, false);
    }
}