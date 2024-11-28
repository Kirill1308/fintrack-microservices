package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class WalletNotFoundException extends BaseException {

    public WalletNotFoundException(Long walletId) {
        super(ErrorCode.RESOURCE_NOT_FOUND, "Wallet with id " + walletId + " not found");
    }

}
