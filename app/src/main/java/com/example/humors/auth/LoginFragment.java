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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.api.ApiClient;
import com.example.humors.home.HomeActivity;
import com.example.humors.newUser.NewUserHomeActivity;
import com.example.humors.pojos.Test;
import com.example.humors.utils.Constants;
import com.example.humors.utils.ExtFunctions;
import com.example.humors.utils.SharedPrefs;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginFragment extends Fragment {

    private TextView registerNowButton;
    private TextView forgotPasswordButton;
    private EditText userEmailTextView, userPasswordTextView;
    private Button loginButton;

    private String userEmail, userPassword;

    private SharedPrefs sharedPrefs;

    private int userId, userStatus;
    private String userName;


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
                    .replace(R.id.login_fragment_container, fragment).addToBackStack(null).commit();
        }

    private void initialiseVariables() {
        registerNowButton = getView().findViewById(R.id.register_now_button_login);
        forgotPasswordButton = requireView().findViewById(R.id.forgot_password_button);
        userEmailTextView = requireView().findViewById(R.id.user_email_edittext_login);
        userPasswordTextView = requireView().findViewById(R.id.user_password_edittext_login);
        loginButton = requireView().findViewById(R.id.login_button);

        sharedPrefs = new SharedPrefs(requireContext());
    }

    private void fetchData() {

    }

    private void setViews() {
        ExtFunctions.underlineText(registerNowButton);
    }

    private void setListeners() {

        registerNowButton.setOnClickListener(view -> {
            setCurrentFragment(new CreateAccountFragment());
        });

        loginButton.setOnClickListener(view -> {
            userEmail = userEmailTextView.getText().toString();
            userPassword = userPasswordTextView.getText().toString();
            loginUser(userEmail, userPassword);
            // TODO: LOGIN USER
        });

        forgotPasswordButton.setOnClickListener(view -> setCurrentFragment(new ForgotPasswordFragment()));

    }

    private void loginUser(String email, String password){

        String url = "user_login.php?user_email=" + email + "&user_password=" + password;

        try {
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
                    if (responseString.equals(Constants.EMAIL_NOT_EXIST)) {
                        userEmailTextView.setError("Invalid email");
                    } else if (responseString.equals(Constants.INVALID_PASSWORD)) {
                        userPasswordTextView.setError("Invalid Password");
                    } else {
                        checkUser(responseString);
                    }
                }

            });

        } catch (Exception e) {
            Log.e("TAG", "couldn't login user");
        }
    }

    private void checkUser(String response) {

        userId = Integer.parseInt(response.split(",")[0]);
        Log.e("TAG", "userId:" + userId);
        userName = response.split(",")[2];
        Log.e("TAG", "userName:" + userName);
        userStatus = Integer.parseInt(response.split(",")[3]);
        Log.e("TAG", "userStatus:" + userStatus);

        if (userStatus == 0) {
            setCurrentFragment(new EmailVerificationFragment());
        } else if (userStatus == 1) {
            sharedPrefs.setUserStatus(1);
            startActivity(NewUserHomeActivity.newInstance(requireContext()));
            requireActivity().finish();
        }
    }

    private void setObservers() {

    }
}