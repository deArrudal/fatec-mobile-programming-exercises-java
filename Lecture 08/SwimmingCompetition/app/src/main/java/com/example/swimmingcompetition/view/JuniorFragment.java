package com.example.swimmingcompetition.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.swimmingcompetition.R;
import com.example.swimmingcompetition.controller.IOperation;
import com.example.swimmingcompetition.controller.OperationJunior;
import com.example.swimmingcompetition.model.Junior;

import java.time.LocalDate;


public class JuniorFragment extends Fragment {

    private View view;
    private EditText editTextJuniorName;
    private EditText editTextJuniorBirthDate;
    private EditText editTextJuniorAddress;
    private EditText editTextJuniorYearPractice;

    public JuniorFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_junior, container, false);

        editTextJuniorName = view.findViewById(R.id.editTextJuniorName);
        editTextJuniorBirthDate = view.findViewById(R.id.editTextJuniorBirthDate);
        editTextJuniorAddress = view.findViewById(R.id.editTextJuniorAddress);
        editTextJuniorYearPractice = view.findViewById(R.id.editTextJuniorYearPractice);

        view.findViewById(R.id.buttonJuniorRegister).setOnClickListener(register -> registerJunior());

        return view;
    }

    private void registerJunior() {
        IOperation<Junior> operations = new OperationJunior();

        Junior junior = new Junior(
                editTextJuniorName.getText().toString(),
                LocalDate.parse(editTextJuniorBirthDate.getText().toString()),
                editTextJuniorAddress.getText().toString(),
                Integer.parseInt(editTextJuniorYearPractice.getText().toString())
        );

        operations.registerSwimmer(junior);

        Toast.makeText(view.getContext(), junior.toString(), Toast.LENGTH_LONG).show();

        clearFieldInput();
    }

    private void clearFieldInput() {
        editTextJuniorName.setText("");
        editTextJuniorBirthDate.setText("");
        editTextJuniorAddress.setText("");
        editTextJuniorYearPractice.setText("");
    }
}