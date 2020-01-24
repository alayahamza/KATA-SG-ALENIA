package com.alenia.kata.bank.domain.service.query;

import com.alenia.kata.bank.domain.entity.Transfer;
import com.alenia.kata.bank.domain.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class OperationQueryServiceImpl implements OperationQueryService {

    private TransferRepository transferRepository;

    @Autowired
    public OperationQueryServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public List<Transfer> findByPayerOrPayee(UUID accountId) {
        return transferRepository.findByPayerIdOrPayeeId(accountId, accountId);
    }

    @Override
    public List<Transfer> findByPayer(UUID accountId) {
        return transferRepository.findByPayerId(accountId);
    }

    @Override
    public List<Transfer> findByPayee(UUID accountId) {
        return transferRepository.findByPayeeId(accountId);
    }

}
