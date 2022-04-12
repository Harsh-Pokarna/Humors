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
import com.example.humors.api.ApiClient;
import com.example.humors.home.HomeActivity;
import com.example.humors.newUser.NewUserHomeActivity;
import com.example.humors.utils.Constants;
import com.example.humors.utils.ExtFunctions;
import com.example.humors.utils.SharedPrefs;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.loopj.android.http.TextHttpResponseHandler;
import com.yqritc.scalablevideoview.ScalableVideoView;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class WelcomeActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;
    private Button mailSignInButton, googleSignInButton;
    private View bgOverlay;
    private VideoView mVideoView;

    private SharedPrefs sharedPrefs;

    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        init();
    }

    private void init() {
        initialiseVariables();
        googleSignIn();
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

    private void googleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null) {
            startActivity(HomeActivity.newInstance(this));
        }

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            checkExistingStatus(account.getEmail());
        } catch (ApiException e) {
            if (e.getStatusCode() == 7) {
                Toast.makeText(this, "Please connect to internet", Toast.LENGTH_SHORT).show();
            }
            Log.e("TAG", "signInResult:failed code=" + e.getStatusCode());
            Log.e("TAG", "The error in sign in is: " + e.getMessage());
            Toast.makeText(this, "Failed to Sign In, Please try again later", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkExistingStatus(String userEmail) {

        String url = "check_user_exist.php?user_email=" + userEmail;

        try {
            ApiClient.getRequest(url, null, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.e("TAG", "There is a error: " + throwable.getMessage());
                    if (throwable.getMessage().equals(Constants.NO_INTERNET_STRING)) {
                        Toast.makeText(WelcomeActivity.this, "Please connect with wifi/data", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(WelcomeActivity.this, "There is a error in interacting with API", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {

                    if (responseString.equals("1") && sharedPrefs.getUserDisease().equals("")) {
                        Toast.makeText(WelcomeActivity.this, "Your email is already registered", Toast.LENGTH_SHORT).show();
                        startActivity(UserLoginActivity.newInstance(WelcomeActivity.this));
                    } else {
                        startActivity(NewUserHomeActivity.newInstance(WelcomeActivity.this, ""));
                    }

                }
            });
        } catch (Exception e) {
            Log.e("TAG", "couldn't login user");
        }
    }

    private void setListeners() {

        mailSignInButton.setOnClickListener(view -> {
//            if (sharedPrefs.getUserStatus() == 1) {
//                startActivity(NewUserHomeActivity.newInstance(this));
//            } else {
//                startActivity(LoginActivity.newInstance(getApplicationContext()));
//            }
            startActivity(UserLoginActivity.newInstance(getApplicationContext()));
//            startActivity(HomeActivity.newInstance(getApplicationContext()));

        });
        googleSignInButton.setOnClickListener(view -> signIn());
    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        Intent i = new Intent(context, WelcomeActivity.class);
        return i;
    }
}