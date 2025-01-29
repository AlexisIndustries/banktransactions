package com.alexisindustries.banktransactions.dto.limit;

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
public class LimitPostDTO {
    private Category category;
    private BigDecimal limitSum;
}
