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
import com.example.geometricshape.controller.IGeometryController;
import com.example.geometricshape.controller.RectangleController;
import com.example.geometricshape.model.Rectangle;

public class RectangleFragment extends Fragment {

    private View view;
    private EditText editTextRectangleBase;
    private EditText editTextRectangleHeight;
    private TextView textViewRectangleOutput;
    private IGeometryController<Rectangle> operations;
    private Rectangle rectangle;

    public RectangleFragment() {
        super();
        this.operations = new RectangleController();
        this.rectangle = new Rectangle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rectangle, container, false);

        editTextRectangleBase = view.findViewById(R.id.editTextRectangleBase);
        editTextRectangleHeight = view.findViewById(R.id.editTextRectangleHeight);
        textViewRectangleOutput = view.findViewById(R.id.textViewRectangleOutput);

        view.findViewById(R.id.buttonComputeArea).setOnClickListener(area -> computeArea());
        view.findViewById(R.id.buttonComputePerimeter).setOnClickListener(perimeter -> computePerimeter());

        return view;
    }

    @SuppressLint("DefaultLocale")
    private void computeArea() {
        rectangle.setRectangleBase(Float.parseFloat(editTextRectangleBase.getText().toString()));
        rectangle.setRectangleHeight(Float.parseFloat(editTextRectangleHeight.getText().toString()));
        textViewRectangleOutput.setText(String.format("%.2f", operations.computeArea(rectangle)));
        clearFieldInput();
    }

    @SuppressLint("DefaultLocale")
    private void computePerimeter() {
        rectangle.setRectangleBase(Float.parseFloat(editTextRectangleBase.getText().toString()));
        rectangle.setRectangleHeight(Float.parseFloat(editTextRectangleHeight.getText().toString()));
        textViewRectangleOutput.setText(String.format("%.2f", operations.computePerimeter(rectangle)));
        clearFieldInput();
    }

    private void clearFieldInput() {
        editTextRectangleBase.setText("");
        editTextRectangleHeight.setText("");
    }
}