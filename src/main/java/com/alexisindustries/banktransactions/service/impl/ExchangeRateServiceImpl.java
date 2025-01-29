package com.alexisindustries.banktransactions.service.impl;

import com.alexisindustries.banktransactions.dto.exchangerate.ExchangeRateDTO;
import com.alexisindustries.banktransactions.dto.feign.OpenExchangeRatesResult;
import com.alexisindustries.banktransactions.feign.OpenExchangeRatesFeignClient;
import com.alexisindustries.banktransactions.mapper.AutoExchangeRateClassMapper;
import com.alexisindustries.banktransactions.model.ExchangeRate;
import com.alexisindustries.banktransactions.repository.ExchangeRateRepository;
import com.alexisindustries.banktransactions.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@AllArgsConstructor
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private ExchangeRateRepository exchangeRateRepository;
    private OpenExchangeRatesFeignClient openExchangeRatesFeignClient;
    private final Logger logger = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);
    private final AutoExchangeRateClassMapper autoExchangeRateClassMapper;

    @Override
    public void fetchExchangeRate() {
        logger.info("Checking if exchange rates are available and up to date...");
        if (!isUpToDateRateAvailable("KZT/USD") && !isUpToDateRateAvailable("RUB/USD")) {
            logger.info("Updating today exchange rates...");
            LocalDate today = LocalDate.now();
            String[] currencyPairs = {"KZT/USD", "RUB/USD"};
            OpenExchangeRatesResult result = openExchangeRatesFeignClient.getLatestOpenExchangeRates();
            for (String currencyPair : currencyPairs) {
                Optional<ExchangeRate> existingRate = exchangeRateRepository.findByCurrencyPairAndDate(currencyPair, today);

                if (existingRate.isEmpty()) {
                    BigDecimal newRate = result.getRates().get(currencyPair.replace("/USD", ""));
                    saveExchangeRate(currencyPair, newRate, today);
                }
            }
            logger.info("Updating today exchange rates complete.");
            return;
        }
        logger.info("Exchange rates are available and up to date.");
    }

    @Override
    public void saveExchangeRate(String currencyPair, BigDecimal exchangeRate, LocalDate date) {
        ExchangeRate exchangeRateToSave = new ExchangeRate();
        exchangeRateToSave.setCurrencyPair(currencyPair);
        exchangeRateToSave.setRate(exchangeRate);
        exchangeRateToSave.setDate(date);

        exchangeRateRepository.save(exchangeRateToSave);
    }

    @Override
    public Optional<ExchangeRate> getLatestExchangeRate(String currencyPair) {
        return exchangeRateRepository.findTopByCurrencyPairOrderByDateDesc(currencyPair);
    }

    @Override
    public List<ExchangeRateDTO> getAllExchangeRates() {
        return exchangeRateRepository.findAll().stream()
                .map(autoExchangeRateClassMapper::fromExchangeRate)
                .toList();
    }

    public boolean isUpToDateRateAvailable(String currencyPair) {
        LocalDate today = LocalDate.now();
        return exchangeRateRepository.findByCurrencyPairAndDate(currencyPair, today).isPresent();
    }
}
