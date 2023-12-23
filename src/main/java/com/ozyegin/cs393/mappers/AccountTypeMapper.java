package com.ozyegin.cs393.mappers;

import com.ozyegin.cs393.dto.AccountTypeDTO;
import com.ozyegin.cs393.entities.AccountType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {

    AccountTypeMapper INSTANCE = Mappers.getMapper(AccountTypeMapper.class);

    AccountTypeDTO accountTypeToAccountTypeDto(AccountType accountType);

    AccountType accountTypeDtoToAccountType(AccountTypeDTO accountTypeDto);

    default List<AccountTypeDTO> accountTypesToAccountTypeDtos(List<AccountType> accountTypes){
        return accountTypes.stream().map(this::accountTypeToAccountTypeDto).collect(Collectors.toList());
    }
}
