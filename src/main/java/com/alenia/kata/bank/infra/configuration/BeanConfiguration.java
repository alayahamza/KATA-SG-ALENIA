package com.alenia.kata.bank.infra.configuration;

import com.alenia.kata.bank.domain.repository.AccountRepository;
import com.alenia.kata.bank.domain.repository.TransferRepository;
import com.alenia.kata.bank.domain.service.command.AccountCommandService;
import com.alenia.kata.bank.domain.service.command.AccountCommandServiceImpl;
import com.alenia.kata.bank.domain.service.command.OperationCommandService;
import com.alenia.kata.bank.domain.service.command.OperationCommandServiceImpl;
import com.alenia.kata.bank.domain.service.query.AccountQueryService;
import com.alenia.kata.bank.domain.service.query.AccountQueryServiceImpl;
import com.alenia.kata.bank.domain.service.query.OperationQueryService;
import com.alenia.kata.bank.domain.service.query.OperationQueryServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    AccountQueryService accountQueryService(AccountRepository accountRepository) {
        return new AccountQueryServiceImpl(accountRepository);
    }

    @Bean
    AccountCommandService accountCommandService(AccountRepository accountRepository, AccountQueryService accountQueryService) {
        return new AccountCommandServiceImpl(accountRepository, accountQueryService);
    }

    @Bean
    OperationCommandService operationCommandService(AccountCommandService accountCommandService, TransferRepository transferRepository) {
        return new OperationCommandServiceImpl(accountCommandService, transferRepository);
    }

    @Bean
    OperationQueryService operationQueryService(TransferRepository transferRepository) {
        return new OperationQueryServiceImpl(transferRepository);
    }
}
