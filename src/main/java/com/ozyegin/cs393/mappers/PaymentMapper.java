package com.ozyegin.cs393.mappers;

import com.ozyegin.cs393.dto.CurrencyDTO;
import com.ozyegin.cs393.dto.PaymentDTO;
import com.ozyegin.cs393.entities.Currency;
import com.ozyegin.cs393.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentDTO paymentToPaymentDto(Payment payment);

    Payment paymentDtoToPayment(PaymentDTO paymentDTO);

    default List<PaymentDTO> paymentsToPaymentDtos (List<Payment> payments){
        return payments.stream().map(this::paymentToPaymentDto).collect(Collectors.toList());
    }
}
