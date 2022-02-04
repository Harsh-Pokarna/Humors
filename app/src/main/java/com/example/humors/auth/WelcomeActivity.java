package com.example.humors.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.humors.R;
import com.example.humors.home.HomeActivity;
import com.example.humors.newUser.NewUserHomeActivity;
import com.example.humors.utils.ExtFunctions;
import com.example.humors.utils.SharedPrefs;
import com.yqritc.scalablevideoview.ScalableVideoView;

import java.io.IOException;

public class WelcomeActivity extends AppCompatActivity {

    private Button mailSignInButton, googleSignInButton;
    private View bgOverlay;
    private VideoView mVideoView;

    private SharedPrefs sharedPrefs;

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
        mVideoView = findViewById(R.id.bgVideo);
        bgOverlay = findViewById(R.id.bgOverlay);
        mailSignInButton = findViewById(R.id.mail_signin_button);
        googleSignInButton = findViewById(R.id.google_signin_button);

        sharedPrefs = new SharedPrefs(this);


    }

    private void backgroundVideo() {
        String path = "android.resource://com.example.humors/"+R.raw.bg;
        Uri u = Uri.parse(path);
        mVideoView.setVideoURI(u);
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mVideoView.start();
    }

    private void setBackgroundOverlay(){
        bgOverlay.getBackground().setAlpha(90);
    }

    private void fetchData() {

    }

    private void setViews() {
        setBackgroundOverlay();
        backgroundVideo();

//        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dummy_video);
//        videoView.setVideoURI(video);
//        Log.e("TAG", video.toString() );
////        try {
////            videoView.setRawData(R.raw.dummy_video);
////        } catch (IOException e) {
////            Log.e("TAG", e.getMessage());
////        }
////        videoView.start();
//        videoView.setOnPreparedListener(mp -> {
//            mp.setOnErrorListener((mediaPlayer, i, i1) -> {
//                Log.e("TAG", "onError: called i" + i );
//                Log.e("TAG", "onError: called i1" + i1);
//                return false;
//            });
//            float videoRatioX = mp.getVideoWidth() / (float) mp.getVideoHeight();
//            float screenRatioX = videoView.getWidth() / ((float) videoView.getHeight()+400);
//            float scaleX = videoRatioX / screenRatioX;
//            float videoRatioY = mp.getVideoHeight() / (float) mp.getVideoWidth();
//            float screenRatioY = (videoView.getHeight()+400) / ((float) videoView.getWidth());
//            float scaleY = videoRatioY / screenRatioY;
//            if (scaleX >= 1f) {
//                videoView.setScaleX(scaleX);
//            } else {
//                videoView.setScaleY(1f / scaleX);
//            }
//            videoView.setScaleY(1f / scaleY);
//            mp.setLooping(true);
//            videoView.start();
//        });

    }

    private void setListeners() {

        mailSignInButton.setOnClickListener(view -> {
//            if (sharedPrefs.getUserStatus() == 1) {
//                startActivity(NewUserHomeActivity.newInstance(this));
//            } else {
//                startActivity(LoginActivity.newInstance(getApplicationContext()));
//            }
            startActivity(LoginActivity.newInstance(getApplicationContext()));

        });
        googleSignInButton.setOnClickListener(view -> Toast.makeText(this, "Press mail sign in button for complete experience", Toast.LENGTH_SHORT).show());

    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        Intent i = new Intent(context, WelcomeActivity.class);
        return i;
    }
}