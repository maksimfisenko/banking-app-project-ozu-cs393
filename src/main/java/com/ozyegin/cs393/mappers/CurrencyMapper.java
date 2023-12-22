package com.ozyegin.cs393.mappers;

import com.ozyegin.cs393.dto.CurrencyDTO;
import com.ozyegin.cs393.entities.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CurrencyMapper {
    CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

    CurrencyDTO currencyToCurrencyDto(Currency currency);

    Currency currencyDtoToCurrency(CurrencyDTO currencyDto);
}
