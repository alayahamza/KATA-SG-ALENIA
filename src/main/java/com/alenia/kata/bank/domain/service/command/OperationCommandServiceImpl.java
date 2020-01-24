package com.alenia.kata.bank.domain.service.command;

import com.alenia.kata.bank.domain.BankConstants;
import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.entity.Account;
import com.alenia.kata.bank.domain.entity.Transfer;
import com.alenia.kata.bank.domain.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class OperationCommandServiceImpl implements OperationCommandService {

    private AccountCommandService accountCommandService;
    private TransferRepository transferRepository;

    @Autowired
    public OperationCommandServiceImpl(AccountCommandService accountCommandService, TransferRepository transferRepository) {
        this.accountCommandService = accountCommandService;
        this.transferRepository = transferRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = BankException.class)
    public Transfer transfer(UUID payerId, UUID payeeId, Double transferAmount) throws BankException {
        if (payerId.equals(payeeId)) {
            throw new BankException(BankConstants.SAME_PAYER_PAYEE);
        }
        Account payer = accountCommandService.withdraw(payerId, transferAmount);
        Account payee = accountCommandService.deposit(payeeId, transferAmount);
        Transfer transfer = new Transfer();
        transfer.setPayer(payer);
        transfer.setPayee(payee);
        transfer.setAmount(transferAmount);
        return transferRepository.save(transfer);
    }

}
