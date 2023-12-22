package com.ozyegin.cs393.mappers;

import com.ozyegin.cs393.dto.AccountTypeDTO;
import com.ozyegin.cs393.entities.AccountType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountTypeMapper {

    AccountTypeMapper INSTANCE = Mappers.getMapper(AccountTypeMapper.class);

    AccountTypeDTO accountTypeToAccountTypeDto(AccountType accountType);

    AccountType accountTypeDtoToAccountType(AccountTypeDTO accountTypeDto);
}
