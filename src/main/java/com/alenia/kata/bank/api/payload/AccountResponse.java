package com.alenia.kata.bank.api.payload;

import java.util.UUID;

public class AccountResponse {

    private UUID id;
    private Double balance;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
