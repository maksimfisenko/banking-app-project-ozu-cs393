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

    DebitCardDTO debitCardtoDebitCardDto(DebitCard debitCard);

    DebitCard debitCardDtoToDebitCard(DebitCardDTO debitCardDTO);

    default List<DebitCardDTO> debitCardsToDebitCardDtos (List<DebitCard> debitCards){
        return debitCards.stream().map(this::debitCardtoDebitCardDto).collect(Collectors.toList());
    }
}
