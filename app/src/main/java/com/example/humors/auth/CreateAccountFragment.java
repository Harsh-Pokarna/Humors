package com.example.humors.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.humors.R;
public class CreateAccountFragment extends Fragment {

    private Button createAccountButton;
    private EditText userNameEditText, userEmailEditText, userPasswordEditText, confirmPasswordEditText;

    private String userName, userEmail, userPassword, confirmPassword;

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
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.login_fragment_container, fragment).addToBackStack(null).commit();
        }

    private void initialiseVariables() {
        createAccountButton = requireView().findViewById(R.id.create_account_button);
        userNameEditText = requireView().findViewById(R.id.user_name_edittext);
        userEmailEditText = requireView().findViewById(R.id.user_email_edittext_create_account);
        userPasswordEditText = requireView().findViewById(R.id.user_password_edittext_create_account);
        confirmPasswordEditText = requireView().findViewById(R.id.confirm_password_edittext);
    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {

        createAccountButton.setOnClickListener(view -> {
            userName = userNameEditText.getText().toString();
            userEmail = userEmailEditText.getText().toString();
            userPassword = userPasswordEditText.getText().toString();
            confirmPassword = confirmPasswordEditText.getText().toString();

            // TODO: CHECK USER
            setCurrentFragment(new EmailVerificationFragment());
        });

    }

    private void setObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }
}