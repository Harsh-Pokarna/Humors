package com.example.humors.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.humors.R;

import java.util.Objects;

public class NewUserHomeFragment extends Fragment {

    CardView addDetailsCardView;

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

    private void initialiseVariables() {
        addDetailsCardView = requireView().findViewById(R.id.add_details_card_view);
    }

    private void fetchData() {

    }

    private void setViews() {


    }

    private void setCurrentFragment(Fragment fragment) {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.new_user_fragment_container, fragment)
                    .addToBackStack(null).commit();
        }

    private void setListeners() {
        addDetailsCardView.setOnClickListener(view -> setCurrentFragment(new AddDataFragment()));
    }

    private void setObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_user_home, container, false);
    }
}