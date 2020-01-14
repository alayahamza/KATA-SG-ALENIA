package com.alenia.kata.bank.api.payload;

import java.util.Calendar;
import java.util.UUID;

public class OperationResponse {

    Calendar date;
    private UUID id;
    private Double amount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
