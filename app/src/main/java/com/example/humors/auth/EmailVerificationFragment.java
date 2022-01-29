package com.example.humors.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.api.ApiClient;
import com.example.humors.newUser.NewUserHomeActivity;
import com.example.humors.utils.Constants;
import com.example.humors.utils.ExtFunctions;
import com.example.humors.utils.SharedPrefs;
import com.loopj.android.http.TextHttpResponseHandler;
import com.mukesh.OtpView;

import cz.msebera.android.httpclient.Header;

public class EmailVerificationFragment extends Fragment {

    private SharedPrefs sharedPrefs;

    private TextView resendOtpButton;
    private OtpView otpView;
    private Button confirmOtp;

    private String userEmail;

    private String otp;

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
        resendOtpButton = requireView().findViewById(R.id.resend_otp_textview_email_verify);
        otpView = requireView().findViewById(R.id.otp_input_email_verify);
        confirmOtp = requireView().findViewById(R.id.confirm_otp_button_email_verify);

        sharedPrefs = new SharedPrefs(requireContext());
        userEmail = sharedPrefs.getUserEmail();
    }

    private void fetchData() {

    }


    private void setViews() {
        ExtFunctions.underlineText(resendOtpButton);

    }

    private void setListeners() {

        confirmOtp.setOnClickListener(view -> {
            otp = otpView.getText().toString();
            verifyOtp(otp);
        });

    }

    private void verifyOtp(String otp) {

    }

    private void setObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_email_verification, container, false);
    }
}