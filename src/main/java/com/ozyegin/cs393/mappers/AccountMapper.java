package com.ozyegin.cs393.mappers;

import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mappings({
            @Mapping(source = "currency.id", target = "currencyId"),
            @Mapping(source = "type.id", target = "accountTypeId"),
            @Mapping(source = "owner.id", target = "ownerId")
    })
    AccountDTO accountToAccountDto(Account account);
    @Mappings({
            @Mapping(source = "currencyId", target = "currency.id"),
            @Mapping(source = "accountTypeId", target = "type.id"),
            @Mapping(source = "ownerId", target = "owner.id")
    })
    Account accountDtoToAccount(AccountDTO accountDTO);

    default List<AccountDTO> accountsToAccountDtos(List<Account> accounts) {
        return accounts.stream().map(this::accountToAccountDto).collect(Collectors.toList());
    }
}
