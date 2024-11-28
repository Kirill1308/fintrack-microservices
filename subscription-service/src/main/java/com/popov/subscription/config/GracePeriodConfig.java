package com.popov.subscription.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "subscription")
@Getter
@Setter
public class GracePeriodConfig {
    private int gracePeriodDays = 7; // Default 7 days
    private List<Integer> reminderDays = Arrays.asList(5, 3, 1); // Days before grace period ends to send reminders
}
