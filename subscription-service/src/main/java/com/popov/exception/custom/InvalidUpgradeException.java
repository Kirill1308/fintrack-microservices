package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class InvalidUpgradeException extends BaseException {

    public InvalidUpgradeException(String message) {
        super(ErrorCode.BAD_REQUEST, message);
    }

}
