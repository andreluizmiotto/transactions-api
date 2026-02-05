package com.challenge.transactions.api.dto;

import com.challenge.transactions.domain.entity.Account;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountResponse(
        @JsonProperty("account_id") Long accountId,
        @JsonProperty("document_number") String documentNumber
) {
    public static AccountResponse fromAccount(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getDocumentNumber()
        );
    }
}
