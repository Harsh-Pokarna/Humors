package com.example.humors.others;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.api.ApiClient;
import com.example.humors.utils.Constants;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AboutActivity extends AppCompatActivity {

    private ImageButton backButton, privacyButton;
    private TextView aboutTextView;

    private String aboutText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        init();
    }

    private void init() {
        initialiseVariables();
        setViews();
        setListeners();
    }

    private void initialiseVariables() {
        aboutTextView = findViewById(R.id.about_text);
        backButton = findViewById(R.id.about_back_button);
        privacyButton = findViewById(R.id.privacy_policy_button);
    }

    private void setViews() {
        getAboutData();
    }

    private void getAboutData() {
        String url = "about_app.php";

        ApiClient.getRequest(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    aboutText = response.getString("about");
                    aboutTextView.setText(aboutText);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("TAG", "couldn't get about data");
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("TAG", "There is a error: " + throwable.getMessage());
                if (throwable.getMessage().equals(Constants.NO_INTERNET_STRING)) {
                    Toast.makeText(AboutActivity.this, "Please connect with wifi/data", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(AboutActivity.this, "There is a error in interacting with API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListeners() {
        backButton.setOnClickListener(view -> onBackPressed());
    }

    public static Intent newInstance(Context context) {
        return new Intent(context, AboutActivity.class);
    }
}