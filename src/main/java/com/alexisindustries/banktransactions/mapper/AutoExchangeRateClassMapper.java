package com.alexisindustries.banktransactions.mapper;

import com.alexisindustries.banktransactions.dto.exchangerate.ExchangeRateDTO;
import com.alexisindustries.banktransactions.model.ExchangeRate;
import org.mapstruct.Mapper;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@Mapper(componentModel = "spring")
public interface AutoExchangeRateClassMapper {
    ExchangeRateDTO fromExchangeRate(ExchangeRate exchangeRate);
    ExchangeRate fromExchangeRateDTO(ExchangeRateDTO exchangeRateDTO);
}
