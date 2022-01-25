package com.example.humors.newUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.humors.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FoodHabitsActivity extends AppCompatActivity {

    private ImageButton backButton;
    private FloatingActionButton nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_habits);

        init();
    }


    private void init() {
        initialiseVariables();
        fetchData();
        setViews();
        setListeners();
        setObservers();
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.new_user_fragment_container, fragment)
                .disallowAddToBackStack().commit();
    }

    private void initialiseVariables() {
        backButton = findViewById(R.id.food_habits_back_button);
        nextButton = findViewById(R.id.food_habits_next_button);
    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {
        backButton.setOnClickListener(view -> onBackPressed());
        nextButton.setOnClickListener(view -> startActivity(NewUserHomeActivity.newInstance(this)));

    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, FoodHabitsActivity.class);
    }

}