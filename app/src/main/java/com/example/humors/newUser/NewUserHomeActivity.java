package com.example.humors.newUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.humors.R;
import com.example.humors.connect.SearchingActivity;
import com.example.humors.home.HomeActivity;
import com.example.humors.utils.Constants;

public class NewUserHomeActivity extends AppCompatActivity {

    CardView addDetailsCardView, shareHabitsCardView, medicalHistoryCardView;
    ImageButton bt1, bt3, bt4;

    private String extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_home);

        init();
    }

    private void init() {
        getExtras();
        initialiseVariables();
        fetchData();
        setViews();
        setListeners();
        setObservers();
    }

    private void getExtras() {
        extras = getIntent().getStringExtra(Constants.NEW_USER_HOME);
    }

    private void initialiseVariables() {
        addDetailsCardView = findViewById(R.id.add_details_card_view);
        shareHabitsCardView = findViewById(R.id.share_habits_card_view);
        medicalHistoryCardView = findViewById(R.id.medical_history_card_view);

        bt1 = findViewById(R.id.bt1);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
    }

    private void fetchData() {

    }

    private void disableOnClick(CardView cardView) {
        cardView.setEnabled(false);
    }

    private void setViews() {
        if (extras.equals(Constants.ADD_DATA)) {
            bt1.setVisibility(View.VISIBLE);
            bt3.setVisibility(View.GONE);
            bt4.setVisibility(View.GONE);
            disableOnClick(shareHabitsCardView);
            disableOnClick(medicalHistoryCardView);
        } else if (extras.equals(Constants.SLEEP_SCHEDULE)) {
            bt1.setVisibility(View.GONE);
            bt3.setVisibility(View.VISIBLE);
            bt4.setVisibility(View.GONE);
            disableOnClick(addDetailsCardView);
            disableOnClick(medicalHistoryCardView);
        } else if (extras.equals(Constants.MEDICAL_HISTORY)) {
            bt1.setVisibility(View.GONE);
            bt3.setVisibility(View.GONE);
            bt4.setVisibility(View.VISIBLE);
            disableOnClick(shareHabitsCardView);
            disableOnClick(addDetailsCardView);
        } else {
            bt1.setVisibility(View.GONE);
            bt3.setVisibility(View.GONE);
            bt4.setVisibility(View.GONE);
            disableOnClick(shareHabitsCardView);
            disableOnClick(medicalHistoryCardView);
            disableOnClick(addDetailsCardView);
        }
    }

    private void setListeners() {
        addDetailsCardView.setOnClickListener(view -> startActivity(AddDataActivity.newInstance(this, "")));
        shareHabitsCardView.setOnClickListener(view -> startActivity(SleepScheduleActivity.newInstance(this, "")));
        medicalHistoryCardView.setOnClickListener(view -> startActivity(MedicalHistoryActivity.newInstance(this, "")));

        bt1.setOnClickListener(view -> addDetailsCardView.callOnClick());
        bt3.setOnClickListener(view -> shareHabitsCardView.callOnClick());
        bt4.setOnClickListener(view -> medicalHistoryCardView.callOnClick());
    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context, String extras) {
        Intent intent = new Intent(context, NewUserHomeActivity.class);
        intent.putExtra(Constants.NEW_USER_HOME, extras);
        return intent;
    }
}