package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class TransactionNotFoundException extends BaseException {

    public TransactionNotFoundException(Long id) {
        super(ErrorCode.RESOURCE_NOT_FOUND, "Transaction with id " + id + " not found");
    }

}
