package com.popov.collaborator.client.wallet;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "wallet-service",
        url = "${application.config.wallet-url}"
)
public interface WalletClient {

    @GetMapping("/is-owner")
    Boolean checkWalletOwner(@RequestParam("walletId") Long walletId, @RequestParam("userId") Long userId);

}
