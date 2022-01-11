package com.example.humors.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.humors.R;
import com.example.humors.utils.ExtFunctions;

public class WelcomeActivity extends AppCompatActivity {

    private TextView registerNowButton;
    private Button mailSignInButton, googleSignInButton;

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

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
        videoView = findViewById(R.id.video_view);
        registerNowButton = findViewById(R.id.register_now_button_welcome);
        mailSignInButton = findViewById(R.id.mail_signin_button);
        googleSignInButton = findViewById(R.id.google_signin_button);
    }

    private void fetchData() {

    }

    private void setViews() {

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bg);
        Log.e("TAG", video.toString() );
        videoView.setVideoURI(video);
        videoView.setOnPreparedListener(mp -> {
            float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
            float screenRatio = videoView.getWidth() / ((float) videoView.getHeight() + 300);
            Log.e("TAG", "" + videoRatio +  "  " + screenRatio);
            float scaleX = videoRatio / screenRatio;
            if (scaleX >= 1f) {
                videoView.setScaleX(scaleX);
            } else {
                videoView.setScaleY(1f / scaleX);
            }
            videoView.setScaleY(scaleX);
            mp.setLooping(true);
            videoView.start();
        });

        ExtFunctions.underlineText(registerNowButton);
    }

    private void setListeners() {

        mailSignInButton.setOnClickListener(view -> startActivity(LoginActivity.newInstance(getApplicationContext())));

//        registerNowButton.setOnClickListener(view -> setCurrentFragment(new CreateAccountFragment()));

    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        Intent i = new Intent(context, WelcomeActivity.class);
        return i;
    }
}