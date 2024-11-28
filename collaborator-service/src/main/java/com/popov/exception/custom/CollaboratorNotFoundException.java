package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class CollaboratorNotFoundException extends BaseException {

    public CollaboratorNotFoundException(Long collaboratorId) {
        super(ErrorCode.RESOURCE_NOT_FOUND, "Collaborator with id " + collaboratorId + " not found");
    }

    public CollaboratorNotFoundException(String email) {
        super(ErrorCode.RESOURCE_NOT_FOUND, "Collaborator with email " + email + " not found");
    }

}
