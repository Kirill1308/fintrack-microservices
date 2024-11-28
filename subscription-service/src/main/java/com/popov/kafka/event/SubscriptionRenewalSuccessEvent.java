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
public class SubscriptionRenewalSuccessEvent implements Serializable {

    private String email;
    private String name;
    private String tier;
    private String plan;
    private LocalDate startDate;
    private LocalDate endDate;

}
