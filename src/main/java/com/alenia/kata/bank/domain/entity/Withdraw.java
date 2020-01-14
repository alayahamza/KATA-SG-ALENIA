package com.alenia.kata.bank.domain.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Withdraw extends Operation {

    @OneToOne
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
