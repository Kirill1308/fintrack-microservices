package com.popov.wallet.client.collaborator;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "collaborator-service",
        url = "${application.config.collaborator-url}"
)
public interface CollaboratorClient {

    @GetMapping("/shared-wallets")
    List<Long> getSharedWallets(@RequestParam("email") String email);

    @GetMapping("/is-collaborator")
    Boolean checkWalletCollaborator(@RequestParam("walletId") Long walletId,
                                    @RequestParam("email") String email,
                                    @RequestParam("role") String role);

}
