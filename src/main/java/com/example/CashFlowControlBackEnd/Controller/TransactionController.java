package com.example.CashFlowControlBackEnd.Controller;

import com.example.CashFlowControlBackEnd.Dto.TransactionRequestDto;
import com.example.CashFlowControlBackEnd.Dto.TransactionSummaryDto;
import com.example.CashFlowControlBackEnd.Entity.Enums.TransactionType;
import com.example.CashFlowControlBackEnd.Entity.Transaction;
import com.example.CashFlowControlBackEnd.Exceptions.GenericException;
import com.example.CashFlowControlBackEnd.Service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/save")
    public Transaction save(@RequestBody TransactionRequestDto dto) throws GenericException {
        return transactionService.save(dto);
    }

    @PutMapping("/update/{id}")
    public Transaction update(@PathVariable Long id, @RequestBody TransactionRequestDto dto) throws GenericException {
        return transactionService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) throws GenericException {
        transactionService.delete(id);
    }

    @GetMapping("/summary")
    public TransactionSummaryDto getSummaryByType(@RequestParam TransactionType type) throws GenericException {
        return transactionService.sumByType(type);
    }
}
