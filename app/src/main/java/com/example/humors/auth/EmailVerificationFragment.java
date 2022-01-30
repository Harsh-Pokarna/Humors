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

        String url = "confirm_email_match_otp.php?email=" + userEmail +  "&otp=" + otp;
        Log.e("TAG", "the hitted url is: " + url);
//        String url = "confirm_email_match_otp.php?email=sagarhosur814@gmail.com&otp=109002";

        ApiClient.getRequest(url, null, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("TAG", "There is a error: " + throwable.getMessage());
                if (throwable.getMessage().equals(Constants.NO_INTERNET_STRING)) {
                    Toast.makeText(requireContext(), "Please connect with wifi/data", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(requireContext(), "There is a error in interacting with API", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.e("TAG", "the response from otp api is: " + responseString);
                if (responseString.equals(Constants.WRONG_OTP)) {
                    otpView.setText(null);
                    Toast.makeText(requireContext(), "Invalid OTP Entered", Toast.LENGTH_SHORT).show();
                } else if(responseString.equals(Constants.SUCCESS_OTP)){
                    sharedPrefs.setUserStatus(1);
                    startActivity(NewUserHomeActivity.newInstance(requireContext()));
                    requireActivity().finish();
                } else {
                    Toast.makeText(requireContext(), Constants.TRY_LATER, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_email_verification, container, false);
    }
}