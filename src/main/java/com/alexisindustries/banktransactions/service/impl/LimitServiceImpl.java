package com.alexisindustries.banktransactions.service.impl;

import com.alexisindustries.banktransactions.dto.limit.LimitDTO;
import com.alexisindustries.banktransactions.dto.limit.LimitPostDTO;
import com.alexisindustries.banktransactions.mapper.AutoLimitClassMapper;
import com.alexisindustries.banktransactions.model.Category;
import com.alexisindustries.banktransactions.model.Limit;
import com.alexisindustries.banktransactions.repository.LimitRepository;
import com.alexisindustries.banktransactions.service.LimitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@AllArgsConstructor
@Service
public class LimitServiceImpl implements LimitService {
    private LimitRepository limitRepository;
    private AutoLimitClassMapper autoLimitClassMapper;

    @Override
    public LimitDTO createNewLimit(LimitPostDTO limitPostDTO) {
        if (limitRepository.findByCategory(limitPostDTO.getCategory()).isPresent()) {
            throw new RuntimeException("Limit already exists for category: " + limitPostDTO.getCategory());
        }

        Limit limit = autoLimitClassMapper.fromLimitPostDTO(limitPostDTO);
        limit.setLimitDateTime(LocalDateTime.now());
        limit.setLimitCurrencyShortName("USD");

        return autoLimitClassMapper.fromLimit(limitRepository.save(limit));
    }

    @Override
    public List<LimitDTO> getAllLimits() {
        return limitRepository.findAll().stream().map(autoLimitClassMapper::fromLimit).collect(Collectors.toList());
    }
}
