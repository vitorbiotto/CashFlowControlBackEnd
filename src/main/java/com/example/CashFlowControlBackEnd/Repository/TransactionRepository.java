package com.example.CashFlowControlBackEnd.Repository;

import com.example.CashFlowControlBackEnd.Entity.Enums.TransactionType;
import com.example.CashFlowControlBackEnd.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    boolean existsById(Long id);

    @Query("SELECT COALESCE(SUM(transaction.amount), 0) FROM Transaction transaction WHERE transaction.type = :type")
    BigDecimal sumByType(@Param("type") TransactionType type);
}
