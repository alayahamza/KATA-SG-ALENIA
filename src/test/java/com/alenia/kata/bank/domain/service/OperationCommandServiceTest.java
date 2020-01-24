package com.alenia.kata.bank.domain.service;

import com.alenia.kata.bank.domain.BankConstants;
import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.entity.Account;
import com.alenia.kata.bank.domain.entity.Transfer;
import com.alenia.kata.bank.domain.repository.AccountRepository;
import com.alenia.kata.bank.domain.repository.TransferRepository;
import com.alenia.kata.bank.domain.service.command.AccountCommandServiceImpl;
import com.alenia.kata.bank.domain.service.command.OperationCommandService;
import com.alenia.kata.bank.domain.service.command.OperationCommandServiceImpl;
import com.alenia.kata.bank.domain.service.query.AccountQueryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@Import({OperationCommandServiceImpl.class,
        AccountCommandServiceImpl.class,
        AccountQueryServiceImpl.class})
public class OperationCommandServiceTest {

    @Autowired
    private OperationCommandService operationService;

    @MockBean
    private TransferRepository transferRepository;

    @MockBean
    private AccountRepository accountRepository;

    private UUID payerId;
    private UUID payeeId;
    private Account payer;
    private Account payee;
    private Double initialPayerBalance = (double) 100;
    private Double initialPayeeBalance = (double) 200;
    private Transfer transfer;

    @Before
    public void init() {
        payerId = UUID.randomUUID();
        payeeId = UUID.randomUUID();
        payer = new Account();
        payer.setId(payerId);
        payer.setBalance(initialPayerBalance);
        payee = new Account();
        payee.setId(payeeId);
        payee.setBalance(initialPayeeBalance);
        transfer = new Transfer();
        transfer.setPayer(payer);
        transfer.setPayee(payee);
    }

    @Test
    public void should_return_payer_balance_60_and_payee_balance_240_when_transfer_40() throws BankException {
        Mockito.when(accountRepository.findById(payerId)).thenReturn(java.util.Optional.ofNullable(payer));
        Mockito.when(accountRepository.findById(payeeId)).thenReturn(java.util.Optional.ofNullable(payee));
        Mockito.when(transferRepository.save(Mockito.any())).thenReturn(transfer);
        Transfer transfer = operationService.transfer(payerId, payeeId, (double) 40);
        Assert.assertEquals(Double.valueOf(60), transfer.getPayer().getBalance());
        Assert.assertEquals(Double.valueOf(240), transfer.getPayee().getBalance());
    }

    @Test
    public void should_return_account_not_found_error_when_transfer_40_to_payee() {
        Mockito.when(accountRepository.findById(payerId)).thenReturn(java.util.Optional.ofNullable(payer));
        try {
            operationService.transfer(payerId, payeeId, (double) 40);
        } catch (BankException exception) {
            Assert.assertEquals(BankConstants.ACCOUNT_NOT_FOUND, exception.getMessage());
        }
    }

    @Test
    public void should_return_account_not_found_error_when_transfer_40_from_payer() {
        Mockito.when(accountRepository.findById(payeeId)).thenReturn(java.util.Optional.ofNullable(payee));
        try {
            operationService.transfer(payerId, payeeId, (double) 40);
        } catch (BankException exception) {
            Assert.assertEquals(BankConstants.ACCOUNT_NOT_FOUND, exception.getMessage());
        }
    }

}
