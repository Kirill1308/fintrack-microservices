package com.popov.collaborator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request object for inviting a collaborator to a wallet")
public class InvitationRequest {

    @Schema(description = "The wallet ID to which the collaborator is being invited", example = "12345")
    @NotNull(message = "Wallet id is required")
    @Positive(message = "Wallet id should be positive")
    private Long walletId;

    @Schema(description = "The email of the collaborator being invited", example = "collaborator@example.com")
    @NotNull(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @Schema(description = "The role assigned to the collaborator", example = "USER")
    @NotNull(message = "Role id is required")
    private String role;

}
