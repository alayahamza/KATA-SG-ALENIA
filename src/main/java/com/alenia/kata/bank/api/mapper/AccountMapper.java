package com.alenia.kata.bank.api.mapper;

import com.alenia.kata.bank.api.payload.AccountResponse;
import com.alenia.kata.bank.domain.entity.Account;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountResponse toAccountResponse(Account account);

    @IterableMapping(elementTargetType = AccountResponse.class)
    List<AccountResponse> toAccountsResponse(List<Account> accounts);
}
