package com.popov.subscription.utils;

import com.popov.subscription.constant.SubscriptionPlan;
import com.popov.subscription.entity.Subscription;

import java.time.LocalDate;

public class SubscriptionUtils {

    public static LocalDate calculateNewEndDate(Subscription subscription) {
        return switch (subscription.getPlan()) {
            case MONTHLY -> subscription.getEndDate().plusMonths(1);
            case QUARTERLY -> subscription.getEndDate().plusMonths(3);
            case ANNUAL -> subscription.getEndDate().plusYears(1);
        };
    }

    public static LocalDate calculateNewEndDate(Subscription subscription, SubscriptionPlan newPlan) {
        return switch (newPlan) {
            case MONTHLY -> subscription.getEndDate().plusMonths(1);
            case QUARTERLY -> subscription.getEndDate().plusMonths(3);
            case ANNUAL -> subscription.getEndDate().plusYears(1);
        };
    }

}
