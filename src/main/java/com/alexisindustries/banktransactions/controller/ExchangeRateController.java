package com.alexisindustries.banktransactions.controller;

import com.alexisindustries.banktransactions.dto.exchangerate.ExchangeRateDTO;
import com.alexisindustries.banktransactions.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/exchange-rates")
public class ExchangeRateController {
    private ExchangeRateService exchangeRateService;

    @GetMapping
    public ResponseEntity<List<ExchangeRateDTO>> getExchangeRates() {
        return ResponseEntity.ok(exchangeRateService.getAllExchangeRates());
    }
}
