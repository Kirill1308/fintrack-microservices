package com.popov.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Response object for a successful login request")
public class LoginResponse {

    @Schema(description = "The user's unique ID", example = "12345")
    private Long id;

    @Schema(description = "The user's email address", example = "basicuser@gmail.com")
    private String email;

    @Schema(description = "The JWT access token for authentication", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;

    @Schema(description = "The JWT refresh token for renewing the access token", example = "dGhpcyBpcyBhIHNhbXBsZSByZWZyZXNoIHRva2Vu...")
    private String refreshToken;

}
