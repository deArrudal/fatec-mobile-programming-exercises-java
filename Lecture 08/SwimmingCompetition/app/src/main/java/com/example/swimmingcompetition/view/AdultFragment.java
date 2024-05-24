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
import com.example.swimmingcompetition.controller.OperationAdult;
import com.example.swimmingcompetition.model.Adult;

import java.time.LocalDate;

public class AdultFragment extends Fragment {

    private View view;
    private EditText editTextAdultName;
    private EditText editTextAdultBirthDate;
    private EditText editTextAdultAddress;
    private EditText editTextAdultGym;
    private EditText editTextAdultBestTime;

    public AdultFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_adult, container, false);

        editTextAdultName = view.findViewById(R.id.editTextAdultName);
        editTextAdultBirthDate = view.findViewById(R.id.editTextAdultBirthDate);
        editTextAdultAddress = view.findViewById(R.id.editTextAdultAddress);
        editTextAdultGym = view.findViewById(R.id.editTextAdultGym);
        editTextAdultBestTime = view.findViewById(R.id.editTextAdultBestTime);

        view.findViewById(R.id.buttonAdultRegister).setOnClickListener(register -> registerAdult());

        return view;
    }

    private void registerAdult() {
        IOperation<Adult> operations = new OperationAdult();

        Adult adult = new Adult(
                editTextAdultName.getText().toString(),
                LocalDate.parse(editTextAdultBirthDate.getText().toString()),
                editTextAdultAddress.getText().toString(),
                editTextAdultGym.getText().toString(),
                Integer.parseInt(editTextAdultBestTime.getText().toString())
        );

        operations.registerSwimmer(adult);

        Toast.makeText(view.getContext(), adult.toString(), Toast.LENGTH_LONG).show();

        clearFieldInput();
    }

    private void clearFieldInput() {
        editTextAdultName.setText("");
        editTextAdultBirthDate.setText("");
        editTextAdultAddress.setText("");
        editTextAdultGym.setText("");
        editTextAdultBestTime.setText("");
    }
}