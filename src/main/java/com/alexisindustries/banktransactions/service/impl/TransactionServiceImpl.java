package com.alexisindustries.banktransactions.service.impl;

import com.alexisindustries.banktransactions.dto.transaction.TransactionDTO;
import com.alexisindustries.banktransactions.dto.transaction.TransactionPostDTO;
import com.alexisindustries.banktransactions.mapper.AutoTransactionClassMapper;
import com.alexisindustries.banktransactions.model.ExchangeRate;
import com.alexisindustries.banktransactions.model.Limit;
import com.alexisindustries.banktransactions.model.Transaction;
import com.alexisindustries.banktransactions.repository.ExchangeRateRepository;
import com.alexisindustries.banktransactions.repository.LimitRepository;
import com.alexisindustries.banktransactions.repository.TransactionRepository;
import com.alexisindustries.banktransactions.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private LimitRepository limitRepository;
    private ExchangeRateRepository exchangeRateRepository;
    private final AutoTransactionClassMapper transactionMapper;

    public TransactionDTO saveTransaction(TransactionPostDTO transactionPostDTO) {
        Transaction transaction = transactionMapper.fromTransactionPostDTO(transactionPostDTO);

        BigDecimal amountInUSD = convertToUSD(transaction.getSum(), transaction.getCurrencyShortName(), transaction.getDatetime().toLocalDate());
        Optional<Limit> categoryLimit = limitRepository.findByCategoryAndLimitDateTimeBefore(transaction.getExpenseCategory(), transaction.getDatetime())
                .stream()
                .reduce((first, second) -> second);

        BigDecimal limitAmount = categoryLimit.map(Limit::getLimitSum).orElse(BigDecimal.valueOf(1000));
        BigDecimal spentAmount = transactionRepository.sumSpentByCategorySince(transaction.getExpenseCategory(), categoryLimit.map(Limit::getLimitDateTime).orElse(LocalDateTime.now()));

        transaction.setLimitExceeded(spentAmount.add(amountInUSD).compareTo(limitAmount) > 0);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionMapper.fromTransaction(savedTransaction);
    }

    public List<TransactionDTO> getExceededTransactions() {
        List<Transaction> transactions = transactionRepository.findByLimitExceededTrue();
        return transactions.stream()
                .map(transactionMapper::fromTransaction)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> getTransactions() {
        return transactionRepository.findAll().stream()
                .map(transactionMapper::fromTransaction)
                .collect(Collectors.toList());
    }

    private BigDecimal convertToUSD(BigDecimal amount, String currency, LocalDate date) {
        if ("USD".equalsIgnoreCase(currency)) {
            return amount;
        }

        Optional<ExchangeRate> exchangeRate = exchangeRateRepository.findByCurrencyPairAndDate(currency + "/USD", date);
        if (exchangeRate.isEmpty()) {
            exchangeRate = exchangeRateRepository.findTopByCurrencyPairOrderByDateDesc(currency + "/USD");
        }

        return exchangeRate.map(rate -> amount.multiply(rate.getRate()))
                .orElseThrow(() -> new EntityNotFoundException("Exchange rate not found for currency: " + currency));
    }
}
