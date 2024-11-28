package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class UserCreationException extends BaseException {

    public UserCreationException(String message) {
        super(ErrorCode.INTERNAL_SERVER_ERROR, message);
    }

}
