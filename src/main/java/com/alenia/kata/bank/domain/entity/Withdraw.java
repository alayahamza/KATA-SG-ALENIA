package com.alenia.kata.bank.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class Withdraw extends Operation {

    @OneToOne
    private Account account;
}
