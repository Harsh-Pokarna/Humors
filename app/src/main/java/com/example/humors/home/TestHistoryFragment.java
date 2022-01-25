package com.example.humors.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.humors.R;
import com.example.humors.api.ApiClient;
import com.example.humors.pojos.Test;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TestHistoryFragment extends Fragment {

    private RecyclerView testHistoryRecyclerView;

    private TestHistoryAdapter testHistoryAdapter = new TestHistoryAdapter(new ArrayList<>());

    private List<Test> listOfTests = new ArrayList();

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

        try {

            ApiClient.getTests("test_data.php", null, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i<jsonArray.length(); i++) {
                            JSONObject currentJsonObject = jsonArray.getJSONObject(i);
                            Test test = new Test();
                            test.setUserId(currentJsonObject.getString("id"));
                            test.setTestId(currentJsonObject.getString("test_id"));
                            test.setEmail(currentJsonObject.getString("email"));
                            test.setDate(currentJsonObject.getString("dttm"));
                            test.setTime("05:00");
                            test.setHealthStatus(currentJsonObject.getString("overall_result"));
                            listOfTests.add(test);
                        }
                        testHistoryAdapter.addData(listOfTests);

                    } catch (JSONException e) {
                        Log.e("TAG", "JSONException: " + e.getMessage() );
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);

                    Log.e("TAG", "onFailure: " + throwable.getMessage());
                    Toast.makeText(requireContext(), "Couldn't fetch data", Toast.LENGTH_SHORT).show();

                }
            });

        } catch (Exception e) {
            Log.e("TAG", e.getMessage() );
        }
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