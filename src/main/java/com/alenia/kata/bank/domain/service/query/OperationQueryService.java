package com.alenia.kata.bank.domain.service.query;

import com.alenia.kata.bank.domain.entity.Transfer;

import java.util.List;
import java.util.UUID;

public interface OperationQueryService {

    List<Transfer> findAll();

    List<Transfer> findByPayerOrPayee(UUID accountId);

    List<Transfer> findByPayer(UUID accountId);

    List<Transfer> findByPayee(UUID accountId);
}
