package com.example.findmyage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;

public class MainActivity extends AppCompatActivity {

    private EditText editTextBirthdayDay;
    private EditText editTextBirthdayMonth;
    private EditText editTextBirthdayYear;
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

        editTextBirthdayDay = findViewById(R.id.editTextBirthdayDay);

        editTextBirthdayMonth = findViewById(R.id.editTextBirthdayMonth);

        editTextBirthdayYear = findViewById(R.id.editTextBirthdayYear);

        textViewOutput = findViewById(R.id.textViewOutput);

        findViewById(R.id.buttonCalculate).setOnClickListener(compute -> calculateAge());
    }

    @SuppressLint("DefaultLocale")
    private void calculateAge() {
        int birthdayYear = Integer.parseInt(editTextBirthdayYear.getText().toString());
        int birthdayMonth = Integer.parseInt(editTextBirthdayMonth.getText().toString());
        int birthdayDay = Integer.parseInt(editTextBirthdayDay.getText().toString());

        try {
            LocalDate birthdayDate = LocalDate.of(birthdayYear, birthdayMonth, birthdayDay);
            LocalDate currentDate = LocalDate.now();

            Period myAge = Period.between(birthdayDate, currentDate);

            textViewOutput.setText(
                    String.format("%s %d years, %d months, %d days", getString(R.string.my_age),
                            myAge.getYears(), myAge.getMonths(), myAge.getDays()));

        } catch (DateTimeException errDate) {
            textViewOutput.setText(R.string.invalid_date);
            textViewOutput.setTextColor(getResources().getColor(R.color.red));
        }
    }
}