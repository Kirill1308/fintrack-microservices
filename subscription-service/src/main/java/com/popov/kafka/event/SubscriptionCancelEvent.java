package com.popov.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionCancelEvent implements Serializable {

    private String email;
    private String name;
    private String tier;
    private String plan;
    private LocalDateTime cancelDate;

}
