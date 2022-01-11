package com.example.humors.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import com.example.humors.R;

public class WelcomeActivity extends AppCompatActivity {

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
    }

    private void fetchData() {

    }

    private void setViews() {

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dummy_video);
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
    }

    private void setListeners() {

    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        Intent i = new Intent(context, WelcomeActivity.class);
        return i;
    }
}