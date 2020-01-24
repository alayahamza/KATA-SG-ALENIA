package com.alenia.kata.bank.domain.service;

import com.alenia.kata.bank.domain.BankConstants;
import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.entity.Account;
import com.alenia.kata.bank.domain.repository.AccountRepository;
import com.alenia.kata.bank.domain.service.query.AccountQueryService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@Import({AccountQueryServiceImpl.class})
public class AccountQueryServiceTest {

    @Autowired
    private AccountQueryService accountService;

    @MockBean
    private AccountRepository accountRepository;

    private UUID accountId;
    private Account account;
    private Double initialBalance = (double) 100;
    private ArrayList<Account> accounts;

    @Before
    public void init() {
        accountId = UUID.randomUUID();
        account = new Account();
        account.setId(accountId);
        account.setBalance(initialBalance);
        accounts = new ArrayList<>();
        accounts.add(account);
    }

    @Test
    public void should_return_account_with_id_x_when_findById_x() throws BankException {
        Mockito.when(accountRepository.findById(accountId)).thenReturn(java.util.Optional.ofNullable(account));
        Account accountById = accountService.findById(accountId);
        Assert.assertNotNull(accountById);
        Assert.assertEquals(accountId, accountById.getId());
        Assert.assertEquals(initialBalance, accountById.getBalance());
    }

    @Test
    public void should_return_account_not_found_error_when_findById_x() {
        try {
            accountService.findById(accountId);
        } catch (BankException exception) {
            Assert.assertEquals(BankConstants.ACCOUNT_NOT_FOUND, exception.getMessage());
        }
    }

    @Test
    public void should_return_all_accounts_when_findAll() {
        Mockito.when(accountRepository.findAll()).thenReturn(accounts);
        List<Account> allAccounts = accountService.findAll();
        Assert.assertFalse(allAccounts.isEmpty());
        Assert.assertEquals(accounts.size(), allAccounts.size());
    }

}
