package com.popov.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Schema(description = "Filter criteria for retrieving transactions")
public class TransactionFilter {

    @NotNull(message = "Wallet id is required")
    @Schema(description = "Unique identifier of the wallet", example = "1")
    private Long walletId;

    @Schema(description = "List of user IDs associated with the transactions", example = "[1, 2, 3]")
    private List<Long> userIds;

    @Schema(description = "List of transaction types (e.g., 'INCOME', 'EXPENSE')", example = "['INCOME', 'EXPENSE']")
    private List<String> transactionTypes;

    @Schema(description = "List of categories to filter transactions by", example = "['Groceries', 'Entertainment']")
    private List<String> categories;

    @Schema(description = "List of payment methods to filter transactions by", example = "['CARD', 'CASH', 'TRANSFER']")
    private List<String> paymentMethods;

    @Schema(description = "Minimum transaction amount", example = "50.0")
    private Double minAmount;

    @Schema(description = "Maximum transaction amount", example = "1000.0")
    private Double maxAmount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Start date for filtering transactions", example = "2024-01-01")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "End date for filtering transactions", example = "2024-12-31")
    private LocalDate endDate;

    @Schema(description = "Field by which to order the results", example = "dateCreated")
    private String orderBy = "dateCreated";

    @Schema(description = "Direction in which to order the results (either 'asc' or 'desc')", example = "asc")
    private String orderDirection = "asc";

}
