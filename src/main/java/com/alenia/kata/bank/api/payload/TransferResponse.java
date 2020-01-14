package com.alenia.kata.bank.api.payload;

import java.util.UUID;

public class TransferResponse extends OperationResponse {

    private UUID payer;
    private UUID payee;

    public UUID getPayer() {
        return payer;
    }

    public void setPayer(UUID payer) {
        this.payer = payer;
    }

    public UUID getPayee() {
        return payee;
    }

    public void setPayee(UUID payee) {
        this.payee = payee;
    }
}
