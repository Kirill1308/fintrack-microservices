package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException(String fieldName, Object fieldValue) {
        super(ErrorCode.RESOURCE_NOT_FOUND, "User with " + fieldName + " : " + fieldValue + " not found");
    }

}
