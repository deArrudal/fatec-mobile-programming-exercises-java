package com.example.drivingrange;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAverageConsumption;
    private EditText editTextFuelTank;
    private TextView textViewOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextAverageConsumption = findViewById(R.id.editTextAverageConsumption);

        editTextFuelTank = findViewById(R.id.editTextFuelTank);

        textViewOutput = findViewById(R.id.textViewOutput);

        findViewById(R.id.buttonCompute).setOnClickListener(compute -> computeDrivingRange());
    }

    @SuppressLint("DefaultLocale")
    private void computeDrivingRange() {
        double averageConsumption = Double.parseDouble(
                editTextAverageConsumption.getText().toString());
        double fuelTank = Double.parseDouble(editTextFuelTank.getText().toString());
        double distance = averageConsumption * fuelTank * 1000; // distance [m]
        textViewOutput.setText(String.format("%s %.2f", getString(R.string.driving_range), distance));
    }
}