package com.popov.auth.controller;

import com.popov.auth.dto.LoginRequest;
import com.popov.auth.dto.LoginResponse;
import com.popov.auth.dto.UserRequest;
import com.popov.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Log in a user", description = "Authenticate a user")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @Operation(summary = "Register a new user", description = "Create a new user account")
    @PostMapping("/register")
    public void register(@RequestBody @Validated UserRequest request) {
        authService.register(request);
    }

}
