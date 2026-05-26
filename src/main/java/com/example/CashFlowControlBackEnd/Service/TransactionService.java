package com.example.CashFlowControlBackEnd.Service;

import com.example.CashFlowControlBackEnd.Dto.TransactionRequestDto;
import com.example.CashFlowControlBackEnd.Dto.TransactionSummaryDto;
import com.example.CashFlowControlBackEnd.Entity.Category;
import com.example.CashFlowControlBackEnd.Entity.Enums.TransactionType;
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

    public Transaction update(Long transactionId, TransactionRequestDto dto) throws GenericException {
        Transaction newTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new GenericException(GenericExceptionKey.TRANSACTION_NOT_FOUND));

        if (dto.getDescription() != null) {
            newTransaction.setDescription(dto.getDescription());
        }

        newTransaction.setAmount(dto.getAmount());
        newTransaction.setDate(dto.getDate());
        newTransaction.setType(dto.getType());

        if (dto.getCategoryId() != null) {
            Category category = new Category();
            category.setId(dto.getCategoryId());
            newTransaction.setCategory(category);
        } else {
            throw new GenericException(GenericExceptionKey.CATEGORY_ID_IS_REQUIRED);
        }

        return transactionRepository.save(newTransaction);
    }

    public void delete(Long id) throws GenericException {
        validateIfIdExists(id);
        transactionRepository.deleteById(id);
    }

    private void validateIfIdExists(Long id) throws GenericException {
        if (id == null) {
            throw new GenericException(GenericExceptionKey.TRANSACTION_ID_IS_REQUIRED);
        }

        if (!transactionRepository.existsById(id)) {
            throw new GenericException(GenericExceptionKey.TRANSACTION_ID_NOT_FOUND);
        }
    }

    public TransactionSummaryDto sumByType(TransactionType type) throws GenericException {
        if (type == null) {
            throw new GenericException(GenericExceptionKey.TRANSACTION_TYPE_IS_REQUIRED);
        }

        BigDecimal totalAmount = transactionRepository.sumByType(type);
        return new TransactionSummaryDto(type, totalAmount);
    }

    private void validateFields(TransactionRequestDto dto) throws GenericException {
        if (dto.getAmount() == null || dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
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
