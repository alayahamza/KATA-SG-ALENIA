package com.alenia.kata.bank.api.controller;

import com.alenia.kata.bank.api.mapper.OperationMapper;
import com.alenia.kata.bank.api.payload.TransferResponse;
import com.alenia.kata.bank.domain.service.query.OperationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/operation")
public class OperationReadController {

    private OperationQueryService operationQueryService;
    private OperationMapper operationMapper;

    @Autowired
    public OperationReadController(OperationQueryService operationQueryService, OperationMapper operationMapper) {
        this.operationQueryService = operationQueryService;
        this.operationMapper = operationMapper;
    }

    @GetMapping
    public ResponseEntity<List<TransferResponse>> findAll() {
        return ResponseEntity.ok(operationMapper.toTransfersResponse(operationQueryService.findAll()));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransferResponse>> findTransactionsByAccountId(@PathVariable UUID accountId) {
        return ResponseEntity.ok(operationMapper.toTransfersResponse(operationQueryService.findByPayerOrPayee(accountId)));
    }

    @GetMapping("/payer/{payerId}")
    public ResponseEntity<List<TransferResponse>> findTransactionsByPayerId(@PathVariable UUID payerId) {
        return ResponseEntity.ok(operationMapper.toTransfersResponse(operationQueryService.findByPayer(payerId)));
    }

    @GetMapping("/payee/{payeeId}")
    public ResponseEntity<List<TransferResponse>> findTransactionsByPayeeId(@PathVariable UUID payeeId) {
        return ResponseEntity.ok(operationMapper.toTransfersResponse(operationQueryService.findByPayee(payeeId)));
    }

}
