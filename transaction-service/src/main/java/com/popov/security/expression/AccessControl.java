package com.popov.security.expression;

import com.popov.security.context.UserContext;
import com.popov.security.context.UserContextHolder;
import com.popov.transaction.client.collaborator.CollaboratorClient;
import com.popov.transaction.client.subscription.SubscriptionTierClient;
import com.popov.transaction.client.wallet.WalletClient;
import com.popov.transaction.entity.Transaction;
import com.popov.transaction.service.CategoryService;
import com.popov.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("accessControl")
@RequiredArgsConstructor
public class AccessControl {

    private final TransactionService transactionService;
    private final CategoryService categoryService;

    private final WalletClient walletClient;
    private final CollaboratorClient collaboratorClient;
    private final SubscriptionTierClient subscriptionTierClient;

    public boolean hasAccessToTransaction(Long id) {
        return hasAccess(id, null);
    }

    public boolean hasAccessToModifyTransaction(Long id) {
        return hasAccess(id, "WRITE");
    }

    public boolean hasAccessToWallet(Long walletId) {
        UserContext userContext = UserContextHolder.getUserContext();

        Long userId = userContext.getUserId();
        return isWalletOwner(walletId, userId);
    }

    public boolean canReadCategory(Long id) {
        UserContext userContext = UserContextHolder.getUserContext();

        Long userId = userContext.getUserId();
        return categoryService.isCategoryOwner(id, userId);
    }

    public boolean canCreateCategory() {
        UserContext userContext = UserContextHolder.getUserContext();

        if (userContext.isAdmin()) {
            return true;
        }

        var tier = subscriptionTierClient.getSubscriptionTier(userContext.getUserId());
        return tier.isCustomizable();
    }

    private boolean hasAccess(Long transactionId, String role) {
        UserContext userContext = UserContextHolder.getUserContext();

        Long userId = userContext.getUserId();
        Transaction transaction = transactionService.getTransaction(transactionId);
        Long walletId = transaction.getWalletId();

        if (isWalletOwner(walletId, userId)) {
            return true;
        }

        String email = userContext.getUsername();
        return isWalletCollaborator(walletId, email, role);
    }

    private boolean isWalletOwner(Long walletId, Long userId) {
        return walletClient.checkWalletOwner(walletId, userId);
    }

    private boolean isWalletCollaborator(Long walletId, String email, String role) {
        return collaboratorClient.checkWalletCollaborator(walletId, email, role);
    }

}
