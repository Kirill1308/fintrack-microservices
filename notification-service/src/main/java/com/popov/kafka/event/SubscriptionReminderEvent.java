package com.popov.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionReminderEvent implements Serializable {

    private String email;
    private String name;
    private String currentTier;
    private String currentPlan;
    private LocalDate endDate;

}
