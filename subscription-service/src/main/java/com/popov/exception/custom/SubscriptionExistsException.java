package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class SubscriptionExistsException extends BaseException {

    public SubscriptionExistsException() {
        super(ErrorCode.DUPLICATE_RESOURCE, "Subscription already exists");
    }

}
