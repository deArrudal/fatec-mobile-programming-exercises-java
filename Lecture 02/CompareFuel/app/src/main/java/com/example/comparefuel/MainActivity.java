package com.example.comparefuel;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextGasPrice;
    private EditText editTextEthanolPrice;
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

        editTextGasPrice = findViewById(R.id.editTextGasPrice);

        editTextEthanolPrice = findViewById(R.id.editTextEthanolPrice);

        textViewOutput = findViewById(R.id.textViewOutput);

        findViewById(R.id.buttonCompare).setOnClickListener(compute -> compareFuel());
    }

    private void compareFuel() {
        double gasPrice = Double.parseDouble(editTextGasPrice.getText().toString());
        double ethanolPrice = Double.parseDouble(editTextEthanolPrice.getText().toString());

        if( ethanolPrice/gasPrice <= .70) {
            textViewOutput.setText(R.string.choose_ethanol);
        } else {
            textViewOutput.setText(R.string.choose_gas);
        }
    }
}