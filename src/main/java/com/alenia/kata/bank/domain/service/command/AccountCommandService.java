package com.alenia.kata.bank.domain.service.command;

import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.entity.Account;

import java.util.UUID;

public interface AccountCommandService {

    Account create(Double amount);

    Account withdraw(UUID accountId, Double amount) throws BankException;

    Account deposit(UUID accountId, Double amount) throws BankException;
}
