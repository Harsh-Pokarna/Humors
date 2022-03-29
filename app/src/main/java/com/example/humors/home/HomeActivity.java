package com.example.humors.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.connect.BreatheTestActivity;
import com.example.humors.utils.GlobalVariables;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton takeReadingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        takeReadingButton = findViewById(R.id.take_reading_fab);
    }

    private void fetchData() {

    }

    private void setViews() {
        setCurrentFragment(new DashboardFragment());
        bottomNavigationView.setSelectedItemId(R.id.home_item);

    }

    private void setCurrentFragment(Fragment fragment) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_fragment_container , fragment)
                    .disallowAddToBackStack().commit();
        }

    private void setListeners() {
        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.settings_item) {
                setCurrentFragment(new SettingsFragment());
            }

            if (item.getItemId() == R.id.test_history_item) {
                setCurrentFragment(new TestHistoryFragment());

            }

            if (item.getItemId() == R.id.home_item) {
                setCurrentFragment(new DashboardFragment());
            }

            if (item.getItemId() == R.id.step_item) {
                setCurrentFragment(new StepCounterFragment());
            }

            return true;
        });

        takeReadingButton.setOnClickListener(view -> {
            if (GlobalVariables.connectedStatus == 1) {
                startActivity(BreatheTestActivity.newInstance(this));
            } else {
                Toast.makeText(this, "Please connect a device first", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, HomeActivity.class);
    }
}