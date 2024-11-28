package com.popov.subscription.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "Response object for subscription tier details")
public class SubscriptionTierResponse {

    @Schema(description = "Name of the subscription tier (e.g., 'BASIC', 'PRO', 'PREMIUM')", example = "PREMIUM")
    private String tier;

    @Schema(description = "Price of the subscription tier", example = "19.99")
    private double price;

    @Schema(description = "Maximum number of wallets allowed in this tier", example = "5")
    private int maxWallets;

    @Schema(description = "Maximum number of transactions allowed in this tier", example = "100")
    private int maxTransactions;

    @Schema(description = "Whether the tier supports collaboration features", example = "true")
    private boolean collaborative;

    @Schema(description = "Whether the tier allows customization options", example = "true")
    private boolean customizable;

}
