package com.example.humors.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.humors.R;

public class LoginActivity extends AppCompatActivity {

    private TextView termsText;
    private final WelcomeFragment welcomeFragment = new WelcomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        initialiseVariables();
        setViews();
        setCurrentFragment(welcomeFragment);
    }

    private void setCurrentFragment(Fragment fragment) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_fragment_container, fragment)
                    .disallowAddToBackStack().commit();
        }

    private void initialiseVariables() {
        termsText = findViewById(R.id.terms_text);
    }

    private void setViews() {
        termsText.setPaintFlags(termsText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
}