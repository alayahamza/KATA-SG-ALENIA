package com.alenia.kata.bank.domain.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

@MappedSuperclass
public class Operation {

    @Column(name = "date")
    Calendar date;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;
    @Column(name = "AMOUNT")
    private Double amount;

    @PrePersist
    private void setDate() {
        this.date = new GregorianCalendar();
    }

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
