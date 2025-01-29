package com.alexisindustries.banktransactions.controller;


import com.alexisindustries.banktransactions.dto.limit.LimitDTO;
import com.alexisindustries.banktransactions.dto.limit.LimitPostDTO;
import com.alexisindustries.banktransactions.service.LimitService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/limits")
public class LimitController {
    private LimitService limitService;

    @PostMapping
    public ResponseEntity<LimitDTO> createLimit(@RequestBody LimitPostDTO limitPostDTO) {
        LimitDTO limit = limitService.createNewLimit(limitPostDTO);
        return ResponseEntity.ok(limit);
    }

    @GetMapping
    public ResponseEntity<List<LimitDTO>> getAllLimits() {
        return ResponseEntity.ok(limitService.getAllLimits());
    }
}
