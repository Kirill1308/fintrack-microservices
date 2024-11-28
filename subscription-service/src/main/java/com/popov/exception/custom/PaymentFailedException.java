package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class PaymentFailedException extends BaseException {

    public PaymentFailedException(String message) {
        super(ErrorCode.BAD_REQUEST, message);
    }

}
