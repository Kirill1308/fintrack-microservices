package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException(Long userId) {
        super(ErrorCode.RESOURCE_NOT_FOUND, "User with id" + " : " + userId + " not found");
    }

}
