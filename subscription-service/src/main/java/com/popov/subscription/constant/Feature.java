package com.popov.subscription.constant;

import lombok.Getter;

@Getter
public enum Feature {
    // Basic features
    WALLETS_5("Up to 5 wallets", 5),
    TRANSACTIONS_100("Up to 100 transactions", 100),

    // Pro features
    WALLETS_10("Up to 10 wallets", 10),
    TRANSACTIONS_500("Up to 500 transactions", 500),
    COLLABORATIVE("Collaborative features"),
    CUSTOMIZABLE("Customization options"),

    // Premium features
    WALLETS_20("Up to 20 wallets", 20),
    TRANSACTIONS_1000("Up to 1000 transactions", 1000);

    private final String description;
    private final Integer limit;

    Feature(String description) {
        this.description = description;
        this.limit = null;
    }

    Feature(String description, Integer limit) {
        this.description = description;
        this.limit = limit;
    }

}
