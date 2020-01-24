package com.alenia.kata.bank.domain.service.command;

import com.alenia.kata.bank.domain.BankConstants;
import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.entity.Account;
import com.alenia.kata.bank.domain.repository.AccountRepository;
import com.alenia.kata.bank.domain.service.query.AccountQueryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class AccountCommandServiceImpl implements AccountCommandService {

    private AccountRepository accountRepository;
    private AccountQueryService accountQueryService;

    @Autowired
    public AccountCommandServiceImpl(AccountRepository accountRepository, AccountQueryService accountQueryService) {
        this.accountRepository = accountRepository;
        this.accountQueryService = accountQueryService;
    }

    @Override
    public Account create(Double amount) {
        Account account = new Account();
        account.setBalance(amount);
        return accountRepository.save(account);
    }

    @Override
    public Account withdraw(UUID accountId, Double amount) throws BankException {
        Account account = accountQueryService.findById(accountId);
        Double accountBalance = account.getBalance();
        double newBalance = accountBalance - amount;
        if (newBalance >= BankConstants.ACCOUNT_MIN_BALANCE) {
            account.setBalance(newBalance);
            accountRepository.save(account);
        } else {
            throw new BankException(BankConstants.INSUFFICIENT_FUNDS);
        }
        return account;
    }

    @Override
    public Account deposit(UUID accountId, Double amount) throws BankException {
        Account account = accountQueryService.findById(accountId);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return account;
    }
}
