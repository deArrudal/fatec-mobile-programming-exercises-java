package com.example.bookrental.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookrental.R;
import com.example.bookrental.controller.BookController;
import com.example.bookrental.controller.MagazineController;
import com.example.bookrental.controller.RentalController;
import com.example.bookrental.controller.StudentController;
import com.example.bookrental.model.Book;
import com.example.bookrental.model.Exemplar;
import com.example.bookrental.model.Magazine;
import com.example.bookrental.model.Rental;
import com.example.bookrental.model.Student;
import com.example.bookrental.persistence.BookDAO;
import com.example.bookrental.persistence.MagazineDAO;
import com.example.bookrental.persistence.RentalDAO;
import com.example.bookrental.persistence.StudentDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalFragment extends Fragment {
    private View view;
    private Spinner spinnerStudent;
    private Spinner spinnerExemplar;
    private EditText editTextRentalDateWithdraw;
    private EditText getEditTextRentalDateReturn;
    private TextView textViewRentalOutput;
    private RentalController rentalController;
    private StudentController studentController;
    private BookController bookController;
    private MagazineController magazineController;
    private List<Student> students;
    private List<Exemplar> exemplars;

    public RentalFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rental, container, false);

        spinnerStudent = view.findViewById(R.id.spinnerStudent);
        spinnerExemplar = view.findViewById(R.id.spinnerExemplar);
        editTextRentalDateWithdraw = view.findViewById(R.id.editTextRentalDateWithdraw);
        getEditTextRentalDateReturn = view.findViewById(R.id.editTextRentalDateReturn);
        textViewRentalOutput = view.findViewById(R.id.textViewRentalOutput);
        textViewRentalOutput.setMovementMethod(new ScrollingMovementMethod());

        rentalController = new RentalController(new RentalDAO(this.getContext()));
        studentController = new StudentController(new StudentDAO(this.getContext()));
        bookController = new BookController(new BookDAO(this.getContext()));
        magazineController = new MagazineController(new MagazineDAO(this.getContext()));

        loadSpinnerStudent();
        loadSpinnerExemplar();

        view.findViewById(R.id.buttonRentalSearch).setOnClickListener(search -> searchEntry());
        view.findViewById(R.id.buttonRentalRegister).setOnClickListener(register -> registerEntry());
        view.findViewById(R.id.buttonRentalUpdate).setOnClickListener(update -> updateEntry());
        view.findViewById(R.id.buttonRentalRemove).setOnClickListener(remove -> removeEntry());
        view.findViewById(R.id.buttonRentalList).setOnClickListener(list -> listEntry());

        return view;
    }

    private void loadSpinnerStudent() {
        Student student0 = new Student(0, "Select Student", null);
        List<Student> studentsSelector = new ArrayList<>();

        try {
            students = studentController.listEntry();

            studentsSelector.add(0, student0);
            studentsSelector.addAll(students);

            ArrayAdapter<Student> arrayAdapter = new ArrayAdapter<>(
                    view.getContext(), android.R.layout.simple_spinner_item, studentsSelector);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerStudent.setAdapter(arrayAdapter);

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void loadSpinnerExemplar() {
        Magazine magazine0 = new Magazine(0, "Select Exemplar", 0, null);
        List<Exemplar> exemplarsSelector = new ArrayList<>();

        try {
            exemplars = new ArrayList<>();
            exemplars.addAll(bookController.listEntry());
            exemplars.addAll(magazineController.listEntry());

            exemplarsSelector.add(0, magazine0);
            exemplarsSelector.addAll(exemplars);

            ArrayAdapter<Exemplar> arrayAdapter = new ArrayAdapter<>(
                    view.getContext(), android.R.layout.simple_spinner_item, exemplarsSelector);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerExemplar.setAdapter(arrayAdapter);

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void searchEntry() {
        Rental rental;

        try {
            rental = rentalController.searchEntry(new Rental(
                    (Student) spinnerStudent.getSelectedItem(),
                    getExemplar(),
                    LocalDate.parse(editTextRentalDateWithdraw.getText().toString())));

            if (rental.getRentalStudent().getStudentName() != null) {
                readRental(rental);

            } else {
                Toast.makeText(
                        view.getContext(), "Rental not found", Toast.LENGTH_LONG).show();

                clearField();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void registerEntry() {
        try {
            rentalController.registerEntry(writeRental());

            Toast.makeText(
                    view.getContext(), "Rental registered successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void updateEntry() {
        try {
            rentalController.updateEntry(writeRental());

            Toast.makeText(
                    view.getContext(), "Rental updated successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void removeEntry() {
        try {
            rentalController.removeEntry(new Rental(
                    (Student) spinnerStudent.getSelectedItem(),
                    getExemplar(),
                    LocalDate.parse(editTextRentalDateWithdraw.getText().toString()))
            );

            Toast.makeText(
                    view.getContext(), "Rental removed successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void listEntry() {
        try {
            List<Rental> rentals = rentalController.listEntry();

            StringBuilder stringBuffer = new StringBuilder();

            for (Rental rental : rentals) {
                stringBuffer.append(rental.toString()).append("\n");
            }

            textViewRentalOutput.setText(stringBuffer.toString());

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Rental writeRental() throws SQLException {
        if (!isFieldValid()) {
            throw new SQLException("Invalid input");
        }

        return new Rental(
                (Student) spinnerStudent.getSelectedItem(),
                getExemplar(),
                LocalDate.parse(editTextRentalDateWithdraw.getText().toString()),
                LocalDate.parse(getEditTextRentalDateReturn.getText().toString())
        );
    }

    private boolean isFieldValid() {
        if (editTextRentalDateWithdraw.length() == 0) {
            return false;
        }

        if (getEditTextRentalDateReturn.length() == 0) {
            return false;
        }

        if (spinnerStudent.getSelectedItemPosition() == 0) {
            return false;
        }

        if (spinnerStudent.getSelectedItemPosition() == 0) {
            return false;
        }

        return true;
    }

    private Exemplar getExemplar() {
        if (spinnerExemplar.getSelectedItem() instanceof Book) {
            return (Book) spinnerExemplar.getSelectedItem();
        } else {
            return (Magazine) spinnerExemplar.getSelectedItem();
        }
    }

    private void readRental(Rental rental) {
        setStudentSpinnerId(rental);
        setExemplarSpinnerId(rental);
        editTextRentalDateWithdraw.setText(String.valueOf(rental.getRentalDateWithdraw()));
        getEditTextRentalDateReturn.setText(String.valueOf(rental.getRentalDateReturn()));
    }

    private void setStudentSpinnerId(Rental rental) {
        int i = 1;
        for (Student student : students) {
            if (student.getStudentId() == rental.getRentalStudent().getStudentId()) {
                spinnerStudent.setSelection(i);
            } else {
                i++;
            }
        }
        if (i > students.size()) {
            spinnerStudent.setSelection(0);
        }
    }

    private void setExemplarSpinnerId(Rental rental) {
        int i = 1;
        for (Exemplar exemplar : exemplars) {
            if (exemplar.getExemplarId() == rental.getRentalExemplar().getExemplarId()) {
                spinnerExemplar.setSelection(i);
            } else {
                i++;
            }
        }
        if (i > exemplars.size()) {
            spinnerExemplar.setSelection(0);
        }
    }

    private void clearField() {
        spinnerStudent.setSelection(0);
        spinnerExemplar.setSelection(0);
        editTextRentalDateWithdraw.setText("");
        getEditTextRentalDateReturn.setText("");
    }
}