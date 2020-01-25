package com.alenia.kata.bank.api.controller;

import com.alenia.kata.bank.api.payload.AccountRequest;
import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.service.command.AccountCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/account")
public class AccountWriteController {

    private AccountCommandService accountCommandService;

    @Autowired
    public AccountWriteController(AccountCommandService accountCommandService) {
        this.accountCommandService = accountCommandService;
    }

    @PutMapping
    public void createAccount(@RequestBody AccountRequest accountRequest) {
        accountCommandService.create(accountRequest.getBalance());
    }

    @PostMapping("/{accountId}/deposit/{amount}")
    public void deposit(@PathVariable UUID accountId, @PathVariable Double amount) throws BankException {
        accountCommandService.deposit(accountId, amount);
    }

    @PostMapping("/{accountId}/withdraw/{amount}")
    public void withdraw(@PathVariable UUID accountId, @PathVariable Double amount) throws BankException {
        accountCommandService.withdraw(accountId, amount);
    }

}
