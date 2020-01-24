package com.alenia.kata.bank.infra.repository;

import com.alenia.kata.bank.domain.entity.Transfer;
import com.alenia.kata.bank.domain.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class H2DbTransferRepository implements TransferRepository {

    private SpringDataTransferRepository springDataTransferRepository;

    @Autowired
    public H2DbTransferRepository(SpringDataTransferRepository springDataTransferRepository) {
        this.springDataTransferRepository = springDataTransferRepository;
    }

    @Override
    public List<Transfer> findByPayerIdOrPayeeId(UUID payerId, UUID payeeId) {
        return springDataTransferRepository.findByPayerIdOrPayeeId(payerId, payeeId);
    }

    @Override
    public List<Transfer> findByPayerId(UUID payerId) {
        return springDataTransferRepository.findByPayerId(payerId);
    }

    @Override
    public List<Transfer> findByPayeeId(UUID payeeId) {
        return springDataTransferRepository.findByPayeeId(payeeId);
    }

    @Override
    public Transfer save(Transfer transfer) {
        return springDataTransferRepository.save(transfer);
    }

    @Override
    public List<Transfer> findAll() {
        return springDataTransferRepository.findAll();
    }
}
