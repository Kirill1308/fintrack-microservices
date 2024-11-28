package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class InvitationNotFoundException extends BaseException {

    public InvitationNotFoundException(String token) {
        super(ErrorCode.RESOURCE_NOT_FOUND, "Invitation with token " + token + " not found");
    }

}
