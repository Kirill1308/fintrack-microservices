package com.popov.transaction.controller;

import com.popov.transaction.dto.TransactionFilter;
import com.popov.transaction.dto.TransactionRequest;
import com.popov.transaction.dto.TransactionResponse;
import com.popov.transaction.entity.Category;
import com.popov.transaction.entity.Transaction;
import com.popov.transaction.mapper.TransactionMapper;
import com.popov.transaction.service.CategoryService;
import com.popov.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Tag(name = "Transactions", description = "API for managing transactions")
public class TransactionController {

    private final CategoryService categoryService;
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @Operation(
            summary = "Retrieve filtered transactions",
            description = "Fetches a paginated list of transactions based on the provided filter criteria"
    )

    @PostMapping("/all")
    @PreAuthorize("@accessControl.hasAccessToWallet(#filter.walletId)")
    public Page<TransactionResponse> getFilteredTransactions(
            @RequestBody(description = "Filter criteria for transactions") @Validated TransactionFilter filter,
            @Parameter(description = "Page number", example = "0") @RequestParam int page,
            @Parameter(description = "Page size", example = "10") @RequestParam int limit) {

        Pageable pageable = PageRequest.of(page, limit);
        Page<Transaction> transactions = transactionService.getFilteredTransactions(filter, pageable);

        return transactions.map(transactionMapper::toResponse);
    }

    @Operation(
            summary = "Retrieve a transaction by ID",
            description = "Fetches the details of a transaction by its unique identifier"
    )
    @GetMapping("/{id}")
    @PreAuthorize("@accessControl.hasAccessToTransaction(#id)")
    public TransactionResponse getTransactionById(@PathVariable @Parameter(description = "Transaction ID", example = "1") Long id) {
        Transaction transaction = transactionService.getTransaction(id);
        return transactionMapper.toResponse(transaction);
    }

    @Operation(
            summary = "Create a new transaction",
            description = "Adds a new transaction to the system with the provided details"
    )
    @PostMapping
    @PreAuthorize("@accessControl.hasAccessToWallet(#transactionRequest.walletId)")
    public TransactionResponse createTransaction(
            @RequestBody(description = "Details of the new transaction") TransactionRequest transactionRequest) {
        Transaction transaction = transactionMapper.toEntity(transactionRequest);

        Category category = categoryService.getCategory(transactionRequest.getCategoryId());
        transaction.setCategory(category);

        Transaction createdTransaction = transactionService.createTransaction(transaction);

        return transactionMapper.toResponse(createdTransaction);
    }

    @Operation(
            summary = "Update an existing transaction",
            description = "Updates the details of a transaction identified by its ID"
    )
    @PutMapping("/{id}")
    @PreAuthorize("@accessControl.hasAccessToModifyTransaction(#id)")
    public TransactionResponse updateTransaction(
            @PathVariable @Parameter(description = "Transaction ID", example = "1") Long id,
            @RequestBody(description = "Updated transaction details") TransactionRequest transactionRequest) {
        Transaction transaction = transactionMapper.toEntity(transactionRequest);
        Transaction updatedTransaction = transactionService.updateTransaction(id, transaction);
        return transactionMapper.toResponse(updatedTransaction);
    }

    @Operation(
            summary = "Delete a transaction",
            description = "Removes a transaction from the system by its ID"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("@accessControl.hasAccessToModifyTransaction(#id)")
    public void deleteTransaction(@PathVariable @Parameter(description = "Transaction ID", example = "1") Long id) {
        transactionService.deleteTransaction(id);
    }

}
