package com.alenia.kata.bank.domain.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Transfer extends Operation {

    @OneToOne
    private Account payer;

    @OneToOne
    private Account payee;

    public Account getPayer() {
        return payer;
    }

    public void setPayer(Account payer) {
        this.payer = payer;
    }

    public Account getPayee() {
        return payee;
    }

    public void setPayee(Account payee) {
        this.payee = payee;
    }
}
