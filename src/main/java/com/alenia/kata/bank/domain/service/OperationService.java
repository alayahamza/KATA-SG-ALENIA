package com.alenia.kata.bank.domain.service;

import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.entity.Transfer;

import java.util.List;
import java.util.UUID;

public interface OperationService {

    Transfer transfer(UUID payerId, UUID payeeId, Double transferAmount) throws BankException;

    List<Transfer> findAll();

    List<Transfer> findByPayerOrPayee(UUID accountId);

    List<Transfer> findByPayer(UUID accountId);

    List<Transfer> findByPayee(UUID accountId);
}
