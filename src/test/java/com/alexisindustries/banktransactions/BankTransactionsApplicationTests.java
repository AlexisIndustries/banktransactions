package com.alexisindustries.banktransactions;

import com.alexisindustries.banktransactions.dto.transaction.TransactionDTO;
import com.alexisindustries.banktransactions.mapper.AutoTransactionClassMapper;
import com.alexisindustries.banktransactions.model.Category;
import com.alexisindustries.banktransactions.model.ExchangeRate;
import com.alexisindustries.banktransactions.model.Limit;
import com.alexisindustries.banktransactions.model.Transaction;
import com.alexisindustries.banktransactions.repository.ExchangeRateRepository;
import com.alexisindustries.banktransactions.repository.LimitRepository;
import com.alexisindustries.banktransactions.repository.TransactionRepository;
import com.alexisindustries.banktransactions.service.ExchangeRateService;
import com.alexisindustries.banktransactions.service.LimitService;
import com.alexisindustries.banktransactions.service.TransactionService;
import com.alexisindustries.banktransactions.service.impl.ExchangeRateServiceImpl;
import com.alexisindustries.banktransactions.service.impl.LimitServiceImpl;
import com.alexisindustries.banktransactions.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankTransactionsApplicationTests {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private LimitRepository limitRepository;

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @Mock
    private AutoTransactionClassMapper autoTransactionClassMapper;

    private Transaction transaction;
    private Limit limit;
    private ExchangeRate exchangeRate;

    @BeforeEach
    void setUp() {
        transaction = new Transaction();
        transaction.setSum(new BigDecimal("500"));
        transaction.setCurrencyShortName("KZT");
        transaction.setExpenseCategory(Category.SERVICES);
        transaction.setDatetime(LocalDate.of(2022, 1, 2).atStartOfDay());

        limit = new Limit();
        limit.setCategory(Category.SERVICES);
        limit.setLimitSum(new BigDecimal("1000"));
        limit.setLimitDateTime(LocalDate.of(2022, 1, 1).atStartOfDay());

        exchangeRate = new ExchangeRate();
        exchangeRate.setDate(LocalDate.of(2022, 1, 2));
        exchangeRate.setCurrencyPair("KZT/USD");
        exchangeRate.setRate(new BigDecimal("0.0023"));
    }

    @Test
    void shouldMarkTransactionAsExceededIfLimitIsReached() {
        when(limitRepository.findByCategoryAndLimitDateTimeBefore(any(Category.class), any(LocalDateTime.class)))
                .thenReturn(List.of(limit));
        when(transactionRepository.sumSpentByCategorySince(any(Category.class), any(LocalDateTime.class)))
                .thenReturn(new BigDecimal("600"));
        when(exchangeRateRepository.findByCurrencyPairAndDate(anyString(), any(LocalDate.class)))
                .thenReturn(Optional.of(exchangeRate));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TransactionDTO savedTransaction = transactionService.saveTransaction(autoTransactionClassMapper.fromTransactionPost(transaction));

        assertTrue(savedTransaction.isLimitExceeded());
    }

    @Test
    void shouldNotMarkTransactionAsExceededIfUnderLimit() {
        when(limitRepository.findByCategoryAndLimitDateTimeBefore(any(Category.class), any(LocalDateTime.class)))
                .thenReturn(List.of(limit));
        when(transactionRepository.sumSpentByCategorySince(any(Category.class), any(LocalDateTime.class)))
                .thenReturn(new BigDecimal("300"));
        when(exchangeRateRepository.findByCurrencyPairAndDate(anyString(), any(LocalDate.class)))
                .thenReturn(Optional.of(exchangeRate));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TransactionDTO savedTransaction = transactionService.saveTransaction(autoTransactionClassMapper.fromTransactionPost(transaction));

        assertFalse(savedTransaction.isLimitExceeded());
    }

    @Test
    void shouldFetchLatestExchangeRate() {
        when(exchangeRateRepository.findTopByCurrencyPairOrderByDateDesc("KZT/USD"))
                .thenReturn(Optional.of(exchangeRate));
        Optional<ExchangeRate> latestRate = exchangeRateService.getLatestExchangeRate("KZT/USD");
        assertTrue(latestRate.isPresent());
        assertEquals(exchangeRate.getRate(), latestRate.get().getRate());
    }
}
