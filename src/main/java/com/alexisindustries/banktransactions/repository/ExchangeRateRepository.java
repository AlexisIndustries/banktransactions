package com.alexisindustries.banktransactions.repository;

import com.alexisindustries.banktransactions.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, String> {
    Optional<ExchangeRate> findByCurrencyPairAndDate(String currencyPair, LocalDate date);
    Optional<ExchangeRate> findTopByCurrencyPairOrderByDateDesc(String currencyPair);
}