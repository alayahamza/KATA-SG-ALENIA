package com.alenia.kata.bank.domain.repository;

import com.alenia.kata.bank.domain.entity.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    Account save(Account account);

    Optional<Account> findById(UUID accountId);

    List<Account> findAll();
}
