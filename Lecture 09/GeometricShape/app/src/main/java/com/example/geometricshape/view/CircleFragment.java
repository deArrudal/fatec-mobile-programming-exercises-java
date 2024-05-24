package com.example.geometricshape.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.geometricshape.R;
import com.example.geometricshape.controller.CircleController;
import com.example.geometricshape.controller.IGeometryController;
import com.example.geometricshape.model.Circle;

public class CircleFragment extends Fragment {

    private View view;
    private EditText editTextCircleRadius;
    private TextView textViewCircleOutput;
    private IGeometryController<Circle> operations;
    private Circle circle;

    public CircleFragment() {
        super();
        this.operations = new CircleController();
        this.circle = new Circle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_circle, container, false);

        editTextCircleRadius = view.findViewById(R.id.editTextCircleRadius);
        textViewCircleOutput = view.findViewById(R.id.textViewCircleOutput);

        view.findViewById(R.id.buttonComputeArea).setOnClickListener(area -> computeArea());
        view.findViewById(R.id.buttonComputePerimeter).setOnClickListener(perimeter -> computePerimeter());

        return view;
    }


    @SuppressLint("DefaultLocale")
    private void computeArea() {
        circle.setCircleRadius(Float.parseFloat(editTextCircleRadius.getText().toString()));
        textViewCircleOutput.setText(String.format("%.2f", operations.computeArea(circle)));
        clearFieldInput();
    }

    @SuppressLint("DefaultLocale")
    private void computePerimeter() {
        circle.setCircleRadius(Float.parseFloat(editTextCircleRadius.getText().toString()));
        textViewCircleOutput.setText(String.format("%.2f", operations.computePerimeter(circle)));
        clearFieldInput();
    }

    private void clearFieldInput() {
        editTextCircleRadius.setText("");
    }
}