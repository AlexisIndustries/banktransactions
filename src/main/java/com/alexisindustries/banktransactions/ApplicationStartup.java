package com.alexisindustries.banktransactions;

import com.alexisindustries.banktransactions.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@AllArgsConstructor
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    private ExchangeRateService exchangeRateService;
    private final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
       exchangeRateService.fetchExchangeRate();
    }
}
