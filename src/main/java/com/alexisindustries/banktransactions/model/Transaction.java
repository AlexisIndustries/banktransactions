package com.alexisindustries.banktransactions.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@Entity
@Table
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private Integer accountFrom;

    @Column(nullable = false)
    private Integer accountTo;

    @Column(nullable = false)
    private String currencyShortName;

    @Column(nullable = false, scale = 2)
    private BigDecimal sum;

    @Column(nullable = false)
    private Category expenseCategory;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @Column(name = "limit_exceeded", nullable = false)
    private boolean limitExceeded;
}
