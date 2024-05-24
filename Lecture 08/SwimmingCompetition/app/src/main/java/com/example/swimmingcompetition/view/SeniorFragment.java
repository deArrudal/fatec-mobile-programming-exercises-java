package com.example.swimmingcompetition.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.swimmingcompetition.R;
import com.example.swimmingcompetition.controller.IOperation;
import com.example.swimmingcompetition.controller.OperationSenior;
import com.example.swimmingcompetition.model.Senior;

import java.time.LocalDate;

public class SeniorFragment extends Fragment {

    private View view;
    private EditText editTextSeniorName;
    private EditText editTextSeniorBirthDate;
    private EditText editTextSeniorAddress;
    private CheckBox checkBoxSeniorIsCardiac;

    public SeniorFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_senior, container, false);

        editTextSeniorName = view.findViewById(R.id.editTextSeniorName);
        editTextSeniorBirthDate = view.findViewById(R.id.editTextSeniorBirthDate);
        editTextSeniorAddress = view.findViewById(R.id.editTextSeniorAddress);
        checkBoxSeniorIsCardiac = view.findViewById(R.id.checkBoxSeniorIsCardiac);

        view.findViewById(R.id.buttonSeniorRegister).setOnClickListener(register -> registerSenior());

        return view;
    }

    private void registerSenior() {
        IOperation<Senior> operations = new OperationSenior();

        Senior senior = new Senior(
                editTextSeniorName.getText().toString(),
                LocalDate.parse(editTextSeniorBirthDate.getText().toString()),
                editTextSeniorAddress.getText().toString(),
                checkBoxSeniorIsCardiac.isChecked()
        );

        operations.registerSwimmer(senior);

        Toast.makeText(view.getContext(), senior.toString(), Toast.LENGTH_LONG).show();

        clearFieldInput();
    }

    private void clearFieldInput() {
        editTextSeniorName.setText("");
        editTextSeniorBirthDate.setText("");
        editTextSeniorAddress.setText("");
        checkBoxSeniorIsCardiac.setChecked(false);
    }
}