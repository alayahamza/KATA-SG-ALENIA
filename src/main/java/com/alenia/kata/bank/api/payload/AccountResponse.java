package com.alenia.kata.bank.api.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountResponse {

    private UUID id;
    private Double balance;
}
