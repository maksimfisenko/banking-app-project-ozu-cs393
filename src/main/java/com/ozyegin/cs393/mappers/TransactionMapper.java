package com.ozyegin.cs393.mappers;

import com.ozyegin.cs393.dto.TransactionDTO;
import com.ozyegin.cs393.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mappings({
            @Mapping(source = "sendingAccount.number", target = "sendingAccountNumber"),
            @Mapping(source = "receivingAccount.number", target = "receivingAccountNumber"),
            @Mapping(source = "currency.id", target = "currencyId")
    })
    TransactionDTO transactionToTransactionDto(Transaction transaction);

    @Mapping(source = "sendingAccountNumber", target = "sendingAccount.number")
    @Mapping(source = "receivingAccountNumber", target = "receivingAccount.number")
    @Mapping(source = "currencyId", target = "currency.id")
    Transaction transactionDtoToTransaction(TransactionDTO transactionDTO);
}
