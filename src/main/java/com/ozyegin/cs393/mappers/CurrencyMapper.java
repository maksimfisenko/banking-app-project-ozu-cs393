package com.ozyegin.cs393.mappers;

import com.ozyegin.cs393.dto.AccountTypeDTO;
import com.ozyegin.cs393.dto.CurrencyDTO;
import com.ozyegin.cs393.entities.AccountType;
import com.ozyegin.cs393.entities.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

    CurrencyDTO currencyToCurrencyDto(Currency currency);

    Currency currencyDtoToCurrency(CurrencyDTO currencyDto);
    default List<CurrencyDTO> currenciesToCurrencyDtos (List<Currency> currencies){
        return currencies.stream().map(this::currencyToCurrencyDto).collect(Collectors.toList());
    }
}
