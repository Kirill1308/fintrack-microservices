package com.popov.security.expression;

import com.popov.collaborator.client.subscription.SubscriptionTierClient;
import com.popov.collaborator.client.wallet.WalletClient;
import com.popov.collaborator.service.CollaboratorService;
import com.popov.security.context.UserContext;
import com.popov.security.context.UserContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("accessControl")
@RequiredArgsConstructor
public class AccessControl {

    private final CollaboratorService collaboratorService;

    private final WalletClient walletClient;
    private final SubscriptionTierClient subscriptionTierClient;

    public boolean hasAccessToWallet(Long walletId) {
        UserContext userContext = UserContextHolder.getUserContext();

        if (isWalletOwner(walletId, userContext.getUserId())) {
            return true;
        }

        return isWalletCollaborator(walletId, userContext.getUsername(), null);
    }

    public boolean hasPermissionToInviteCollaborator(Long walletId) {
        UserContext userContext = UserContextHolder.getUserContext();

        if (isWalletOwner(walletId, userContext.getUserId())) {
            var tier = subscriptionTierClient.getSubscriptionTier(userContext.getUserId());
            return tier.isCollaborative();
        }

        return false;
    }

    public boolean hasAccessToUpdateCollaboratorRole(Long walletId) {
        UserContext userContext = UserContextHolder.getUserContext();

        if (hasAccessToWallet(walletId) && isWalletOwner(walletId, userContext.getUserId())) {
            return true;
        }

        return isWalletCollaborator(walletId, userContext.getUsername(), "ADMIN");
    }

    private boolean isWalletOwner(Long walletId, Long userId) {
        return walletClient.checkWalletOwner(walletId, userId);
    }

    private boolean isWalletCollaborator(Long walletId, String email, String role) {
        return collaboratorService.isCollaborator(walletId, email, role);
    }

}
