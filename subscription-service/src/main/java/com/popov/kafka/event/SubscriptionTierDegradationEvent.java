package com.popov.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionTierDegradationEvent implements Serializable {

    private String email;
    private String name;
    private String degradedTier;

}
