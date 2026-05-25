package com.example.CashFlowControlBackEnd.Repository;

import com.example.CashFlowControlBackEnd.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
