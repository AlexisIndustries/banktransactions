package com.alexisindustries.banktransactions.dto.feign;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@Getter
@Setter
@ToString
public class OpenExchangeRatesResult {
    private String disclaimer;
    private String license;
    private String timestamp;
    private String base;
    private HashMap<String, BigDecimal> rates;
}
