package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class PasswordResetException extends BaseException {

    public PasswordResetException(String message) {
        super(ErrorCode.BAD_REQUEST, message);
    }

    public PasswordResetException(String fieldName, Object fieldValue) {
        super(ErrorCode.RESOURCE_NOT_FOUND, "Password reset with " + fieldName + " : " + fieldValue + " not found");
    }

}
