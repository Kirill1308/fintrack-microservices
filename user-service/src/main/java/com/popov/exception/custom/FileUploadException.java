package com.popov.exception.custom;

import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;

public class FileUploadException extends BaseException {

    public FileUploadException(String message) {
        super(ErrorCode.BAD_REQUEST, message);
    }

}
