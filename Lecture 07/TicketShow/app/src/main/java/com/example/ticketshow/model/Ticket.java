package com.example.ticketshow.model;

import androidx.annotation.NonNull;

public class Ticket {
    private String ticketId;
    private float ticketPrice;

    public Ticket(String ticketId, float ticketPrice) {
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public float computeFinalPrice(float ticketFee) {
        return  ticketPrice + ticketFee;
    }

    @NonNull
    @Override
    public String toString() {
        return "Common Ticket" + "\n"
                + "Id=" + ticketId + "\n"
                + "Base Price=" + ticketPrice;
    }

}
