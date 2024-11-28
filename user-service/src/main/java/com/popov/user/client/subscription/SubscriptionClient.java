package com.popov.user.client.subscription;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "subscription-service",
        url = "${application.config.subscription-url}"
)
public interface SubscriptionClient {

    @PostMapping("/create")
    void createBasicSubscription(@RequestParam Long userId);

}
