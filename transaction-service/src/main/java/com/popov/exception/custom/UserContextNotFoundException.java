package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class UserContextNotFoundException extends BaseException {

    public UserContextNotFoundException() {
        super(ErrorCode.RESOURCE_NOT_FOUND, "No user context found");
    }

}
