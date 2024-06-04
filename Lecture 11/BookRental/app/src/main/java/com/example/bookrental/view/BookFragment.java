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
import com.example.bookrental.controller.BookController;
import com.example.bookrental.model.Book;
import com.example.bookrental.persistence.BookDAO;

import java.sql.SQLException;
import java.util.List;

public class BookFragment extends Fragment {
    private View view;
    private EditText editTextBookId;
    private EditText editTextBookName;
    private EditText editTextBookPages;
    private EditText editTextBookISBN;
    private EditText editTextBookEdition;
    private TextView textViewBookOutput;
    private BookController bookController;

    public BookFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_book, container, false);

        editTextBookId = view.findViewById(R.id.editTextBookId);
        editTextBookName = view.findViewById(R.id.editTextBookName);
        editTextBookPages = view.findViewById(R.id.editTextBookPages);
        editTextBookISBN = view.findViewById(R.id.editTextBookISBN);
        editTextBookEdition = view.findViewById(R.id.editTextBookEdition);
        textViewBookOutput = view.findViewById(R.id.textViewBookOutput);
        textViewBookOutput.setMovementMethod(new ScrollingMovementMethod());

        bookController = new BookController(new BookDAO(this.getContext()));

        view.findViewById(R.id.buttonBookSearch).setOnClickListener(search -> searchEntry());
        view.findViewById(R.id.buttonBookRegister).setOnClickListener(register -> registerEntry());
        view.findViewById(R.id.buttonBookUpdate).setOnClickListener(update -> updateEntry());
        view.findViewById(R.id.buttonBookRemove).setOnClickListener(remove -> removeEntry());
        view.findViewById(R.id.buttonBookList).setOnClickListener(list -> listEntry());

        return view;
    }

    private void searchEntry() {
        Book book;

        try {
            book = bookController.searchEntry(new Book(
                    Integer.parseInt(editTextBookId.getText().toString()),
                    null,
                    0,
                    null,
                    0)
            );

            if (book.getExemplarName() != null) {
                readBook(book);

            } else {
                Toast.makeText(
                        view.getContext(), "Book not found", Toast.LENGTH_LONG).show();

                clearField();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void registerEntry() {
        try {
            bookController.registerEntry(writeBook());

            Toast.makeText(
                    view.getContext(), "Book registered successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void updateEntry() {
        try {
            bookController.updateEntry(writeBook());

            Toast.makeText(
                    view.getContext(), "Book updated successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void removeEntry() {
        try {
            bookController.removeEntry(
                    new Book(Integer.parseInt(editTextBookId.getText().toString()), null, 0, null, 0));

            Toast.makeText(
                    view.getContext(), "Book removed successfully", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        clearField();
    }

    private void listEntry() {
        try {
            List<Book> books = bookController.listEntry();

            StringBuilder stringBuffer = new StringBuilder();

            for (Book book : books) {
                stringBuffer.append(book.toString()).append("\n");
            }

            textViewBookOutput.setText(stringBuffer.toString());

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Book writeBook() throws SQLException {
        if (!isFieldValid()) {
            throw new SQLException("Invalid input");
        }

        return new Book(
                Integer.parseInt(editTextBookId.getText().toString()),
                editTextBookName.getText().toString(),
                Integer.parseInt(editTextBookPages.getText().toString()),
                editTextBookISBN.getText().toString(),
                Integer.parseInt(editTextBookEdition.getText().toString())
        );
    }

    private boolean isFieldValid() {
        if (editTextBookId.length() == 0) {
            return false;
        }

        if (editTextBookName.length() == 0) {
            return false;
        }

        if (editTextBookPages.length() == 0) {
            return false;
        }

        if (editTextBookISBN.length() == 0) {
            return false;
        }

        if (editTextBookEdition.length() == 0) {
            return false;
        }

        return true;
    }

    private void readBook(Book book) {
        editTextBookId.setText(String.valueOf(book.getExemplarId()));
        editTextBookName.setText(book.getExemplarName());
        editTextBookPages.setText(String.valueOf(book.getExemplarPages()));
        editTextBookISBN.setText(book.getBookISBN());
        editTextBookEdition.setText(String.valueOf(book.getBookEdition()));
    }

    private void clearField() {
        editTextBookId.setText("");
        editTextBookName.setText("");
        editTextBookPages.setText("");
        editTextBookISBN.setText("");
        editTextBookEdition.setText("");
    }
}