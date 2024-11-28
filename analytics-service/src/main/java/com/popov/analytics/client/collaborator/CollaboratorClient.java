package com.popov.analytics.client.collaborator;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "collaborator-service",
        url = "${application.config.collaborator-url}"
)
public interface CollaboratorClient {

    @GetMapping("/is-collaborator")
    Boolean checkWalletCollaborator(@RequestParam("walletId") Long walletId,
                                    @RequestParam("email") String email,
                                    @RequestParam("role") String role);

}
