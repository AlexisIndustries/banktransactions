package com.alexisindustries.banktransactions.dto.limit;

import com.alexisindustries.banktransactions.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
public class LimitDTO {
    private String id;
    private Category category;
    private BigDecimal limitSum;
    private LocalDateTime limitDateTime;
    private String limitCurrencyShortName;
}
