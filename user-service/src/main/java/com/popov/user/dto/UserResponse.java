package com.popov.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "User response object containing user details")
public class UserResponse {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @Schema(description = "Email address of the user", example = "user@example.com")
    private String email;

    @Schema(description = "Password of the user (hashed)", example = "$2a$10$7eqJtq98hPqEXAMPLE")
    private String password;

    @Schema(description = "Role assigned to the user", example = "USER")
    private String role;

    @Schema(description = "Full name of the user", example = "John Doe")
    private String name;

    @Schema(description = "URL or path to the user's avatar image", example = "https://example.com/avatar.jpg")
    private String avatar;

}
