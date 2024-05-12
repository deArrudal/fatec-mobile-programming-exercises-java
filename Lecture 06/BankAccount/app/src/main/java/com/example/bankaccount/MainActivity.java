package com.example.bankaccount;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bankaccount.model.SavingAccount;
import com.example.bankaccount.model.SpecialAccount;


public class MainActivity extends AppCompatActivity {

    private RadioButton radioButtonSaving;
    private RadioButton radioButtonSpecial;
    private Spinner spinnerTransaction;
    private EditText editTextAmount;
    private TextView textViewOutput;
    private SavingAccount savingAccount;
    private SpecialAccount specialAccount;

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

        radioButtonSaving = findViewById(R.id.radioButtonSaving);
        radioButtonSpecial = findViewById(R.id.radioButtonSpecial);
        spinnerTransaction = findViewById(R.id.spinnerTransaction);
        editTextAmount = findViewById(R.id.editTextAmount);
        textViewOutput = findViewById(R.id.textViewOutput);

        setAccount();
        setSpinnerList();

        findViewById(R.id.buttonDetail).setOnClickListener(detail -> displayDetail());
        findViewById(R.id.buttonConfirm).setOnClickListener(compute -> callAccount());
    }

    private void setAccount() {
        savingAccount = new SavingAccount(
                1, "John Doe", 1500.00f, 15);
        specialAccount = new SpecialAccount(
                2, "Jane Doe", 3000.00f, 1000.00f);
    }

    private void setSpinnerList() {
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.account_transaction_list,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinnerTransaction.setAdapter(adapter);
    }

    private void displayDetail() {
        if(radioButtonSaving.isChecked()) textViewOutput.setText(savingAccount.toString());
        else if(radioButtonSpecial.isChecked()) textViewOutput.setText(specialAccount.toString());
    }

    @SuppressLint("DefaultLocale")
    private void callAccount() {
        if (radioButtonSaving.isChecked()) {
            String accountTransaction = (String) spinnerTransaction.getSelectedItem();
            switch (accountTransaction) {
                case "Balance":
                    savingAccount.computeNewBalance(0.05f);
                    textViewOutput.setText(String.format("$ %.2f", savingAccount.getAccountBalance()));
                    break;
                case "Deposit":
                    displayTransaction(
                            savingAccount.deposit(Float.parseFloat(editTextAmount.getText().toString()))
                    );
                    break;
                case "Withdraw":
                    displayTransaction(
                            savingAccount.withdraw(Float.parseFloat(editTextAmount.getText().toString()))
                    );
                    break;
            }
        } else if (radioButtonSpecial.isChecked()) {
            String accountTransaction = (String) spinnerTransaction.getSelectedItem();
            switch (accountTransaction) {
                case "Balance":
                    textViewOutput.setText(String.format("$ %.2f", specialAccount.getAccountBalance()));
                    break;
                case "Deposit":
                    displayTransaction(
                            specialAccount.deposit(Float.parseFloat(editTextAmount.getText().toString()))
                    );
                    break;
                case "Withdraw":
                    displayTransaction(
                            specialAccount.withdraw(Float.parseFloat(editTextAmount.getText().toString()))
                    );
                    break;
            }
        }
    }

    private void displayTransaction(boolean isComplete) {
        if(isComplete) textViewOutput.setText(R.string.output_success);
        else {
            textViewOutput.setText(R.string.output_fail);
            textViewOutput.setTextColor(getResources().getColor(R.color.red));
        }
    }

}