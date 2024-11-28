package com.popov.analytics.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Response object for wallet analytics")
public class WalletAnalyticsResponse {

    @Schema(description = "The current balance of the wallet", example = "1200.50")
    private Double currentWalletBalance;

    @Schema(description = "The total income during the selected period", example = "5000.00")
    private Double totalPeriodIncome;

    @Schema(description = "The total expenses during the selected period", example = "2000.00")
    private Double totalPeriodExpenses;

    @Schema(description = "The net change in the wallet during the selected period", example = "3000.00")
    private Double totalPeriodChange;

}
