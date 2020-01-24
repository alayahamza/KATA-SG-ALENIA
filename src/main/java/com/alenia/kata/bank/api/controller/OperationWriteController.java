package com.alenia.kata.bank.api.controller;

import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.service.command.OperationCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/operation")
public class OperationWriteController {

    private OperationCommandService operationCommandService;

    @Autowired
    public OperationWriteController(OperationCommandService operationCommandService) {
        this.operationCommandService = operationCommandService;
    }

    @PostMapping("/{payerId}/{payeeId}/{amount}")
    public void transfer(@PathVariable UUID payerId, @PathVariable UUID payeeId, @PathVariable Double amount) throws BankException {
        operationCommandService.transfer(payerId, payeeId, amount);
    }

}
