package com.popov.analytics.controller;

import com.popov.analytics.dto.WalletAnalyticsResponse;
import com.popov.analytics.service.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
@Tag(name = "Analytics", description = "Endpoints for managing wallet analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/wallet/{walletId}")
    @PreAuthorize("@accessControl.hasAccessToWallet(#walletId)")
    @Operation(
            summary = "Get wallet analytics for a specific period",
            description = "Returns wallet analytics.",
            parameters = {
                    @Parameter(name = "walletId", description = "ID of the wallet"),
                    @Parameter(name = "periodStart", description = "Start date of the period"),
                    @Parameter(name = "periodEnd", description = "End date of the period")
            }
    )
    public WalletAnalyticsResponse getWalletAnalytics(
            @PathVariable Long walletId,
            @RequestParam LocalDate periodStart,
            @RequestParam LocalDate periodEnd) {

        return analyticsService.getAnalyticsForPeriod(walletId, periodStart, periodEnd);
    }

}
