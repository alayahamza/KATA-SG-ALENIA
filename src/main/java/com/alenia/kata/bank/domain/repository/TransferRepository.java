package com.alenia.kata.bank.domain.repository;

import com.alenia.kata.bank.domain.entity.Transfer;

import java.util.List;
import java.util.UUID;

public interface TransferRepository extends OperationRepository<Transfer> {

    List<Transfer> findByPayerIdOrPayeeId(UUID payerId, UUID payeeId);

    List<Transfer> findByPayerId(UUID payerId);

    List<Transfer> findByPayeeId(UUID payeeId);
}
