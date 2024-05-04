package com.example.checksalary;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.checksalary.control.ProfessorControl;


public class MainActivity extends AppCompatActivity {

    private EditText editTextIdentification;
    private TextView textViewOutput;
    private ProfessorControl professorControl;


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

        editTextIdentification = findViewById(R.id.editTextIdentification);
        textViewOutput =findViewById(R.id.textViewOutput);

        professorControl = new ProfessorControl();

        findViewById(R.id.buttonCheck).setOnClickListener(compute -> getSalary());

    }

    private void getSalary() {
        textViewOutput.setText(
                professorControl.checkSalary(editTextIdentification.getText().toString()));
    }
}