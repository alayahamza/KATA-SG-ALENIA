package com.alenia.kata.bank.infra.configuration;

import com.alenia.kata.bank.domain.repository.AccountRepository;
import com.alenia.kata.bank.domain.repository.TransferRepository;
import com.alenia.kata.bank.domain.service.AccountService;
import com.alenia.kata.bank.domain.service.AccountServiceImpl;
import com.alenia.kata.bank.domain.service.OperationService;
import com.alenia.kata.bank.domain.service.OperationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    AccountService accountService(AccountRepository accountRepository) {
        return new AccountServiceImpl(accountRepository);
    }

    @Bean
    OperationService operationService(AccountService accountService, TransferRepository transferRepository) {
        return new OperationServiceImpl(accountService, transferRepository);
    }
}
