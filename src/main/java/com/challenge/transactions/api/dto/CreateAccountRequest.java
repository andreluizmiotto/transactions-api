package com.challenge.transactions.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CreateAccountRequest(
        @NotBlank(message = "Document Number is required")
        @JsonProperty("document_number")
        String documentNumber
) {
}
