package com.example.humors.others;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.example.humors.R;
import com.example.humors.utils.GlobalVariables;

public class ScannerActivity extends AppCompatActivity {

    private CodeScannerView scannerView;
    private CodeScanner codeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        init();
    }

    private void init() {
        initialiseVariables();
        startCamera();
        setListeners();
    }

    private void initialiseVariables() {
        scannerView = findViewById(R.id.scanner_view);
        codeScanner = new CodeScanner(this, scannerView);
    }

    private void startCamera() {
        codeScanner.startPreview();
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        codeScanner.releaseResources();
    }

    private void setListeners() {
        codeScanner.setDecodeCallback(result -> {
            runOnUiThread(() -> {
                // Your Code here
                GlobalVariables.connectedStatus = 1;
                startActivity(AddDeviceActivity.newInstance(this, result.toString()));
            });
            Log.e("TAG", "Decoded code is: " +  result);
        });
    }

    public static Intent newInstance(Context context) {
       return new Intent(context, ScannerActivity.class);
    }
}