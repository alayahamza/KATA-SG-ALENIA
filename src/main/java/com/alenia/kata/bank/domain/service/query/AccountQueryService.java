package com.alenia.kata.bank.domain.service.query;

import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountQueryService {

    Account findById(UUID accountId) throws BankException;

    List<Account> findAll();
}
