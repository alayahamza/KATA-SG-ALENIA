package com.alenia.kata.bank.domain.service;

import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account create(Double amount);

    Account findById(UUID accountId) throws BankException;

    List<Account> findAll();

    Account withdraw(UUID accountId, Double amount) throws BankException;

    Account deposit(UUID accountId, Double amount) throws BankException;
}
