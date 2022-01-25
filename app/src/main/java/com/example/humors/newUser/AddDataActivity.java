package com.example.humors.newUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.humors.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddDataActivity extends AppCompatActivity {

    private ImageButton backButton;
    private EditText userNameEditText;
    private FloatingActionButton nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

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
        backButton = findViewById(R.id.add_details_back_button);
        userNameEditText = findViewById(R.id.user_name_edittext_add_data);
        nextButton = findViewById(R.id.add_data_next_button);
    }

    private void fetchData() {

    }

    private void setViews() {
        userNameEditText.setError("Please enter a valid email");

    }

    private void setListeners() {
        backButton.setOnClickListener(view -> onBackPressed());
        nextButton.setOnClickListener(view -> startActivity(NewUserHomeActivity.newInstance(this)));
    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, AddDataActivity.class);
    }
}