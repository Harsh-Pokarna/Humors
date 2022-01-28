package com.example.humors.newUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.humors.R;
import com.example.humors.connect.SearchingActivity;
import com.example.humors.home.HomeActivity;

public class NewUserHomeActivity extends AppCompatActivity {

    CardView addDetailsCardView, shareHabitsCardView, medicalHistoryCardView;
    ImageButton bt1, bt3, bt4;
    AppCompatButton connectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_home);

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
        addDetailsCardView = findViewById(R.id.add_details_card_view);
        shareHabitsCardView = findViewById(R.id.share_habits_card_view);
        medicalHistoryCardView = findViewById(R.id.medical_history_card_view);
        connectButton = findViewById(R.id.connect_device);

        bt1 = findViewById(R.id.bt1);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
    }

    private void fetchData() {

    }

    private void setViews() {


    }

    private void setListeners() {
        addDetailsCardView.setOnClickListener(view -> startActivity(AddDataActivity.newInstance(this)));
        shareHabitsCardView.setOnClickListener(view -> startActivity(SleepScheduleActivity.newInstance(this)));
        medicalHistoryCardView.setOnClickListener(view -> startActivity(MedicalHistoryActivity.newInstance(this)));
//        connectButton.setOnClickListener(view -> startActivity(SearchingActivity.newInstance(this)));
        connectButton.setOnClickListener(view -> startActivity(HomeActivity.newInstance(this)));


        bt1.setOnClickListener(view -> addDetailsCardView.callOnClick());
        bt3.setOnClickListener(view -> shareHabitsCardView.callOnClick());
        bt4.setOnClickListener(view -> medicalHistoryCardView.callOnClick());
    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
       return new Intent(context, NewUserHomeActivity.class);
    }
}