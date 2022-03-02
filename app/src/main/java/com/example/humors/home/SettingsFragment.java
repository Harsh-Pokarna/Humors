package com.example.humors.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.humors.R;
import com.example.humors.others.AboutActivity;
import com.example.humors.others.ChangePasswordActivity;

public class SettingsFragment extends Fragment {

    private FrameLayout changePassword, aboutButton;

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
        changePassword = requireView().findViewById(R.id.change_password);
        aboutButton = requireView().findViewById(R.id.about_button);

    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {
        changePassword.setOnClickListener(view -> startActivity(ChangePasswordActivity.newInstance(requireContext())));
        aboutButton.setOnClickListener(view -> startActivity(AboutActivity.newInstance(requireContext())));
    }

    private void setObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}