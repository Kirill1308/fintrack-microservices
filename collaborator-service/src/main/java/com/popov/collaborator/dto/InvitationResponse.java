package com.popov.collaborator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Response object for a collaborator invitation")
public class InvitationResponse {

    @Schema(description = "The unique identifier for the invitation", example = "12345")
    private Long id;

    @Schema(description = "The token associated with the invitation", example = "abc123tokenxyz")
    private String token;

}
