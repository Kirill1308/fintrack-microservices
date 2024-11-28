package com.popov.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request object for user registration")
public class UserRequest {

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    @Schema(description = "The user's email address", example = "user@example.com")
    private String email;

    @NotNull(message = "Password is required")
    @Schema(description = "The user's password", example = "securePassword123")
    private String password;

    @NotNull(message = "Name is required")
    @Size(min = 2, message = "Name should have at least 2 characters")
    @Schema(description = "The user's name", example = "John Doe")
    private String name;

}
