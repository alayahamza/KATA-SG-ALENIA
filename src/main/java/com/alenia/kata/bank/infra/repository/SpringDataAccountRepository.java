package com.alenia.kata.bank.infra.repository;

import com.alenia.kata.bank.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataAccountRepository extends JpaRepository<Account, UUID> {
}
