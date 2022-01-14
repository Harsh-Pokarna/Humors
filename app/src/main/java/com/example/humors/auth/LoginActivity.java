package com.example.humors.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.humors.R;
import com.example.humors.utils.ExtFunctions;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private TextView termsText;
    private SliderView sliderView;
//    private final WelcomeFragment welcomeFragment = new WelcomeFragment();/

    private List<Drawable> listOfIllustrations;

    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        initialiseVariables();
        setViews();

        setCurrentFragment(new LoginFragment());
    }

    private void setCurrentFragment(Fragment fragment) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_fragment_container, fragment)
                    .disallowAddToBackStack().commit();
        }

    private void initialiseVariables() {
        termsText = findViewById(R.id.terms_text);
//        sliderView = findViewById(R.id.slider_view);

        listOfIllustrations = new ArrayList<>();
        listOfIllustrations.add(getResources().getDrawable(R.drawable.ic_welcom_illustration_1));
        listOfIllustrations.add(getResources().getDrawable(R.drawable.ic_welcome_illustration_2));
        listOfIllustrations.add(getResources().getDrawable(R.drawable.ic_welcome_illustration_3));


//        sliderAdapter = new SliderAdapter(listOfIllustrations);
//        sliderView.setSliderAdapter(sliderAdapter);
//        sliderView.startAutoCycle();
//        sliderView.setAutoCycle(true);


    }

    private void setViews() {
        ExtFunctions.underlineText(termsText);
    }

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
}