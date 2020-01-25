package com.alenia.kata.bank.api.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class TransferResponse extends OperationResponse {

    private UUID payer;
    private UUID payee;
}
