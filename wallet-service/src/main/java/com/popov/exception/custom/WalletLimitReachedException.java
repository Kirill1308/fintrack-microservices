package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class WalletLimitReachedException extends BaseException {

    public WalletLimitReachedException() {
        super(ErrorCode.BAD_REQUEST, "Wallet limit reached");

    }

}
