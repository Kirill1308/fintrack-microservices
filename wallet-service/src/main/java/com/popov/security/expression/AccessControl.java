package com.popov.security.expression;

import com.popov.security.context.UserContext;
import com.popov.security.context.UserContextHolder;
import com.popov.wallet.client.collaborator.CollaboratorClient;
import com.popov.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("accessControl")
@RequiredArgsConstructor
public class AccessControl {

    private final WalletService walletService;
    private final CollaboratorClient collaboratorClient;

    public boolean hasAccessToWallet(Long walletId) {
        UserContext userContext = UserContextHolder.getUserContext();

        if (walletService.isOwner(walletId, userContext.getUserId())) {
            return true;
        }
        return isWalletCollaborator(walletId, userContext.getUsername(), null);
    }

    public boolean hasAccessToUpdateWallet(Long walletId) {
        UserContext userContext = UserContextHolder.getUserContext();

        if (walletService.isOwner(walletId, userContext.getUserId())) {
            return true;
        }
        return isWalletCollaborator(walletId, userContext.getUsername(), "WRITE");
    }

    public boolean hasAccessToDeleteWallet(Long walletId) {
        UserContext userContext = UserContextHolder.getUserContext();

        return walletService.isOwner(walletId, userContext.getUserId());
    }

    private boolean isWalletCollaborator(Long walletId, String email, String role) {
        return collaboratorClient.checkWalletCollaborator(walletId, email, role);
    }

}
