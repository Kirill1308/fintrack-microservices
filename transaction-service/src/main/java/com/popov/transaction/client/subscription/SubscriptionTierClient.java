package com.popov.transaction.client.subscription;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "subscription-service",
        url = "${application.config.subscription-tier-url}"
)
public interface SubscriptionTierClient {

    @GetMapping("/user")
    SubscriptionTierResponse getSubscriptionTier(@RequestParam("userId") Long userId);

}
