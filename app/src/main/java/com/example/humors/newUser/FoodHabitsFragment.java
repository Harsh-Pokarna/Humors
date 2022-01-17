package com.example.humors.newUser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.humors.R;
import com.example.humors.newUser.NewUserHomeFragment;
import com.example.humors.newUser.ShareHabitsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FoodHabitsFragment extends Fragment {

    private ImageButton backButton;
    private FloatingActionButton nextButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.new_user_fragment_container, fragment)
                    .disallowAddToBackStack().commit();
        }

    private void initialiseVariables() {
        backButton = requireView().findViewById(R.id.food_habits_back_button);
        nextButton = requireView().findViewById(R.id.food_habits_next_button);
    }

    private void fetchData() {

    }

    private void setViews() {

    }

    private void setListeners() {
        backButton.setOnClickListener(view -> setCurrentFragment(new ShareHabitsFragment()));
        nextButton.setOnClickListener(view -> setCurrentFragment(new NewUserHomeFragment()));


    }

    private void setObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_habits, container, false);
    }
}