package com.example.humors.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.humors.R;

public class LoginActivity extends AppCompatActivity {

    private TextView termsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        initialiseVariables();
        setViews();
    }

    private void initialiseVariables() {
        termsText = findViewById(R.id.terms_text);
    }

    private void setViews() {
        termsText.setPaintFlags(termsText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }
}