package com.alexisindustries.banktransactions.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@Entity
@Table
@Getter
@Setter
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String currencyPair;

    @Column(nullable = false, scale = 6)
    private BigDecimal rate;

    @Column(nullable = false)
    private LocalDate date;
}
