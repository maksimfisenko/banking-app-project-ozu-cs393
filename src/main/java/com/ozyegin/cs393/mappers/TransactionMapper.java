package com.ozyegin.cs393.mappers;

import com.ozyegin.cs393.dto.TransactionDTO;
import com.ozyegin.cs393.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDTO transactionToTransactionDto(Transaction transaction);

    Transaction transactionDtoToTransaction(TransactionDTO transactionDTO);

    default List<TransactionDTO> TransactionsToTransactionDtos (List<Transaction> transactions){
        return transactions.stream().map(this::transactionToTransactionDto).collect(Collectors.toList());
    }
}
