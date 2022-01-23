package com.example.humors.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.humors.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

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
    }

    private void fetchData() {

    }

    private void setViews() {

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

            return false;
        });
    }

    private void setObservers() {

    }

    public static Intent newInstance(Context context) {
        return new Intent(context, HomeActivity.class);
    }
}