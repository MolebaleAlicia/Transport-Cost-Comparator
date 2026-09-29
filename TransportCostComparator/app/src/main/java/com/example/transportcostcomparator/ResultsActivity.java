package com.example.transportcostcomparator;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    private TextView tvModeOfTransport;
    private TextView tvTransportType;
    private TextView tvDailyCost;
    private TextView tvMonthlyCost;
    private TextView tvMonthlyDistance;
    private TextView tvCostCategory;
    private TextView tvRecommendation;
    private Button btnCalculateAgain;
    private Button btnReturnHome;
    private Button btnExitResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        tvModeOfTransport = findViewById(R.id.tvModeOfTransport);
        tvTransportType = findViewById(R.id.tvTransportType);
        tvDailyCost = findViewById(R.id.tvDailyCost);
        tvMonthlyCost = findViewById(R.id.tvMonthlyCost);
        tvMonthlyDistance = findViewById(R.id.tvMonthlyDistance);
        tvCostCategory = findViewById(R.id.tvCostCategory);
        tvRecommendation = findViewById(R.id.tvRecommendation);
        btnCalculateAgain = findViewById(R.id.btnCalculateAgain);
        btnReturnHome = findViewById(R.id.btnReturnHome);
        btnExitResults = findViewById(R.id.btnExitResults);

        displayResultsFromDatabase();
        setupNavigationButtons();
    }

    private void displayResultsFromDatabase() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.getLatestReport();


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String modeOfTransport =
                        cursor.getString(cursor.getColumnIndexOrThrow("mode_of_transport"));
                String transportType =
                        cursor.getString(cursor.getColumnIndexOrThrow("transport_type"));
                double dailyCost =
                        cursor.getDouble(cursor.getColumnIndexOrThrow("daily_cost"));
                double monthlyCost =
                        cursor.getDouble(cursor.getColumnIndexOrThrow("monthly_cost"));
                double monthlyDistance =
                        cursor.getDouble(cursor.getColumnIndexOrThrow("monthly_distance"));

                // Question 10a
                tvModeOfTransport.setText("Mode of Transport: " + modeOfTransport);
                tvTransportType.setText("Transport Type: " + transportType);
                tvDailyCost.setText("Daily Transport Cost: R" +
                        String.format("%.2f", dailyCost));
                tvMonthlyCost.setText("Monthly Transport Cost: R" +
                        String.format("%.2f", monthlyCost));
                tvMonthlyDistance.setText("Monthly Distance Travelled: " +
                        String.format("%.2f", monthlyDistance) + " km");

                // Question 10b
                Recommendation recommendation = new Recommendation();
                String category = recommendation.getCostCategory(monthlyCost);
                tvCostCategory.setText("Transport Cost Category: " + category);

                // Question 10c
                String adviceText = recommendation.getRecommendation(category);
                tvRecommendation.setText("Recommendation: " + adviceText);
            }
            cursor.close();
        }
    }

    private void setupNavigationButtons() {
        btnCalculateAgain.setOnClickListener(v -> {
            startActivity(new Intent(ResultsActivity.this, InputActivity.class));
            finish();
        });

        btnReturnHome.setOnClickListener(v -> {
            Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        btnExitResults.setOnClickListener(v -> finishAffinity());
    }
}