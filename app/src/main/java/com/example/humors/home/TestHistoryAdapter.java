package com.example.humors.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.humors.R;
import com.example.humors.pojos.Test;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;

public class TestHistoryAdapter extends RecyclerView.Adapter<TestHistoryAdapter.TestHistoryViewHolder> {

    private List<Test> listOfTests;

    public TestHistoryAdapter(List<Test> listOfTests) {
        this.listOfTests = listOfTests;
    }

    @NonNull
    @Override
    public TestHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TestHistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestHistoryViewHolder holder, int position) {
        holder.bind(listOfTests.get(position));
    }

    @Override
    public int getItemCount() {
        return listOfTests.size();
    }

    public void addData(List<Test> listOfTests) {
        this.listOfTests = listOfTests;
        notifyDataSetChanged();
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

        public void bind(Test test) {
            DonutSection donutSection = new DonutSection(test.getTestId(), Color.parseColor("#0984e3"), Integer.parseInt(test.getHealthStatus()));
            donutProgressView.setCap(100);
            donutProgressView.submitData(Collections.singletonList(donutSection));
            healthStatus.setText(test.getHealthStatus() + "%");
            testDate.setText(test.getDate());
            testTime.setText(test.getTime());
        }
    }
}
