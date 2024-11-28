package com.popov.subscription.controller;

import com.popov.subscription.dto.SubscriptionRequest;
import com.popov.subscription.dto.SubscriptionResponse;
import com.popov.subscription.entity.Subscription;
import com.popov.subscription.mapper.SubscriptionMapper;
import com.popov.subscription.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Subscription API", description = "Endpoints for managing subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    @Operation(
            summary = "Get subscription details by user ID",
            description = "Retrieve the subscription information for a specific user based on user ID"
    )
    @GetMapping
    @PreAuthorize("@accessControl.hasAccess(#userId)")
    public SubscriptionResponse getSubscriptionByUserId(@RequestParam Long userId) {
        Subscription subscription = subscriptionService.getSubscriptionByUserId(userId);
        return subscriptionMapper.toResponse(subscription);
    }

    @Operation(
            summary = "Create a basic subscription for a user",
            description = "Create a basic subscription for a given user by their user ID"
    )
    @PostMapping("/create")
    public void createBasicSubscription(@RequestParam Long userId) {
        subscriptionService.createBasicSubscription(userId);
    }

    @Operation(
            summary = "Upgrade the subscription tier for a user",
            description = "Upgrade the subscription tier for a user to a new plan and tier"
    )
    @PostMapping("/upgrade")
    @PreAuthorize("@accessControl.hasAccess(#request.userId)")
    public void upgradeTier(@RequestBody SubscriptionRequest request) {
        subscriptionService.upgradeTier(request.getUserId(), request.getTier(), request.getPlan());
    }

    @Operation(
            summary = "Cancel a subscription for a user",
            description = "Cancel the subscription for a user based on user ID"
    )
    @PostMapping("/cancel")
    @PreAuthorize("@accessControl.hasAccess(#userId)")
    public void cancelSubscription(@RequestParam Long userId) {
        subscriptionService.cancelSubscription(userId);
    }

}
