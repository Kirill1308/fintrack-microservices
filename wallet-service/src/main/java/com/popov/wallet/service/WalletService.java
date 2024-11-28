package com.popov.wallet.service;

import com.popov.exception.custom.WalletLimitReachedException;
import com.popov.exception.custom.WalletNotFoundException;
import com.popov.kafka.event.WalletDeletedEvent;
import com.popov.kafka.producer.WalletEventProducer;
import com.popov.security.context.UserContext;
import com.popov.security.context.UserContextHolder;
import com.popov.wallet.client.collaborator.CollaboratorClient;
import com.popov.wallet.client.subscription.SubscriptionTierClient;
import com.popov.wallet.entity.Wallet;
import com.popov.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final CollaboratorClient collaboratorClient;
    private final SubscriptionTierClient subscriptionTierClient;
    private final WalletEventProducer walletEventProducer;

    @Transactional(readOnly = true)
    public Wallet getWalletById(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException(walletId));
    }

    @Transactional(readOnly = true)
    public List<Wallet> getAllWallets() {
        UserContext userContext = UserContextHolder.getUserContext();

        List<Wallet> ownedWallets = walletRepository.findOwnedWallets(userContext.getUserId());
        List<Wallet> sharedWallets = getSharedWallets(userContext.getUsername());

        List<Wallet> wallets = new ArrayList<>();
        wallets.addAll(ownedWallets);
        wallets.addAll(sharedWallets);

        return wallets;
    }

    @Transactional
    public Wallet createWallet(Wallet wallet) {
        UserContext userContext = UserContextHolder.getUserContext();

        if (hasReachedMaxWallets(userContext.getUserId())) {
            throw new WalletLimitReachedException();
        }
        wallet.setOwnerId(userContext.getUserId());

        return walletRepository.save(wallet);
    }

    @Transactional
    public Wallet updateWallet(Long walletId, Wallet wallet) {
        Wallet existingWallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException(walletId));

        BeanUtils.copyProperties(wallet, existingWallet, "id");

        return walletRepository.save(existingWallet);
    }

    @Transactional
    public void deleteWallet(Long walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException(walletId));

        walletRepository.delete(wallet);

        WalletDeletedEvent event = new WalletDeletedEvent(walletId);
        walletEventProducer.send(event);

    }

    @Transactional(readOnly = true)
    public boolean isOwner(Long walletId, Long userId) {
        return walletRepository.existsByIdAndOwnerId(walletId, userId);
    }

    private List<Wallet> getSharedWallets(String email) {
        List<Long> walletIds = collaboratorClient.getSharedWallets(email);

        if (walletIds == null || walletIds.isEmpty()) {
            return new ArrayList<>();
        }

        return walletRepository.findAllById(walletIds);
    }

    private boolean hasReachedMaxWallets(Long userId) {
        UserContext userContext = UserContextHolder.getUserContext();

        Integer currentWalletCount = walletRepository.countByOwnerId(userId);
        if (currentWalletCount == 0) {
            return false;
        }

        var tier = subscriptionTierClient.getSubscriptionTier(userContext.getUserId());

        return tier != null && currentWalletCount >= tier.getMaxWallets();
    }

}
