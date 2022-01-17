package com.example.humors.newUser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.example.humors.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MedicalHistoryFragment extends Fragment {

    private CheckBox diabetesCheckbox;
    private RadioGroup diabetesRadioGroup;

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

    private void setCurrentFragment(Fragment fragment) {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.new_user_fragment_container, fragment)
                    .disallowAddToBackStack().commit();
        }

    private void initialiseVariables() {
        backButton = requireView().findViewById(R.id.medical_history_back_button);

        diabetesCheckbox = requireView().findViewById(R.id.diabetes_checkbox);
        diabetesRadioGroup = requireView().findViewById(R.id.diabetes_radio_grp);
    }

    private void fetchData() {

    }

    private void setViews() {
    }

    private void setListeners() {
        diabetesCheckbox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                diabetesRadioGroup.setVisibility(View.VISIBLE);
            } else {
                diabetesRadioGroup.clearCheck();
                diabetesRadioGroup.setVisibility(View.GONE);
            }
        });

        backButton.setOnClickListener(view -> requireActivity().getSupportFragmentManager().popBackStack());
        nextButton.setOnClickListener(view -> setCurrentFragment(new NewUserHomeFragment()));

    }

    private void setObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medical_history, container, false);
    }
}