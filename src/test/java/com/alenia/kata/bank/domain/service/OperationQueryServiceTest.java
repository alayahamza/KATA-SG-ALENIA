package com.alenia.kata.bank.domain.service;

import com.alenia.kata.bank.domain.entity.Account;
import com.alenia.kata.bank.domain.entity.Transfer;
import com.alenia.kata.bank.domain.repository.AccountRepository;
import com.alenia.kata.bank.domain.repository.TransferRepository;
import com.alenia.kata.bank.domain.service.query.AccountQueryServiceImpl;
import com.alenia.kata.bank.domain.service.query.OperationQueryService;
import com.alenia.kata.bank.domain.service.query.OperationQueryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@Import({OperationQueryServiceImpl.class, AccountQueryServiceImpl.class})
public class OperationQueryServiceTest {

    @Autowired
    private OperationQueryService operationService;

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
    private List<Transfer> transfers;

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
        transfers = new ArrayList<>();
        transfers.add(transfer);
    }

    @Test
    public void should_return_all_transfers_when_findAll() {
        Mockito.when(transferRepository.findAll()).thenReturn(transfers);
        List<Transfer> transferList = operationService.findAll();
        Assert.assertFalse(transferList.isEmpty());
    }

    @Test
    public void should_return_transfers_made_from_account_x() {
        Mockito.when(transferRepository.findByPayerId(payerId)).thenReturn(transfers);
        List<Transfer> transactionList = operationService.findByPayer(payerId);
        Assert.assertFalse(transactionList.isEmpty());
        transactionList.forEach(transfer -> Assert.assertEquals(payerId, transfer.getPayer().getId()));
    }

    @Test
    public void should_return_transfers_made_to_account_x() {
        Mockito.when(transferRepository.findByPayeeId(payeeId)).thenReturn(transfers);
        List<Transfer> transactionList = operationService.findByPayee(payeeId);
        Assert.assertFalse(transactionList.isEmpty());
        transactionList.forEach(transfer -> Assert.assertEquals(payeeId, transfer.getPayee().getId()));
    }

    @Test
    public void should_return_transfers_made_to_or_from_account_x() {
        Mockito.when(transferRepository.findByPayerIdOrPayeeId(payerId, payerId)).thenReturn(transfers);
        List<Transfer> transactionList = operationService.findByPayerOrPayee(payerId);
        Assert.assertFalse(transactionList.isEmpty());
        transactionList.forEach(transfer -> Assert.assertEquals(payerId, transfer.getPayer().getId()));
    }
}
