package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class SubscriptionNotFoundException extends BaseException {

    public SubscriptionNotFoundException(String message) {
        super(ErrorCode.RESOURCE_NOT_FOUND, message);
    }

    public SubscriptionNotFoundException(Long subscriptionId) {
        super(ErrorCode.RESOURCE_NOT_FOUND, "Subscription with id " + subscriptionId + " not found");
    }

}
