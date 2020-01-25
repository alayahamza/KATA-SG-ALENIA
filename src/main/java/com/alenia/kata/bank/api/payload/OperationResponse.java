package com.alenia.kata.bank.api.payload;

import lombok.Data;

import java.util.Calendar;
import java.util.UUID;

@Data
public class OperationResponse {

    Calendar date;
    private UUID id;
    private Double amount;
}
