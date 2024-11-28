package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class CategoryNotFoundException extends BaseException {

    public CategoryNotFoundException(Long categoryId) {
        super(ErrorCode.RESOURCE_NOT_FOUND, "Category with id " + categoryId + " not found");
    }

}
