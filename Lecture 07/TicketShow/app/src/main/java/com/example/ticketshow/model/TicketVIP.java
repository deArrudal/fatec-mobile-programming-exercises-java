package com.example.ticketshow.model;

import androidx.annotation.NonNull;

public class TicketVIP extends Ticket {
    private String ticketVipType;

    public TicketVIP(String ticketId, float ticketPrice, String ticketVipType) {
        super(ticketId, ticketPrice);
        this.ticketVipType = ticketVipType;
    }

    public String getTicketVipType() {
        return ticketVipType;
    }

    public void setTicketVipType(String ticketVipType) {
        this.ticketVipType = ticketVipType;
    }

    @Override
    public float computeFinalPrice(float ticketFee) {
        return (super.getTicketPrice() + ticketFee) * 1.18f;
    }

    @NonNull
    @Override
    public String toString() {
        return "VIP Ticket" + "\n"
                + "Id=" + getTicketId() + "\n"
                + "Base Price=" + getTicketPrice() + "\n"
                + "VIP=" + ticketVipType;
    }

}
