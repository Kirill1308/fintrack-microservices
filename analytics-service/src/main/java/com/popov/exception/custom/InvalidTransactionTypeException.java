package com.popov.exception.custom;

import com.popov.analytics.constant.TransactionType;
import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class InvalidTransactionTypeException extends BaseException {

    public InvalidTransactionTypeException(TransactionType type) {
        super(ErrorCode.BAD_REQUEST, "Invalid transaction type: " + type);
    }

}
