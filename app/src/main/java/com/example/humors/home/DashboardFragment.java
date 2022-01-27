package com.example.humors.home;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.humors.R;
import com.example.humors.connect.ResultsActivity;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class DashboardFragment extends Fragment {

    private ImageButton profileImage;

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
        Log.e("TAG", "init: ");
        initialiseVariables();
        fetchData();
        setViews();
        setListeners();
        setObservers();
    }

    private void initialiseVariables() {

        Log.e("TAG", "initialiseVariables: ");
        profileImage = requireView().findViewById(R.id.profile_button_dashboard);

        Log.e("TAG", "initialised bototm sheet behavior");

    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {

        profileImage.setOnClickListener(view -> startActivity(ProfileActivity.newInstance(requireContext())));

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