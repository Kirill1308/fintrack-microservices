package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class AuthenticationException extends BaseException {

    public AuthenticationException(String message) {
        super(ErrorCode.FORBIDDEN, message);
    }

}
