package com.example.throwdice;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioButtonOption1;
    private RadioButton radioButtonOption2;
    private RadioButton radioButtonOption3;
    private Spinner spinnerDiceType;
    private TextView textViewDice1;
    private TextView textViewDice2;
    private TextView textViewDice3;
    private HashMap<String,Integer> dices;
    private Random rnd;

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

        dices = new HashMap<>();
        rnd = new Random();

        radioButtonOption1 = findViewById(R.id.radioButtonOption1);
        radioButtonOption2 = findViewById(R.id.radioButtonOption2);
        radioButtonOption3 = findViewById(R.id.radioButtonOption3);

        spinnerDiceType = findViewById(R.id.spinnerDiceType);

        textViewDice1 = findViewById(R.id.textViewDice1);
        textViewDice2 = findViewById(R.id.textViewDice2);
        textViewDice3 = findViewById(R.id.textViewDice3);

        createDiceSet();

        setSpinnerList();

        findViewById(R.id.buttonThrow).setOnClickListener(compute -> throwDices());

    }

    private void createDiceSet() {
        dices.put("d4", 4);
        dices.put("d6", 6);
        dices.put("d8", 8);
        dices.put("d10", 10);
        dices.put("d12", 12);
        dices.put("d20", 20);
        dices.put("d100", 100);
    }
    private void setSpinnerList() {
        List<String> diceList = new ArrayList<>();
        dices.forEach((key,value) -> diceList.add(key)); // add dices to list.

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, diceList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDiceType.setAdapter(adapter);

    }

    private void throwDices() {
        try {
            Integer diceValue = dices.get((String) spinnerDiceType.getSelectedItem());
            int dice1 = rnd.nextInt(diceValue + 1);
            int dice2 = rnd.nextInt(diceValue + 1);
            int dice3 = rnd.nextInt(diceValue + 1);

            if(radioButtonOption1.isChecked()) {
                textViewDice1.setText(String.valueOf(dice1));
            }
            if(radioButtonOption2.isChecked()) {
                textViewDice1.setText(String.valueOf(dice1));
                textViewDice2.setText(String.valueOf(dice2));
            }
            if(radioButtonOption3.isChecked()) {
                textViewDice1.setText(String.valueOf(dice1));
                textViewDice2.setText(String.valueOf(dice2));
                textViewDice3.setText(String.valueOf(dice3));
            }

        } catch (NullPointerException errNull) {
            textViewDice1.setText(R.string.selector_error);
        }

    }
}