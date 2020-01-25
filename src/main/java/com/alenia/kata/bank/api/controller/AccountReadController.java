package com.alenia.kata.bank.api.controller;

import com.alenia.kata.bank.api.mapper.AccountMapper;
import com.alenia.kata.bank.api.payload.AccountResponse;
import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.service.query.AccountQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/account")
public class AccountReadController {

    private AccountQueryService accountQueryService;
    private AccountMapper accountMapper;

    @Autowired
    public AccountReadController(AccountQueryService accountQueryService, AccountMapper accountMapper) {
        this.accountQueryService = accountQueryService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> findAccount(@PathVariable UUID accountId) throws BankException {
        return ResponseEntity.ok(accountMapper.toAccountResponse(accountQueryService.findById(accountId)));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAll() {
        return ResponseEntity.ok(accountMapper.toAccountsResponse(accountQueryService.findAll()));
    }
}
