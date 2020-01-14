package com.alenia.kata.bank.api.controller;

import com.alenia.kata.bank.api.mapper.AccountMapper;
import com.alenia.kata.bank.api.payload.AccountRequest;
import com.alenia.kata.bank.api.payload.AccountResponse;
import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @PutMapping()
    public void createAccount(@RequestBody AccountRequest accountRequest) {
        accountService.create(accountRequest.getBalance());
    }

    @PostMapping("/{accountId}/deposit/{amount}")
    public void deposit(@PathVariable UUID accountId, @PathVariable Double amount) throws BankException {
        accountService.deposit(accountId, amount);
    }

    @PostMapping("/{accountId}/withdraw/{amount}")
    public void withdraw(@PathVariable UUID accountId, @PathVariable Double amount) throws BankException {
        accountService.withdraw(accountId, amount);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> findAccount(@PathVariable UUID accountId) throws BankException {
        return ResponseEntity.ok(accountMapper.toAccountResponse(accountService.findById(accountId)));
    }

    @GetMapping()
    public ResponseEntity<List<AccountResponse>> findAll() {
        return ResponseEntity.ok(accountMapper.toAccountsResponse(accountService.findAll()));
    }
}
