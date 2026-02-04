package com.challenge.transactions.service;

import com.challenge.transactions.api.dto.CreateTransactionRequest;
import com.challenge.transactions.domain.entity.Account;
import com.challenge.transactions.domain.entity.Transaction;
import com.challenge.transactions.domain.enums.OperationType;
import com.challenge.transactions.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Transactional
    public Transaction create(CreateTransactionRequest transactionRequest) {
        Account account = accountService.findById(transactionRequest.accountId());
        OperationType operationType = OperationType.fromCode(transactionRequest.operationTypeId());
        BigDecimal amount = applySignal(transactionRequest.amount(), operationType);

        Transaction transaction = new Transaction(
                account,
                operationType.getCode(),
                amount,
                LocalDateTime.now()
        );

        return transactionRepository.save(transaction);
    }

    private BigDecimal applySignal(BigDecimal amount, OperationType operationType) {
        BigDecimal absAmount = amount.abs();

        if (operationType == OperationType.CREDIT_VOUCHER) {
            return absAmount;
        }
        return absAmount.negate();
    }
}
