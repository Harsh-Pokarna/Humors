package com.example.humors.newUser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.humors.R;
public class AddDataFragment extends Fragment {

    private ImageButton backButton;
    private EditText userNameEditText;
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
        backButton = requireView().findViewById(R.id.add_details_back_button);
        userNameEditText = requireView().findViewById(R.id.user_name_edittext_add_data);
    }

    private void fetchData() {

    }

    private void setViews() {
        userNameEditText.setError("Please enter a valid email");

    }

    private void setListeners() {
        backButton.setOnClickListener(view -> requireActivity().getSupportFragmentManager().popBackStack());

    }

    private void setObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_data, container, false);
    }
}