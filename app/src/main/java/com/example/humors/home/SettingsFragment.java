package com.example.humors.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.humors.R;
import com.example.humors.database.SQLiteDatabaseHandler;
import com.example.humors.others.AboutActivity;
import com.example.humors.others.AddDeviceActivity;
import com.example.humors.others.ChangePasswordActivity;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    private FrameLayout changePassword, aboutButton, addDeviceBtn;
    private TextView fakeText;

    private SQLiteDatabaseHandler sqLiteDatabaseHandler;

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
        setTextView();
    }

    private void initialiseVariables() {
        changePassword = requireView().findViewById(R.id.change_password);
        aboutButton = requireView().findViewById(R.id.about_button);
        addDeviceBtn = requireView().findViewById(R.id.add_device_btn);
        fakeText = requireView().findViewById(R.id.fake_text);

        sqLiteDatabaseHandler = new SQLiteDatabaseHandler(requireContext());

    }

    private void setTextView() {
        ArrayList<String> steps = sqLiteDatabaseHandler.getSteps();
        ArrayList<String> date = sqLiteDatabaseHandler.getDate();
        ArrayList<String> id = sqLiteDatabaseHandler.getIds();

        ArrayList<String> entry = new ArrayList<>();

        for (int i = 0; i < steps.size(); i++) {
            entry.add("Id: " + id.get(i) + " Date: " + date.get(i) + " Steps: " + steps.get(i) + "\n");
        }

        for (int i = 0; i < entry.size(); i++) {
            fakeText.append(entry.get(i));
        }

    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {
        changePassword.setOnClickListener(view -> startActivity(ChangePasswordActivity.newInstance(requireContext())));
        aboutButton.setOnClickListener(view -> startActivity(AboutActivity.newInstance(requireContext())));
        addDeviceBtn.setOnClickListener(view -> startActivity(AddDeviceActivity.newInstance(requireContext(), "")));
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