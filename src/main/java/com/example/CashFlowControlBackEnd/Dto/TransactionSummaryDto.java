package com.example.CashFlowControlBackEnd.Dto;

import com.example.CashFlowControlBackEnd.Entity.Enums.TransactionType;

import java.math.BigDecimal;

public class TransactionSummaryDto {
    private TransactionType type;
    private BigDecimal totalAmount;

    public TransactionSummaryDto(TransactionType type, BigDecimal totalAmount) {
        this.type = type;
        this.totalAmount = totalAmount;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
