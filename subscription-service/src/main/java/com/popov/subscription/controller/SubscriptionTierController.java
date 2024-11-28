package com.popov.subscription.controller;

import com.popov.subscription.constant.SubscriptionTier;
import com.popov.subscription.dto.SubscriptionTierResponse;
import com.popov.subscription.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/subscription-tiers")
@RequiredArgsConstructor
@Tag(name = "Subscription Tier API", description = "Endpoints for managing subscription tiers")
public class SubscriptionTierController {

    private final SubscriptionService subscriptionService;

    @Operation(
            summary = "Get all available subscription tiers",
            description = "Retrieve a list of all subscription tiers, including their details"
    )
    @GetMapping("/all")
    public List<SubscriptionTierResponse> getAllSubscriptionTiers() {
        return Arrays.stream(SubscriptionTier.values())
                .map(this::mapToResponse)
                .toList();
    }

    @Operation(
            summary = "Get subscription tier details by tier name",
            description = "Retrieve the details of a specific subscription tier by its name"
    )
    @GetMapping("/tier/{tier}")
    public SubscriptionTierResponse getSubscriptionTierDetails(@PathVariable SubscriptionTier tier) {
        SubscriptionTier tierDetails = SubscriptionTier.valueOf(tier.name());
        return mapToResponse(tierDetails);
    }

    @Operation(
            summary = "Get subscription tier details for a specific user",
            description = "Retrieve the subscription tier details for a given user by their user ID"
    )
    @GetMapping("/user")
    public SubscriptionTierResponse getSubscriptionTierDetails(@RequestParam Long userId) {
        SubscriptionTier tier = subscriptionService.getSubscriptionByUserId(userId).getTier();
        SubscriptionTier tierDetails = SubscriptionTier.valueOf(tier.name());
        return mapToResponse(tierDetails);
    }

    private SubscriptionTierResponse mapToResponse(SubscriptionTier tier) {
        return SubscriptionTierResponse.builder()
                .tier(tier.name())
                .price(tier.getPrice())
                .maxWallets(tier.getMaxWallets())
                .maxTransactions(tier.getMaxTransactions())
                .collaborative(tier.isCollaborative())
                .customizable(tier.isCustomizable())
                .build();
    }

}
