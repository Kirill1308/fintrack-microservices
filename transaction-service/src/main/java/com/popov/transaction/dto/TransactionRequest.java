package com.popov.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.popov.transaction.constant.PaymentType;
import com.popov.transaction.constant.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Request body for creating or updating a transaction")
public class TransactionRequest {

    @NotNull(message = "Wallet id is required")
    @Positive(message = "Wallet id should be positive")
    @Schema(description = "Unique identifier of the wallet", example = "1")
    private Long walletId;

    @NotNull(message = "Type is required")
    @Schema(description = "Type of the transaction (e.g., 'INCOME' or 'EXPENSE')", example = "EXPENSE")
    private TransactionType type;

    @NotNull(message = "Category id is required")
    @Positive(message = "Category id should be positive")
    @Schema(description = "Unique identifier of the transaction category", example = "1")
    private Long categoryId;

    @NotNull(message = "Payment method is required")
    @Schema(description = "Payment method used for the transaction (e.g., 'CARD', 'CASH', 'TRANSFER')", example = "CARD")
    private PaymentType payment;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount should be positive")
    @Schema(description = "Amount of the transaction", example = "100.0")
    private Double amount;

    @Schema(description = "Optional note or description for the transaction", example = "Payment for groceries")
    private String note;

    @NotNull(message = "Date created is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Date when the transaction was created", example = "2024-05-15")
    private LocalDate dateCreated;

}
