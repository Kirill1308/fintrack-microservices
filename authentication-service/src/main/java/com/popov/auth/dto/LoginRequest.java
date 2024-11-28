package com.popov.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "The request to log in a user")
public class LoginRequest {

    @NotNull(message = "Username cannot be null")
    @Email(message = "Email should be valid")
    @Schema(description = "The user's email address", example = "basicuser@gmail.com")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Schema(description = "The user's password", example = "12345")
    private String password;

}
