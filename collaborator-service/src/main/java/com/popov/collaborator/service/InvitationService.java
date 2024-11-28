package com.popov.collaborator.service;

import com.popov.collaborator.dto.CollaboratorRequest;
import com.popov.collaborator.entity.Invitation;
import com.popov.collaborator.repository.InvitationRepository;
import com.popov.exception.custom.InvitationNotFoundException;
import com.popov.kafka.event.InvitationEvent;
import com.popov.kafka.producer.InvitationEventProducer;
import com.popov.security.context.UserContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvitationService {

    private final CollaboratorService collaboratorService;
    private final InvitationRepository invitationRepository;

    private final InvitationEventProducer invitationEventProducer;

    public Invitation inviteMember(Invitation invitation) {
        String token = UUID.randomUUID().toString();
        invitation.setToken(token);

        invitationRepository.save(invitation);

        InvitationEvent event = InvitationEvent.builder()
                .senderEmail(UserContextHolder.getUserContext().getUsername())
                .receiverEmail(invitation.getEmail())
                .token(token)
                .role(invitation.getRole())
                .build();

        invitationEventProducer.send(event);

        return invitation;
    }

    public void acceptInvitation(String token) {
        Invitation invitation = invitationRepository.findByToken(token)
                .orElseThrow(() -> new InvitationNotFoundException(token));

        CollaboratorRequest collaborator = new CollaboratorRequest();
        collaborator.setEmail(invitation.getEmail());
        collaborator.setWalletId(invitation.getWalletId());
        collaborator.setRole(invitation.getRole());

        collaboratorService.addCollaborator(collaborator);

        invitationRepository.delete(invitation);
    }

}
