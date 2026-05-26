package com.example.CashFlowControlBackEnd.Dto;

import com.example.CashFlowControlBackEnd.Entity.Enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRequestDto {
    private String description;
    private BigDecimal amount;
    private LocalDateTime date;
    private TransactionType type;
    private Long categoryId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
