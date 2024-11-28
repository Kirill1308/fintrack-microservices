package com.popov.transaction.dto;

import com.popov.transaction.constant.PaymentType;
import com.popov.transaction.constant.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Response object for created or updated transaction")
public class TransactionResponse {

    @Schema(description = "Unique identifier of the transaction", example = "1")
    private Long id;

    @Schema(description = "Unique identifier of the transaction", example = "1")
    private Long ownerId;

    @Schema(description = "Unique identifier of the wallet to which transaction belongs", example = "1")
    private Long walletId;

    @Schema(description = "Type of the transaction (e.g., 'INCOME' or 'EXPENSE')", example = "EXPENSE")
    private TransactionType type;

    @Schema(name = "Transaction category response object")
    private CategoryResponse category;

    @Schema(description = "Payment method used for the transaction (e.g., 'CARD', 'CASH', 'TRANSFER')", example = "CARD")
    private PaymentType payment;

    @Schema(description = "Amount of the transaction", example = "100.0")
    private Double amount;

    @Schema(description = "Optional note or description for the transaction", example = "Payment for groceries")
    private String note;

    @Schema(description = "Date when the transaction was created", example = "2024-05-15")
    private LocalDate dateCreated;

}
