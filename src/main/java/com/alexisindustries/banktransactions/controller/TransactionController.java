package com.alexisindustries.banktransactions.controller;

import com.alexisindustries.banktransactions.dto.transaction.TransactionDTO;
import com.alexisindustries.banktransactions.dto.transaction.TransactionPostDTO;
import com.alexisindustries.banktransactions.model.Transaction;
import com.alexisindustries.banktransactions.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author <a href="https://github.com/AlexisIndustries">AlexisIndustries</a>
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionPostDTO transactionPostDTO) {
        TransactionDTO savedTransaction = transactionService.saveTransaction(transactionPostDTO);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping("/exceeded")
    public ResponseEntity<List<TransactionDTO>> getTransactions() {
        List<TransactionDTO> exceededTransactions = transactionService.getTransactions();
        return ResponseEntity.ok(exceededTransactions);
    }
}
