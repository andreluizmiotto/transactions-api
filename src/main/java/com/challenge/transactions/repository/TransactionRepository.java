package com.challenge.transactions.repository;

import com.challenge.transactions.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
