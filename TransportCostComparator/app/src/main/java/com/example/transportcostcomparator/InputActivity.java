package com.example.transportcostcomparator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class InputActivity extends AppCompatActivity {

    private EditText etModeOfTransport;
    private EditText etDistancePerDay;
    private EditText etCostPerKm;
    private DatePicker dpTravelDays;
    private Spinner spinnerTransportType;
    private Button btnCalculateSave;
    private Button btnClear;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        etModeOfTransport = findViewById(R.id.etModeOfTransport);
        etDistancePerDay = findViewById(R.id.etDistancePerDay);
        etCostPerKm = findViewById(R.id.etCostPerKm);
        dpTravelDays = findViewById(R.id.dpTravelDays);
        spinnerTransportType = findViewById(R.id.spinnerTransportType);
        btnCalculateSave = findViewById(R.id.btnCalculateSave);
        btnClear = findViewById(R.id.btnClear);

        databaseHelper = new DatabaseHelper(this);

        btnCalculateSave.setOnClickListener(v -> validateAndCalculate());
        btnClear.setOnClickListener(v -> clearFields());
    }

    //Question 9a
    private void validateAndCalculate() {
        String modeOfTransport = etModeOfTransport.getText().toString().trim();
        String distanceStr = etDistancePerDay.getText().toString().trim();
        String costStr = etCostPerKm.getText().toString().trim();
        String transportType = spinnerTransportType.getSelectedItem().toString();

        boolean isValid = true;

        if (modeOfTransport.isEmpty()) {
            etModeOfTransport.setError("Mode of Transport is required");
            isValid = false;
        }

        if (distanceStr.isEmpty()) {
            etDistancePerDay.setError("Distance is required");
            isValid = false;
        }

        if (costStr.isEmpty()) {
            etCostPerKm.setError("Cost per kilometre is required");
            isValid = false;
        }

        if (!isValid) {
            return;
        }

        double distancePerDay;
        double costPerKm;

        try {
            distancePerDay = Double.parseDouble(distanceStr);
        } catch (NumberFormatException e) {
            etDistancePerDay.setError("Enter a valid number");
            return;
        }

        try {
            costPerKm = Double.parseDouble(costStr);
        } catch (NumberFormatException e) {
            etCostPerKm.setError("Enter a valid number");
            return;
        }

        // Question 9b
        int travelDays = dpTravelDays.getDayOfMonth();
        if (travelDays <= 0) {
            Toast.makeText(this, "Please select a valid number of travel days.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Transport transport = new Transport(modeOfTransport, distancePerDay,
                costPerKm, travelDays);

        TransportCalculator calculator = new TransportCalculator();
        double dailyCost = calculator.calculateDailyCost(transport);
        double monthlyCost = calculator.calculateMonthlyCost(transport);
        double monthlyDistance = calculator.calculateMonthlyDistance(transport);

        databaseHelper.insertReport(modeOfTransport, transportType, dailyCost,
                monthlyCost, monthlyDistance);

        Intent intent = new Intent(InputActivity.this, ResultsActivity.class);
        startActivity(intent);
    }

    private void clearFields() {
        etModeOfTransport.setText("");
        etDistancePerDay.setText("");
        etCostPerKm.setText("");
        spinnerTransportType.setSelection(0);

        Calendar calendar = Calendar.getInstance();
        dpTravelDays.updateDate(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
    }
}