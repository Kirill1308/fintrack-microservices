package com.popov.exception.handler;

import com.popov.exception.ApiError;
import com.popov.exception.BaseException;
import com.popov.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError> handleBaseException(BaseException ex, HttpServletRequest request) {
        ApiError apiError = ApiError.builder()
                .path(request.getRequestURI())
                .message(ex.getMessage())
                .statusCode(ex.getErrorCode().getStatus().value())
                .statusName(ex.getErrorCode().getStatus().name())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(ex.getErrorCode().getStatus()).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllUncaughtException(
            HttpServletRequest request
    ) {
        ApiError apiError = ApiError.builder()
                .path(request.getRequestURI())
                .message("An unexpected error occurred")
                .statusCode(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
                .statusName(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().name())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.internalServerError().body(apiError);
    }

}
