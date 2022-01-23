package com.example.humors.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.humors.R;
public class TestHistoryFragment extends Fragment {

    private RecyclerView testHistoryRecyclerView;

    private TestHistoryAdapter testHistoryAdapter = new TestHistoryAdapter();

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
        testHistoryRecyclerView = requireView().findViewById(R.id.test_history_recycler_view);

    }

    private void fetchData() {

    }

    private void setViews() {
        testHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        testHistoryRecyclerView.setAdapter(testHistoryAdapter);
    }

    private void setListeners() {

    }

    private void setObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_history, container, false);
    }
}