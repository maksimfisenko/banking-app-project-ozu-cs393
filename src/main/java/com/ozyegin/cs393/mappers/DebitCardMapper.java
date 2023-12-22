package com.ozyegin.cs393.mappers;

import com.ozyegin.cs393.dto.CurrencyDTO;
import com.ozyegin.cs393.dto.DebitCardDTO;
import com.ozyegin.cs393.entities.Currency;
import com.ozyegin.cs393.entities.DebitCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DebitCardMapper {

    DebitCardMapper INSTANCE = Mappers.getMapper(DebitCardMapper.class);

    @Mapping(source = "account.number", target = "accountNumber")
    DebitCardDTO debitCardtoDebitCardDto(DebitCard debitCard);

    @Mapping(source = "accountNumber", target = "account.number")
    DebitCard debitCardDtoToDebitCard(DebitCardDTO debitCardDTO);

    default List<DebitCardDTO> debutCardsToDebitCardDtos (List<DebitCard> debitCards){
        return debitCards.stream().map(this::debitCardtoDebitCardDto).collect(Collectors.toList());
    }
}
