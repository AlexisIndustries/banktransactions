package com.alexisindustries.banktransactions.mapper;

import com.alexisindustries.banktransactions.dto.limit.LimitDTO;
import com.alexisindustries.banktransactions.dto.limit.LimitPostDTO;
import com.alexisindustries.banktransactions.model.Limit;
import org.mapstruct.Mapper;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@Mapper(componentModel = "spring")
public interface AutoLimitClassMapper {
    LimitDTO fromLimit(Limit limit);
    Limit fromLimitDTO(LimitDTO limitDTO);
    Limit fromLimitPostDTO(LimitPostDTO limitPostDTO);
}
