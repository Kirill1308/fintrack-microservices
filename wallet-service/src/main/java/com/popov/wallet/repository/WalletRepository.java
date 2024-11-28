package com.popov.wallet.repository;

import com.popov.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("SELECT w FROM Wallet w WHERE w.ownerId = :userId")
    List<Wallet> findOwnedWallets(Long userId);

    boolean existsByIdAndOwnerId(Long walletId, Long userId);

    Integer countByOwnerId(Long ownerId);

}
