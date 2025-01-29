package com.alexisindustries.banktransactions.dto.transaction;

import com.alexisindustries.banktransactions.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@Getter
@Setter
public class TransactionDTO {
    private String id;
    private Integer accountFrom;
    private Integer accountTo;
    private String currencyShortName;
    private BigDecimal sum;
    private Category expenseCategory;
    private LocalDateTime datetime;
    private boolean limitExceeded;
}
