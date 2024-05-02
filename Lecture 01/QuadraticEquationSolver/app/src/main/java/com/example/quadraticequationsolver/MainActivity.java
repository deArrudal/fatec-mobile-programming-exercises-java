package com.example.quadraticequationsolver;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInputA;
    private EditText editTextInputB;
    private EditText editTextInputC;
    private TextView textViewOutputX1;
    private TextView textViewOutputX2;



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

        editTextInputA = findViewById(R.id.editTextInputA);
        editTextInputA.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        editTextInputB = findViewById(R.id.editTextInputB);
        editTextInputB.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        editTextInputC = findViewById(R.id.editTextInputC);
        editTextInputC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        textViewOutputX1 = findViewById(R.id.textViewOutputX1);
        textViewOutputX1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        textViewOutputX2 = findViewById(R.id.textViewOutputX2);
        textViewOutputX2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        findViewById(R.id.buttonCompute).setOnClickListener(compute -> computeRoots());

    }

    private  void computeRoots() {
        double a = Double.parseDouble(editTextInputA.getText().toString());
        double b = Double.parseDouble(editTextInputB.getText().toString());
        double c = Double.parseDouble(editTextInputC.getText().toString());
        double x1, x2;

        if(a == 0) {
            // case: no solution
            if (b == 0) {
                textViewOutputX1.setText(R.string.invalidEquation);
                textViewOutputX2.setText("");

                return;
            }

            // case: linear equation
            x2 = -c / b;

            textViewOutputX1.setText(R.string.linearEquation);
            textViewOutputX2.setText(String.format(Double.toString(x2)));

            return;
        }

        // calculate the determinant
        double determinant = b * b - 4 * a * c;

        // case: determinant is greater than 0
        if (determinant > 0) {
            x1 = (-b + Math.sqrt(determinant)) / (2 * a);
            x2 = (-b - Math.sqrt(determinant)) / (2 * a);

            textViewOutputX1.setText(String.format(Double.toString(x1)));
            textViewOutputX2.setText(String.format(Double.toString(x2)));
        }

        // case: determinant is equal to 0
        else if (determinant == 0) {
            x2 = -b / (2 * a);

            textViewOutputX1.setText(R.string.singleRoot);
            textViewOutputX2.setText(String.format(Double.toString(x2)));
        }

        // case: determinant is less than zero
        else {
            textViewOutputX1.setText(R.string.complexRoots);
            textViewOutputX2.setText("");
        }
    }
}
