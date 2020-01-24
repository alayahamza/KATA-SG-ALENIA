package com.alenia.kata.bank.domain.service;

import com.alenia.kata.bank.domain.BankConstants;
import com.alenia.kata.bank.domain.BankException;
import com.alenia.kata.bank.domain.entity.Account;
import com.alenia.kata.bank.domain.repository.AccountRepository;
import com.alenia.kata.bank.domain.service.command.AccountCommandService;
import com.alenia.kata.bank.domain.service.command.AccountCommandServiceImpl;
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
@Import({AccountCommandServiceImpl.class, AccountQueryServiceImpl.class})
public class AccountCommandServiceTest {

    @Autowired
    private AccountCommandService accountService;

    @MockBean
    private AccountRepository accountRepository;

    private UUID accountId;
    private Account account;
    private Double initialBalance = (double) 100;

    @Before
    public void init() {
        accountId = UUID.randomUUID();
        account = new Account();
        account.setId(accountId);
        account.setBalance(initialBalance);
    }

    @Test
    public void should_create_account_when_create() {
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);
        Account accountCreated = accountService.create(initialBalance);
        Assert.assertNotNull(accountCreated);
        Assert.assertEquals(accountId, accountCreated.getId());
        Assert.assertEquals(initialBalance, accountCreated.getBalance());
    }

    @Test
    public void should_return_account_with_balance_60_when_withdraw_40_from_account_with_balance_100() throws BankException {
        Mockito.when(accountRepository.findById(accountId)).thenReturn(java.util.Optional.ofNullable(account));
        Account accountWithdraw = accountService.withdraw(accountId, (double) 40);
        Assert.assertNotNull(accountWithdraw);
        Assert.assertEquals(Double.valueOf(60), accountWithdraw.getBalance());
    }

    @Test
    public void should_return_account_not_found_error_when_withdraw_40_from_account_with_balance_100() {
        try {
            accountService.withdraw(accountId, (double) 40);
        } catch (BankException exception) {
            Assert.assertEquals(BankConstants.ACCOUNT_NOT_FOUND, exception.getMessage());
        }
    }

    @Test
    public void should_return_insufficient_funds_error_when_withdraw_140_from_account_with_balance_100() {
        Mockito.when(accountRepository.findById(accountId)).thenReturn(java.util.Optional.ofNullable(account));
        try {
            accountService.withdraw(accountId, (double) 140);
        } catch (BankException exception) {
            Assert.assertEquals(BankConstants.INSUFFICIENT_FUNDS, exception.getMessage());
        }
    }

    @Test
    public void should_return_account_with_balance_140_when_deposit_40_to_account_with_balance_100() throws BankException {
        Mockito.when(accountRepository.findById(accountId)).thenReturn(java.util.Optional.ofNullable(account));
        Account accountDeposit = accountService.deposit(accountId, (double) 40);
        Assert.assertNotNull(accountDeposit);
        Assert.assertEquals(Double.valueOf(140), accountDeposit.getBalance());
    }

    @Test
    public void should_return_account_not_found_error_when_deposit_40_to_account_with_balance_100() {
        try {
            accountService.deposit(accountId, (double) 40);
        } catch (BankException exception) {
            Assert.assertEquals(BankConstants.ACCOUNT_NOT_FOUND, exception.getMessage());
        }
    }
}
