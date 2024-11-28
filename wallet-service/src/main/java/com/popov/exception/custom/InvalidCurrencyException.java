package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class InvalidCurrencyException extends BaseException {

    public InvalidCurrencyException(String message) {
        super(ErrorCode.BAD_REQUEST, message);
    }

}
