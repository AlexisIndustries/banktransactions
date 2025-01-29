package com.alexisindustries.banktransactions.feign;

import com.alexisindustries.banktransactions.dto.feign.OpenExchangeRatesResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@FeignClient(name = "oer", url = "${oer.baseurl}")
public interface OpenExchangeRatesFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/latest.json?app_id=${oer.appid}")
    OpenExchangeRatesResult getLatestOpenExchangeRates();
}
