package com.alenia.kata.bank.domain.service;

import com.alenia.kata.bank.domain.BankConstants;
import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.entity.Account;
import com.alenia.kata.bank.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(Double amount) {
        Account account = new Account();
        account.setBalance(amount);
        return accountRepository.save(account);
    }

    @Override
    public Account findById(UUID accountId) throws BankException {
        Objects.requireNonNull(accountId);
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new BankException(BankConstants.ACCOUNT_NOT_FOUND));
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account withdraw(UUID accountId, Double amount) throws BankException {
        Account account = findById(accountId);
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
        Account account = findById(accountId);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return account;
    }
}
