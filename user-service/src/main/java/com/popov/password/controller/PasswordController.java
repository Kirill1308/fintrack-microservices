package com.popov.password.controller;

import com.popov.password.dto.ForgotPasswordRequest;
import com.popov.password.dto.ForgotPasswordResponse;
import com.popov.password.dto.ResetPasswordRequest;
import com.popov.password.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
public class PasswordController {

    private final PasswordService passwordService;

    @PostMapping("/forgot")
    public ForgotPasswordResponse forgotPassword(
            @RequestBody @Validated ForgotPasswordRequest request) {
        return passwordService.handleForgotPassword(request.getEmail());
    }

    @PostMapping("/reset/{token}")
    public void resetPassword(
            @PathVariable String token,
            @RequestBody @Validated ResetPasswordRequest request) {
        passwordService.handleResetPassword(token, request);
    }

}
