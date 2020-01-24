package com.alenia.kata.bank.infra.repository;

import com.alenia.kata.bank.domain.entity.Account;
import com.alenia.kata.bank.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class H2DbAccountRepository implements AccountRepository {

    private SpringDataAccountRepository springDataAccountRepository;

    @Autowired
    public H2DbAccountRepository(SpringDataAccountRepository springDataAccountRepository) {
        this.springDataAccountRepository = springDataAccountRepository;
    }

    @Override
    public Account save(Account account) {
        return springDataAccountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(UUID accountId) {
        return springDataAccountRepository.findById(accountId);
    }

    @Override
    public List<Account> findAll() {
        return springDataAccountRepository.findAll();
    }
}
