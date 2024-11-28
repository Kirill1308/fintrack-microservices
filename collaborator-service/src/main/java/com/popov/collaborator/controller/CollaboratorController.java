package com.popov.collaborator.controller;

import com.popov.collaborator.dto.CollaboratorRequest;
import com.popov.collaborator.dto.CollaboratorResponse;
import com.popov.collaborator.dto.InvitationRequest;
import com.popov.collaborator.dto.InvitationResponse;
import com.popov.collaborator.entity.Collaborator;
import com.popov.collaborator.entity.Invitation;
import com.popov.collaborator.mapper.CollaboratorMapper;
import com.popov.collaborator.mapper.InvitationMapper;
import com.popov.collaborator.service.CollaboratorService;
import com.popov.collaborator.service.InvitationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collaborators")
@RequiredArgsConstructor
@Tag(name = "Collaborator API", description = "Endpoints for managing collaborators")
public class CollaboratorController {

    private final CollaboratorService collaboratorService;
    private final InvitationService invitationService;

    private final CollaboratorMapper collaboratorMapper;
    private final InvitationMapper invitationMapper;

    @Operation(summary = "Invite a collaborator to a wallet", description = "Send an invitation to a user to become a collaborator of a specific wallet.")
    @PostMapping("/invite")
    @PreAuthorize("@accessControl.hasPermissionToInviteCollaborator(#invitationRequest.walletId)")
    public InvitationResponse inviteCollaborator(@Validated @RequestBody InvitationRequest invitationRequest) {
        Invitation invitation = invitationMapper.toEntity(invitationRequest);
        Invitation createdInvitation = invitationService.inviteMember(invitation);
        return invitationMapper.toResponse(createdInvitation);
    }

    @Operation(summary = "Accept a collaborator invitation", description = "Accept an invitation to collaborate on a wallet using a token.")
    @PostMapping("/accept")
    public void acceptCollaborator(@RequestParam @Parameter(description = "The invitation token to accept") String token) {
        invitationService.acceptInvitation(token);
    }

    @Operation(summary = "Get all collaborators for a wallet", description = "Retrieve a list of all collaborators for a specific wallet.")
    @GetMapping("/all")
    @PreAuthorize("@accessControl.hasAccessToWallet(#walletId)")
    public List<CollaboratorResponse> getWalletCollaborators(@RequestParam Long walletId) {
        List<Collaborator> collaborators = collaboratorService.getWalletCollaborators(walletId);
        return collaboratorMapper.toResponse(collaborators);
    }

    @Operation(summary = "Get a specific collaborator by email", description = "Retrieve a specific collaborator from a wallet by their email address.")
    @GetMapping
    @PreAuthorize("@accessControl.hasAccessToWallet(#walletId)")
    public CollaboratorResponse getCollaborator(@RequestParam Long walletId, @RequestParam String email) {
        Collaborator collaborator = collaboratorService.getCollaborator(walletId, email);
        return collaboratorMapper.toResponse(collaborator);
    }

    @Operation(summary = "Update a collaborator's role", description = "Update the role of a collaborator in a wallet.")
    @PutMapping("/{id}")
    @PreAuthorize("@accessControl.hasAccessToUpdateCollaboratorRole(#collaboratorRequest.walletId)")
    public CollaboratorResponse updateCollaboratorRole(@PathVariable Long id,
                                                       @Validated @RequestBody CollaboratorRequest collaboratorRequest) {
        Collaborator collaborator = collaboratorMapper.toEntity(collaboratorRequest);
        Collaborator updatedCollaborator = collaboratorService.updateCollaboratorRole(id, collaborator);
        return collaboratorMapper.toResponse(updatedCollaborator);
    }

    @Operation(summary = "Remove a collaborator from a wallet", description = "Remove a collaborator from a wallet by their email address.")
    @DeleteMapping
    @PreAuthorize("@accessControl.hasAccessToWallet(#walletId)")
    public void removeCollaborator(@RequestParam Long walletId, @RequestParam String email) {
        collaboratorService.removeCollaborator(walletId, email);
    }

    @Operation(summary = "Check if a user is a collaborator", description = "Check if a user is a collaborator on a specific wallet with a given role.")
    @GetMapping("/is-collaborator")
    public boolean isCollaborator(@RequestParam Long walletId,
                                  @RequestParam String email,
                                  @RequestParam(required = false) String role) {
        return collaboratorService.isCollaborator(walletId, email, role);
    }

    @Operation(summary = "Get shared wallets of a user", description = "Retrieve a list of wallet IDs that a user is a collaborator in.")
    @GetMapping("/shared-wallets")
    public List<Long> getSharedWallets(@RequestParam String email) {
        return collaboratorService.getSharedWallets(email);
    }

}
