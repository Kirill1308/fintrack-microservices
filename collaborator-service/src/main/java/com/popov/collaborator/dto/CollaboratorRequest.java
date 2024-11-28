package com.popov.collaborator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request object to add or update a collaborator")
public class CollaboratorRequest {

    @NotNull(message = "Email is required")
    @Email(message = "Email must be valid")
    @Schema(description = "The email of the collaborator", example = "collaborator@example.com")
    private String email;

    @NotNull(message = "Wallet id is required")
    @Positive(message = "Wallet id must be positive")
    @Schema(description = "The wallet ID to which the collaborator will be added", example = "1")
    private Long walletId;

    @NotNull(message = "Role is required")
    @Schema(description = "The role of the collaborator in the wallet", example = "ADMIN")
    private String role;

}
