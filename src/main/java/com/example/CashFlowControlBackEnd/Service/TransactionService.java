package com.example.CashFlowControlBackEnd.Service;

import com.example.CashFlowControlBackEnd.Dto.TransactionRequestDto;
import com.example.CashFlowControlBackEnd.Entity.Category;
import com.example.CashFlowControlBackEnd.Entity.Transaction;
import com.example.CashFlowControlBackEnd.Exceptions.Enums.GenericExceptionKey;
import com.example.CashFlowControlBackEnd.Exceptions.GenericException;
import com.example.CashFlowControlBackEnd.Repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction save(TransactionRequestDto dto) throws GenericException {
        validateFields(dto);

        Category category = new Category();
        category.setId(dto.getCategoryId());

        Transaction transaction = new Transaction();
        transaction.setDescription(dto.getDescription());
        transaction.setAmount(dto.getAmount());
        transaction.setDate(dto.getDate());
        transaction.setType(dto.getType());
        transaction.setCategory(category);

        return transactionRepository.save(transaction);
    }

    private void validateFields(TransactionRequestDto dto) throws GenericException {
        if (dto.getAmount() == null || dto.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            throw new GenericException(GenericExceptionKey.INVALID_TRANSACTION_AMOUNT);
        }

        if (dto.getDate() == null) {
            throw new GenericException(GenericExceptionKey.TRANSACTION_DATE_IS_REQUIRED);
        }

        if (dto.getType() == null) {
            throw new GenericException(GenericExceptionKey.TRANSACTION_TYPE_IS_REQUIRED);
        }

        if (dto.getCategoryId() == null) {
            throw new GenericException(GenericExceptionKey.CATEGORY_ID_IS_REQUIRED);
        }
    }
}
