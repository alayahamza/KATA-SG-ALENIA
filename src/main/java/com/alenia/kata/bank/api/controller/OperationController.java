package com.alenia.kata.bank.api.controller;

import com.alenia.kata.bank.api.mapper.OperationMapper;
import com.alenia.kata.bank.api.payload.TransferResponse;
import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/operation")
public class OperationController {

    private final OperationService operationService;
    private final OperationMapper operationMapper;

    @Autowired
    public OperationController(OperationService operationService, OperationMapper operationMapper) {
        this.operationService = operationService;
        this.operationMapper = operationMapper;
    }

    @PostMapping("/{payerId}/{payeeId}/{amount}")
    public void transfer(@PathVariable UUID payerId, @PathVariable UUID payeeId, @PathVariable Double amount) throws BankException {
        operationService.transfer(payerId, payeeId, amount);
    }

    @GetMapping
    public ResponseEntity<List<TransferResponse>> findAll() {
        return ResponseEntity.ok(operationMapper.toTransfersResponse(operationService.findAll()));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransferResponse>> findTransactionsByAccountId(@PathVariable UUID accountId) {
        return ResponseEntity.ok(operationMapper.toTransfersResponse(operationService.findByPayerOrPayee(accountId)));
    }

    @GetMapping("/payer/{payerId}")
    public ResponseEntity<List<TransferResponse>> findTransactionsByPayerId(@PathVariable UUID payerId) {
        return ResponseEntity.ok(operationMapper.toTransfersResponse(operationService.findByPayer(payerId)));
    }

    @GetMapping("/payee/{payeeId}")
    public ResponseEntity<List<TransferResponse>> findTransactionsByPayeeId(@PathVariable UUID payeeId) {
        return ResponseEntity.ok(operationMapper.toTransfersResponse(operationService.findByPayee(payeeId)));
    }

}
