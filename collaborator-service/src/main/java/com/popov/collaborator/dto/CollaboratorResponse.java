package com.popov.collaborator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Response object of a collaborator in a wallet")
public class CollaboratorResponse {

    @Schema(description = "The unique identifier of the collaborator", example = "1")
    private Long id;

    @Schema(description = "The email of the collaborator", example = "collaborator@example.com")
    private String email;

    @Schema(description = "The wallet ID that the collaborator has access to", example = "12345")
    private Long walletId;

    @Schema(description = "The role of the collaborator in the wallet", example = "ADMIN")
    private String role;

}
