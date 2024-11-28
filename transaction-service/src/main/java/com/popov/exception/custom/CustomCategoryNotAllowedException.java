package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class CustomCategoryNotAllowedException extends BaseException {

    public CustomCategoryNotAllowedException() {
        super(ErrorCode.BAD_REQUEST, "Custom categories are not allowed");
    }

}
