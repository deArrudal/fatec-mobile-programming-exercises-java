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
import com.example.bookrental.controller.StudentController;
import com.example.bookrental.model.Student;
import com.example.bookrental.persistence.StudentDAO;

import java.sql.SQLException;
import java.util.List;

public class StudentFragment extends Fragment {
    private View view;
    private EditText editTextStudentId;
    private EditText editTextStudentName;
    private EditText editTextStudentEmail;
    private TextView textViewStudentOutput;
    private StudentController studentController;

    public StudentFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student, container, false);

        editTextStudentId = view.findViewById(R.id.editTextStudentId);
        editTextStudentName = view.findViewById(R.id.editTextStudentName);
        editTextStudentEmail = view.findViewById(R.id.editTextStudentEmail);
        textViewStudentOutput = view.findViewById(R.id.textViewStudentOutput);
        textViewStudentOutput.setMovementMethod(new ScrollingMovementMethod());

        studentController = new StudentController(new StudentDAO(view.getContext()));

        view.findViewById(R.id.buttonStudentSearch).setOnClickListener(search -> searchEntry());
        view.findViewById(R.id.buttonStudentRegister).setOnClickListener(register -> registerEntry());
        view.findViewById(R.id.buttonStudentUpdate).setOnClickListener(update -> updateEntry());
        view.findViewById(R.id.buttonStudentRemove).setOnClickListener(remove -> removeEntry());
        view.findViewById(R.id.buttonStudentList).setOnClickListener(list -> listEntry());

        return view;
    }

    private void searchEntry() {
        Student student;

        try {
            // only requires the studentId to removal
            student = studentController.searchEntry(new Student(
                    Integer.parseInt(editTextStudentId.getText().toString()),
                    null,
                    null)
            );

            if (student.getStudentName() != null) {
                readStudent(student);

            } else {
                Toast.makeText(
                        view.getContext(), "Student not found", Toast.LENGTH_LONG).show();

                clearField();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void registerEntry() {
        try {
            studentController.registerEntry(writeStudent());

            Toast.makeText(
                    view.getContext(), "Student registered successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void updateEntry() {
        try {
            studentController.updateEntry(writeStudent());

            Toast.makeText(
                    view.getContext(), "Student updated successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void removeEntry() {
        try {
            // only requires the studentId to removal
            studentController.removeEntry(new Student(
                    Integer.parseInt(editTextStudentId.getText().toString()), null, null));

            Toast.makeText(
                    view.getContext(), "Student removed successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void listEntry() {
        try {
            List<Student> students = studentController.listEntry();

            StringBuilder stringBuffer = new StringBuilder();

            for (Student student : students) {
                stringBuffer.append(student.toString()).append("\n");
            }

            textViewStudentOutput.setText(stringBuffer.toString());

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Student writeStudent() throws SQLException {
        if (!isFieldValid()) {
            throw new SQLException("Invalid input");
        }

        return new Student(
                Integer.parseInt(editTextStudentId.getText().toString()),
                editTextStudentName.getText().toString(),
                editTextStudentEmail.getText().toString()
        );
    }

    private boolean isFieldValid() {
        if (editTextStudentId.length() == 0) {
            return false;
        }

        if (editTextStudentName.length() == 0) {
            return false;
        }

        if (editTextStudentEmail.length() == 0) {
            return false;
        }

        return true;
    }

    private void readStudent(Student student) {
        editTextStudentId.setText(String.valueOf(student.getStudentId()));
        editTextStudentName.setText(student.getStudentName());
        editTextStudentEmail.setText(student.getStudentEmail());
    }

    private void clearField() {
        editTextStudentId.setText("");
        editTextStudentName.setText("");
        editTextStudentEmail.setText("");
    }
}