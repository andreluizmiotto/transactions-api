package com.challenge.transactions.api.dto;

import com.challenge.transactions.domain.entity.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        Long id,
        @JsonProperty("account_id") Long accountId,
        @JsonProperty("operation_type_id") Integer operationTypeId,
        BigDecimal amount,
        @JsonProperty("event_date") LocalDateTime eventDate
) {
    public static TransactionResponse fromTransaction(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getAccount().getId(),
                transaction.getOperationType(),
                transaction.getAmount(),
                transaction.getEventDate()
        );
    }
}
