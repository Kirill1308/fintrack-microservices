package com.popov.wallet.controller;

import com.popov.wallet.dto.WalletRequest;
import com.popov.wallet.dto.WalletResponse;
import com.popov.wallet.entity.Wallet;
import com.popov.wallet.mapper.WalletMapper;
import com.popov.wallet.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
@Tag(name = "Wallet API", description = "Endpoints for managing wallets")
public class WalletController {

    private final WalletService walletService;
    private final WalletMapper walletMapper;

    @Operation(
            summary = "Get wallet details by ID",
            description = "Retrieve the details of a specific wallet by its ID"
    )
    @GetMapping("/{id}")
    @PreAuthorize("@accessControl.hasAccessToWallet(#id)")
    public WalletResponse getWalletById(@PathVariable Long id) {
        Wallet wallet = walletService.getWalletById(id);
        return walletMapper.toResponse(wallet);
    }

    @Operation(
            summary = "Get all wallets",
            description = "Retrieve a list of all wallets"
    )
    @GetMapping("/all")
    public List<WalletResponse> getAllWallets() {
        List<Wallet> wallets = walletService.getAllWallets();
        return walletMapper.toResponse(wallets);
    }

    @Operation(
            summary = "Create a new wallet",
            description = "Create a new wallet with the provided details"
    )
    @PostMapping
    public WalletResponse createWallet(@Validated @RequestBody WalletRequest walletRequest) {
        Wallet wallet = walletMapper.toEntity(walletRequest);
        Wallet createdWallet = walletService.createWallet(wallet);
        return walletMapper.toResponse(createdWallet);
    }

    @Operation(
            summary = "Update wallet details",
            description = "Update the details of an existing wallet by ID"
    )
    @PutMapping("/{id}")
    @PreAuthorize("@accessControl.hasAccessToUpdateWallet(#id)")
    public WalletResponse updateWallet(@PathVariable Long id,
                                       @Validated @RequestBody WalletRequest walletRequest) {
        Wallet wallet = walletMapper.toEntity(walletRequest);
        Wallet updatedWallet = walletService.updateWallet(id, wallet);
        return walletMapper.toResponse(updatedWallet);
    }

    @Operation(
            summary = "Delete wallet",
            description = "Delete a specific wallet by ID"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("@accessControl.hasAccessToDeleteWallet(#id)")
    public void deleteWallet(@PathVariable Long id) {
        walletService.deleteWallet(id);
    }

    @Operation(
            summary = "Check if a user owns a wallet",
            description = "Check whether the specified user is the owner of the given wallet"
    )
    @GetMapping("/is-owner")
    public boolean checkWalletOwnership(@RequestParam Long walletId, @RequestParam Long userId) {
        return walletService.isOwner(walletId, userId);
    }

}
