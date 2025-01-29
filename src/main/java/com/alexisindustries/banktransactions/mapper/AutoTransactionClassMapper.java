package com.alexisindustries.banktransactions.mapper;

import com.alexisindustries.banktransactions.dto.transaction.TransactionDTO;
import com.alexisindustries.banktransactions.dto.transaction.TransactionPostDTO;
import com.alexisindustries.banktransactions.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@Mapper(componentModel = "spring")
public interface AutoTransactionClassMapper {
    Transaction fromTransactionDTO(TransactionDTO transactionDTO);
    Transaction fromTransactionPostDTO(TransactionPostDTO transactionPostDTO);
    TransactionDTO fromTransaction(Transaction transaction);
    TransactionPostDTO fromTransactionPost(Transaction transaction);
}
