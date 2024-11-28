package com.popov.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionUpgradeConfirmEvent implements Serializable {

    private String email;
    private String name;
    private String tier;
    private String plan;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean autoRenew;

}
