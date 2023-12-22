package com.ozyegin.cs393.mappers;

import com.ozyegin.cs393.dto.PaymentDTO;
import com.ozyegin.cs393.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mappings({
            @Mapping(source = "sendingCard.id", target = "sendingCardId"),
            @Mapping(source = "receivingAccount.number", target = "receivingAccountNumber"),
            @Mapping(source = "currency.id", target = "currencyId")
    })
    PaymentDTO paymentToPaymentDto(Payment payment);

    @Mapping(source = "sendingCardId", target = "sendingCard.id")
    @Mapping(source = "receivingAccountNumber", target = "receivingAccount.number")
    @Mapping(source = "currencyId", target = "currency.id")
    Payment paymentDtoToPayment(PaymentDTO paymentDTO);
}
