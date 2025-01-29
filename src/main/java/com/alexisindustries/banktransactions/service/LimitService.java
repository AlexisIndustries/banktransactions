package com.alexisindustries.banktransactions.service;

import com.alexisindustries.banktransactions.dto.limit.LimitDTO;
import com.alexisindustries.banktransactions.dto.limit.LimitPostDTO;
import com.alexisindustries.banktransactions.model.Category;
import com.alexisindustries.banktransactions.model.Limit;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
public interface LimitService {
    LimitDTO createNewLimit(LimitPostDTO limitPostDTO);
    List<LimitDTO> getAllLimits();
}
