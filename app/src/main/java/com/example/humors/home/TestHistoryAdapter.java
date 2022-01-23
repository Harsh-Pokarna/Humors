package com.example.humors.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.humors.R;

import app.futured.donut.DonutProgressView;

public class TestHistoryAdapter extends RecyclerView.Adapter<TestHistoryAdapter.TestHistoryViewHolder> {

    @NonNull
    @Override
    public TestHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TestHistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestHistoryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class TestHistoryViewHolder extends RecyclerView.ViewHolder {

        DonutProgressView donutProgressView;
        TextView healthStatus, testDate, testTime, viewDetails;

        public TestHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            donutProgressView = itemView.findViewById(R.id.donut_view);
            healthStatus = itemView.findViewById(R.id.health_status_item);
            testDate = itemView.findViewById(R.id.test_date);
            testTime = itemView.findViewById(R.id.test_time);
            viewDetails = itemView.findViewById(R.id.view_details);

        }
    }
}
