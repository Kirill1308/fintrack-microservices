package com.popov.subscription.dto;

import com.popov.subscription.constant.SubscriptionPlan;
import com.popov.subscription.constant.SubscriptionStatus;
import com.popov.subscription.constant.SubscriptionTier;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Response object for subscription details")
public class SubscriptionResponse {

    @Schema(description = "Unique identifier of the subscription", example = "1")
    private Long id;

    @Schema(description = "Unique identifier of the user who owns the subscription", example = "1")
    private Long userId;

    @Schema(description = "Tier of the subscription (e.g., 'BASIC', 'PRO', 'PREMIUM')", example = "PREMIUM")
    private SubscriptionTier tier;

    @Schema(description = "Plan of the subscription (e.g., 'MONTHLY', 'QUARTERLY', 'ANNUAL')", example = "MONTHLY")
    private SubscriptionPlan plan;

    @Schema(description = "Current status of the subscription", example = "ACTIVE")
    private SubscriptionStatus status;

    @Schema(description = "Start date of the subscription", example = "2024-01-01")
    private LocalDate startDate;

    @Schema(description = "End date of the subscription", example = "2025-01-01")
    private LocalDate endDate;

    @Schema(description = "Whether the subscription auto-renews at the end of the period", example = "true")
    private boolean autoRenew;

}
