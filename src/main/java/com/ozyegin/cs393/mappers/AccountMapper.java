package com.ozyegin.cs393.mappers;

import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO accountToAccountDto(Account account);

    Account accountDtoToAccount(AccountDTO accountDTO);

    default List<AccountDTO> accountsToAccountDtos(List<Account> accounts) {
        return accounts.stream().map(this::accountToAccountDto).collect(Collectors.toList());
    }
}
