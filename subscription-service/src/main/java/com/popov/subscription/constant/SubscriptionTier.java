package com.popov.subscription.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum SubscriptionTier {
    BASIC(0.0, 5, 100, false, false),
    PRO(9.99, 10, 500, true, true),
    PREMIUM(19.99, 20, 1000, true, true);

    private final double price;
    private final int maxWallets;
    private final int maxTransactions;
    private final boolean isCollaborative;
    private final boolean isCustomizable;

}
