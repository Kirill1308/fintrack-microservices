package com.popov.security.expression;

import com.popov.analytics.client.collaborator.CollaboratorClient;
import com.popov.analytics.client.wallet.WalletClient;
import com.popov.security.context.UserContext;
import com.popov.security.context.UserContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("accessControl")
@RequiredArgsConstructor
public class AccessControl {

    private final WalletClient walletClient;
    private final CollaboratorClient collaboratorClient;

    public boolean hasAccessToWallet(Long walletId) {
        UserContext userContext = UserContextHolder.getUserContext();

        if (isOwner(walletId, userContext.getUserId())) {
            return true;
        }

        return isCollaborator(walletId, userContext.getUsername());
    }

    private boolean isOwner(Long walletId, Long userId) {
        return walletClient.checkWalletOwner(walletId, userId);
    }

    private boolean isCollaborator(Long walletId, String email) {
        return collaboratorClient.checkWalletCollaborator(walletId, email, null);
    }

}
