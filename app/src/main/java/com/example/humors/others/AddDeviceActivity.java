package com.example.humors.others;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.humors.R;
import com.example.humors.utils.Constants;

public class AddDeviceActivity extends AppCompatActivity {

    private AppCompatButton addButton;
    private ImageButton backButton;

    private String extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        init();
    }

    private void init() {
        getExtras();
        initialiseVariables();
        setListeners();
    }

    private void getExtras() {
        extras = getIntent().getStringExtra(Constants.ADD_DEVICE);
    }

    private void initialiseVariables() {
        addButton = findViewById(R.id.activity_add_device_add_btn);
        backButton = findViewById(R.id.activity_add_device_back_btn);

    }

    private void setListeners() {
        backButton.setOnClickListener(view -> onBackPressed());
        addButton.setOnClickListener(view -> ScannerActivity.newInstance(this));

    }

    public static Intent newInstance(Context context, String extras) {
        Intent intent = new Intent(context, AddDeviceActivity.class);
        intent.putExtra(Constants.ADD_DEVICE, extras);
        return intent;
    }
}