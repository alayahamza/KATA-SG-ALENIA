package com.alenia.kata.bank.domain.service;

import com.alenia.kata.bank.domain.BankConstants;
import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.entity.Account;
import com.alenia.kata.bank.domain.entity.Transfer;
import com.alenia.kata.bank.domain.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public class OperationServiceImpl implements OperationService {

    private AccountService accountService;
    private TransferRepository transferRepository;

    @Autowired
    public OperationServiceImpl(AccountService accountService, TransferRepository transferRepository) {
        this.accountService = accountService;
        this.transferRepository = transferRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = BankException.class)
    public Transfer transfer(UUID payerId, UUID payeeId, Double transferAmount) throws BankException {
        if (payerId.equals(payeeId)) {
            throw new BankException(BankConstants.SAME_PAYER_PAYEE);
        }
        Account payer = accountService.withdraw(payerId, transferAmount);
        Account payee = accountService.deposit(payeeId, transferAmount);
        Transfer transfer = new Transfer();
        transfer.setPayer(payer);
        transfer.setPayee(payee);
        transfer.setAmount(transferAmount);
        return transferRepository.save(transfer);
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
