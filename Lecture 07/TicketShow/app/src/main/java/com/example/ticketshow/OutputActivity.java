package com.example.ticketshow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ticketshow.model.Ticket;
import com.example.ticketshow.model.TicketVIP;

public class OutputActivity extends AppCompatActivity {
    private TextView textViewTicket;
    private Ticket ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_output);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewTicket = findViewById(R.id.textViewTicket);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String ticketType = bundle.getString("ticketType");
        switch (ticketType) {
            case"Common":
                ticket = new Ticket(bundle.getString("ticketID"), bundle.getFloat("ticketPrice"));
                break;
            case "VIP":
                ticket = new TicketVIP(bundle.getString("ticketID"), bundle.getFloat("ticketPrice"),
                        bundle.getString("ticketVIP"));
                break;
        }

        textViewTicket.setText(
                String.format("%s\n%s%s", ticket.toString(), getString(R.string.ticket_final_price),
                ticket.computeFinalPrice(bundle.getFloat("ticketFee")))
        );

        findViewById(R.id.buttonReturn).setOnClickListener(confirm -> returnActivity());
    }

    private void returnActivity() {
        Intent intentReturn = new Intent(this, MainActivity.class);
        this.startActivity(intentReturn);
        this.finish();
    }

}