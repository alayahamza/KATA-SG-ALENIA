package com.alenia.kata.bank.infra.repository;

import com.alenia.kata.bank.domain.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataTransferRepository extends JpaRepository<Transfer, UUID> {

    List<Transfer> findByPayerIdOrPayeeId(UUID payerId, UUID payeeId);

    List<Transfer> findByPayerId(UUID payerId);

    List<Transfer> findByPayeeId(UUID payeeId);
}
