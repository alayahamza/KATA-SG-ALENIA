package com.alenia.kata.bank.domain.repository;

import com.alenia.kata.bank.domain.entity.Operation;

import java.util.List;

public interface OperationRepository<T extends Operation> {
    T save(T t);

    List<T> findAll();
}
