package com.example.humors.auth;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.humors.R;
import com.example.humors.utils.ExtFunctions;

import java.util.Objects;

public class WelcomeFragment extends Fragment {

    private TextView registerNowButton;
    private Button mailSignInButton, googleSignInButton;

//    public static WelcomeFragment newInstance(String param1, String param2) {
//        WelcomeFragment fragment = new WelcomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

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

    private void fetchData() {

    }

    private void setCurrentFragment(Fragment fragment) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.login_fragment_container, fragment).addToBackStack(null).commit();
        }

    private void setListeners() {

        mailSignInButton.setOnClickListener(view -> setCurrentFragment(new LoginFragment()));

        registerNowButton.setOnClickListener(view -> setCurrentFragment(new CreateAccountFragment()));

    }

    private void setObservers() {

    }

    private void initialiseVariables() {
        registerNowButton = requireView().findViewById(R.id.register_now_button_welcome);
        mailSignInButton = requireView().findViewById(R.id.mail_signin_button);
        googleSignInButton = requireView().findViewById(R.id.google_signin_button);
    }

    private void setViews() {
        ExtFunctions.underlineText(registerNowButton);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

}