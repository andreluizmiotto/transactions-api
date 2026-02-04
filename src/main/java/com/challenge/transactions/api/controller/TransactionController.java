package com.challenge.transactions.api.controller;

import com.challenge.transactions.api.dto.CreateTransactionRequest;
import com.challenge.transactions.domain.entity.Transaction;
import com.challenge.transactions.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody @Valid CreateTransactionRequest transactionRequest) {
        return transactionService.create(transactionRequest);
    }

}
