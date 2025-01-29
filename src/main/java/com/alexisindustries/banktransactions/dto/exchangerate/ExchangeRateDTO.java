package com.alexisindustries.banktransactions.dto.exchangerate;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@Getter
@Setter
public class ExchangeRateDTO {
    private String currencyPair;
    private BigDecimal rate;
    private LocalDate date;
}
