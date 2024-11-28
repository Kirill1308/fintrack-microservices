package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;
import com.popov.kafka.event.TransactionEventType;

public class InvalidEventTypeException extends BaseException {

    public InvalidEventTypeException(TransactionEventType type) {
        super(ErrorCode.BAD_REQUEST, "Invalid event type: " + type);
    }

}
