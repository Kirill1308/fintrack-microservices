package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class TransactionLimitReachedException extends BaseException {

    public TransactionLimitReachedException() {
        super(ErrorCode.BAD_REQUEST, "Transaction limit reached");

    }

}
