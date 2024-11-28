package com.popov.subscription.dto;

import com.popov.subscription.constant.SubscriptionPlan;
import com.popov.subscription.constant.SubscriptionTier;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request object for creating a subscription")
public class SubscriptionRequest {

    @Schema(description = "Unique identifier of the user", example = "1")
    @NotNull(message = "User ID is required")
    private Long userId;

    @Schema(description = "Tier of the subscription (e.g., 'BASIC', 'PRO', 'PREMIUM')", example = "PREMIUM")
    @NotNull(message = "Tier is required")
    private SubscriptionTier tier;

    @Schema(description = "Plan of the subscription (e.g., 'MONTHLY', 'QUARTERLY', 'ANNUAL')", example = "MONTHLY")
    @NotNull(message = "Plan is required")
    private SubscriptionPlan plan;

}
