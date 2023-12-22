package com.ozyegin.cs393.mappers;

import com.ozyegin.cs393.dto.DebitCardDTO;
import com.ozyegin.cs393.entities.DebitCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DebitCardMapper {

    DebitCardMapper INSTANCE = Mappers.getMapper(DebitCardMapper.class);

    @Mapping(source = "account.number", target = "accountNumber")
    DebitCardDTO debitCardtoDebitCardDto(DebitCard debitCard);

    @Mapping(source = "accountNumber", target = "account.number")
    DebitCard debitCardDtoToDebitCard(DebitCardDTO debitCardDTO);
}
