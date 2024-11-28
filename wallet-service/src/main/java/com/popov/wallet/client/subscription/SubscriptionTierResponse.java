package com.popov.wallet.client.subscription;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubscriptionTierResponse {

    private String tier;
    private double price;
    private int maxWallets;
    private int maxTransactions;
    private boolean collaborative;
    private boolean customizable;

}
