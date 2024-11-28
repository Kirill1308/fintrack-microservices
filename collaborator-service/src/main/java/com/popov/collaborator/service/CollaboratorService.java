package com.popov.collaborator.service;

import com.popov.collaborator.dto.CollaboratorRequest;
import com.popov.collaborator.entity.Collaborator;
import com.popov.exception.custom.CollaboratorNotFoundException;
import com.popov.kafka.event.WalletDeletedEvent;
import com.popov.collaborator.repository.CollaboratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollaboratorService {

    private final CollaboratorRepository collaboratorRepository;

    @Transactional(readOnly = true)
    public List<Collaborator> getWalletCollaborators(Long walletId) {
        return collaboratorRepository.findAllByWalletId(walletId);
    }

    @Transactional(readOnly = true)
    public Collaborator getCollaborator(Long walletId, String email) {
        return collaboratorRepository.findByWalletIdAndEmail(walletId, email)
                .orElseThrow(() -> new CollaboratorNotFoundException(email));
    }

    @Transactional
    public void addCollaborator(CollaboratorRequest collaboratorRequest) {
        Collaborator collaborator = new Collaborator();
        collaborator.setEmail(collaboratorRequest.getEmail());
        collaborator.setWalletId(collaboratorRequest.getWalletId());
        collaborator.setRole(collaboratorRequest.getRole());

        collaboratorRepository.save(collaborator);
    }

    @Transactional
    public Collaborator updateCollaboratorRole(Long collaboratorId, Collaborator collaborator) {
        Collaborator existingCollaborator = collaboratorRepository.findById(collaboratorId)
                .orElseThrow(() -> new CollaboratorNotFoundException(collaboratorId));

        existingCollaborator.setRole(collaborator.getRole());

        return collaboratorRepository.save(existingCollaborator);
    }

    @Transactional
    public void removeCollaborator(Long walletId, String email) {
        collaboratorRepository.deleteByWalletIdAndEmail(walletId, email);
    }

    @Transactional
    @KafkaListener(topics = "wallet-deleted", groupId = "collaborator-service")
    public void removeAllCollaborators(WalletDeletedEvent event) {
        Long walletId = event.getWalletId();
        collaboratorRepository.deleteAllByWalletId(walletId);
    }

    @Transactional(readOnly = true)
    public boolean isCollaborator(Long walletId, String email, String role) {
        if (role == null) {
            return collaboratorRepository.existsByWalletIdAndEmail(walletId, email);
        }

        return collaboratorRepository.existsByWalletIdAndEmailAndRole(walletId, email, role);
    }

    @Transactional(readOnly = true)
    public List<Long> getSharedWallets(String email) {
        return collaboratorRepository.findWalletsByEmail(email);
    }

}
