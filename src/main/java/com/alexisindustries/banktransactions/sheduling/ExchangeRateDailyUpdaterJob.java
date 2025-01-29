package com.alexisindustries.banktransactions.sheduling;

import com.alexisindustries.banktransactions.service.ExchangeRateService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@Component
public class ExchangeRateDailyUpdaterJob {
    private ExchangeRateService exchangeRateService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void fetchExchangeRatesDaily() {
        exchangeRateService.fetchExchangeRate();
    }
}
