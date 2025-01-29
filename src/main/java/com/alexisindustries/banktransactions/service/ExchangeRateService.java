package com.alexisindustries.banktransactions.service;

import com.alexisindustries.banktransactions.dto.exchangerate.ExchangeRateDTO;
import com.alexisindustries.banktransactions.model.ExchangeRate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
public interface ExchangeRateService {
    void fetchExchangeRate();
    void saveExchangeRate(String currencyPair, BigDecimal exchangeRate, LocalDate date);
    Optional<ExchangeRate> getLatestExchangeRate(String currencyPair);
    List<ExchangeRateDTO> getAllExchangeRates();
    boolean isUpToDateRateAvailable(String currencyPair);
}
