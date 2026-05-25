package com.example.CashFlowControlBackEnd.Controller;

import com.example.CashFlowControlBackEnd.Dto.TransactionRequestDto;
import com.example.CashFlowControlBackEnd.Entity.Transaction;
import com.example.CashFlowControlBackEnd.Exceptions.GenericException;
import com.example.CashFlowControlBackEnd.Service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
