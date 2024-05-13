package com.example.ticketshow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioButtonVIP;
    private EditText editTextVIPType;

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

        radioButtonVIP = findViewById(R.id.radioButtonVIP);
        editTextVIPType = findViewById(R.id.editTextVIPType);

        findViewById(R.id.buttonConfirm).setOnClickListener(process -> processTicket());
    }

    public void processTicket() {
        String ticketID = "001";
        float ticketPrice = 135.50f;
        float ticketFee = 15.00f;
        String ticketType = "Common";
        String ticketVIP = "";

        if (radioButtonVIP.isChecked()) {
            ticketType = "VIP";
            ticketVIP = editTextVIPType.getText().toString();
        }

        Bundle bundle = new Bundle();
        bundle.putString("ticketID", ticketID);
        bundle.putFloat("ticketPrice", ticketPrice);
        bundle.putFloat("ticketFee", ticketFee);
        bundle.putString("ticketType", ticketType);
        bundle.putString("ticketVIP", ticketVIP);

        sendData(bundle);
    }

    private void sendData(Bundle bundle) {
        Intent intent = new Intent(this, OutputActivity.class);
        intent.putExtras(bundle);
        this.startActivity(intent);
        this.finish();
    }

}