package com.alenia.kata.bank.domain.service.query;

import com.alenia.kata.bank.domain.BankConstants;
import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.entity.Account;
import com.alenia.kata.bank.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AccountQueryServiceImpl implements AccountQueryService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountQueryServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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

}
