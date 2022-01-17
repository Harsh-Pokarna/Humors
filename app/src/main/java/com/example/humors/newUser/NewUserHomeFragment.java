package com.example.humors.newUser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.humors.R;

public class NewUserHomeFragment extends Fragment {

    CardView addDetailsCardView, shareHabitsCardView, medicalHistoryCardView;
    ImageButton bt1, bt3, bt4;

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
        shareHabitsCardView = requireView().findViewById(R.id.share_habits_card_view);
        medicalHistoryCardView = requireView().findViewById(R.id.medical_history_card_view);

        bt1 = requireView().findViewById(R.id.bt1);
        bt3 = requireView().findViewById(R.id.bt3);
        bt4 = requireView().findViewById(R.id.bt4);
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
        shareHabitsCardView.setOnClickListener(view -> setCurrentFragment(new ShareHabitsFragment()));
        medicalHistoryCardView.setOnClickListener(view -> setCurrentFragment(new MedicalHistoryFragment()));


        bt1.setOnClickListener(view -> addDetailsCardView.callOnClick());
        bt3.setOnClickListener(view -> shareHabitsCardView.callOnClick());
        bt4.setOnClickListener(view -> medicalHistoryCardView.callOnClick());
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