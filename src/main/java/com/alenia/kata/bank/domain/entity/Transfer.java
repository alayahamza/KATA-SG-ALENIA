package com.alenia.kata.bank.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class Transfer extends Operation {

    @OneToOne
    private Account payer;

    @OneToOne
    private Account payee;
}
