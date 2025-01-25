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
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false, scale = 2)
    private BigDecimal limitSum;

    @Column(nullable = false)
    private LocalDateTime limitDateTime;

    @Column
    private String limitCurrencyShortName;
}
