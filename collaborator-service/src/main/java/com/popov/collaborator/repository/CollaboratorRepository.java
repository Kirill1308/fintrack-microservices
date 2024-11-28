package com.popov.collaborator.repository;

import com.popov.collaborator.entity.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {

    List<Collaborator> findAllByWalletId(Long walletId);

    Optional<Collaborator> findByWalletIdAndEmail(Long walletId, String email);

    void deleteByWalletIdAndEmail(Long walletId, String email);

    void deleteAllByWalletId(Long walletId);

    boolean existsByWalletIdAndEmail(Long walletId, String email);

    boolean existsByWalletIdAndEmailAndRole(Long walletId, String email, String role);

    @Query("SELECT c.walletId FROM Collaborator c WHERE c.email = :email")
    List<Long> findWalletsByEmail(String email);

}
