package com.alenia.kata.bank.domain.service.command;

import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.entity.Transfer;

import java.util.UUID;

public interface OperationCommandService {

    Transfer transfer(UUID payerId, UUID payeeId, Double transferAmount) throws BankException;
}
