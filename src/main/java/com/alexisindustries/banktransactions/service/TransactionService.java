package com.alexisindustries.banktransactions.service;

import com.alexisindustries.banktransactions.dto.transaction.TransactionDTO;
import com.alexisindustries.banktransactions.dto.transaction.TransactionPostDTO;

import java.util.List;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
public interface TransactionService {
    TransactionDTO saveTransaction(TransactionPostDTO transactionPostDTO);
    List<TransactionDTO> getExceededTransactions();
    List<TransactionDTO> getTransactions();
}
