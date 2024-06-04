package com.example.bookrental.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookrental.R;
import com.example.bookrental.controller.MagazineController;
import com.example.bookrental.model.Magazine;
import com.example.bookrental.persistence.MagazineDAO;

import java.sql.SQLException;
import java.util.List;

public class MagazineFragment extends Fragment {
    private View view;
    private EditText editTextMagazineId;
    private EditText editTextMagazineName;
    private EditText editTextMagazinePages;
    private EditText editTextMagazineISSN;
    private TextView textViewMagazineOutput;
    private MagazineController magazineController;

    public MagazineFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_magazine, container, false);

        editTextMagazineId = view.findViewById(R.id.editTextMagazineId);
        editTextMagazineName = view.findViewById(R.id.editTextMagazineName);
        editTextMagazinePages = view.findViewById(R.id.editTextMagazinePages);
        editTextMagazineISSN = view.findViewById(R.id.editTextMagazineISSN);
        textViewMagazineOutput = view.findViewById(R.id.textViewMagazineOutput);
        textViewMagazineOutput.setMovementMethod(new ScrollingMovementMethod());

        magazineController = new MagazineController(new MagazineDAO(this.getContext()));

        view.findViewById(R.id.buttonMagazineSearch).setOnClickListener(search -> searchEntry());
        view.findViewById(R.id.buttonMagazineRegister).setOnClickListener(register -> registerEntry());
        view.findViewById(R.id.buttonMagazineUpdate).setOnClickListener(update -> updateEntry());
        view.findViewById(R.id.buttonMagazineRemove).setOnClickListener(remove -> removeEntry());
        view.findViewById(R.id.buttonMagazineList).setOnClickListener(list -> listEntry());

        return view;
    }

    private void searchEntry() {
        Magazine magazine;

        try {
            magazine = magazineController.searchEntry(new Magazine(
                    Integer.parseInt(editTextMagazineId.getText().toString()),
                    null,
                    0,
                    null)
            );

            if (magazine.getExemplarName() != null) {
                readBook(magazine);

            } else {
                Toast.makeText(
                        view.getContext(), "Magazine not found", Toast.LENGTH_LONG).show();

                clearField();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void registerEntry() {
        try {
            magazineController.registerEntry(writeMagazine());

            Toast.makeText(
                    view.getContext(), "Magazine registered successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void updateEntry() {
        try {
            magazineController.updateEntry(writeMagazine());

            Toast.makeText(
                    view.getContext(), "Magazine updated successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void removeEntry() {
        try {
            magazineController.removeEntry(
                    new Magazine(
                            Integer.parseInt(editTextMagazineId.getText().toString()), null, 0, null));

            Toast.makeText(
                    view.getContext(), "Magazine removed successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void listEntry() {
        try {
            List<Magazine> magazines = magazineController.listEntry();

            StringBuilder stringBuffer = new StringBuilder();

            for (Magazine magazine : magazines) {
                stringBuffer.append(magazine.toString()).append("\n");
            }

            textViewMagazineOutput.setText(stringBuffer.toString());

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Magazine writeMagazine() throws SQLException {
        if (!isFieldValid()) {
            throw new SQLException("Invalid input");
        }

        return new Magazine(
                Integer.parseInt(editTextMagazineId.getText().toString()),
                editTextMagazineName.getText().toString(),
                Integer.parseInt(editTextMagazinePages.getText().toString()),
                editTextMagazineISSN.getText().toString()
        );
    }

    private boolean isFieldValid() {
        if (editTextMagazineId.length() == 0) {
            return false;
        }

        if (editTextMagazineName.length() == 0) {
            return false;
        }

        if (editTextMagazinePages.length() == 0) {
            return false;
        }

        if (editTextMagazineISSN.length() == 0) {
            return false;
        }

        return true;
    }

    private void readBook(Magazine magazine) {
        editTextMagazineId.setText(String.valueOf(magazine.getExemplarId()));
        editTextMagazineName.setText(magazine.getExemplarName());
        editTextMagazinePages.setText(String.valueOf(magazine.getExemplarPages()));
        editTextMagazineISSN.setText(magazine.getMagazineISSN());
    }

    private void clearField() {
        editTextMagazineId.setText("");
        editTextMagazineName.setText("");
        editTextMagazinePages.setText("");
        editTextMagazineISSN.setText("");
    }

}