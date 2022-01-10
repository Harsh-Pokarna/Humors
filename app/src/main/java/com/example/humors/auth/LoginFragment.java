package com.example.humors.auth;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.humors.R;
import com.example.humors.utils.ExtFunctions;

public class LoginFragment extends Fragment {

    private TextView registerNowButton, forgotPasswordButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
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
                    .replace(R.id.login_fragment_container, fragment)
                    .disallowAddToBackStack().commit();
        }

    private void initialiseVariables() {
        registerNowButton = getView().findViewById(R.id.register_now_button_login);
        forgotPasswordButton = requireView().findViewById(R.id.forgot_password_button);
    }

    private void fetchData() {

    }

    private void setViews() {
        ExtFunctions.underlineText(registerNowButton);
    }

    private void setListeners() {

        registerNowButton.setOnClickListener(view -> setCurrentFragment(new CreateAccountFragment()));

        forgotPasswordButton.setOnClickListener(view -> setCurrentFragment(new ForgotPasswordFragment()));

    }

    private void setObservers() {

    }
}