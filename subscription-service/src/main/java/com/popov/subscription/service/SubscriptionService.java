package com.popov.subscription.service;

import com.popov.exception.custom.InvalidUpgradeException;
import com.popov.exception.custom.PaymentFailedException;
import com.popov.exception.custom.SubscriptionExistsException;
import com.popov.exception.custom.SubscriptionNotFoundException;
import com.popov.kafka.event.SubscriptionCancelEvent;
import com.popov.kafka.event.SubscriptionUpgradeConfirmEvent;
import com.popov.kafka.producer.SubscriptionEventProducer;
import com.popov.subscription.client.UserClient;
import com.popov.subscription.client.UserResponse;
import com.popov.subscription.constant.SubscriptionPlan;
import com.popov.subscription.constant.SubscriptionStatus;
import com.popov.subscription.constant.SubscriptionTier;
import com.popov.subscription.entity.Subscription;
import com.popov.subscription.repository.SubscriptionRepository;
import com.popov.subscription.service.billing.DevBillingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.popov.subscription.utils.SubscriptionUtils.calculateNewEndDate;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final DevBillingServiceImpl billingService;
    private final SubscriptionRepository subscriptionRepository;
    private final UserClient userClient;
    private final SubscriptionEventProducer eventPublisher;

    @Transactional(readOnly = true)
    public Subscription getSubscriptionByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId)
                .orElseThrow(() -> new SubscriptionNotFoundException(userId));
    }

    @Transactional
    public void createBasicSubscription(Long userId) {
        if (subscriptionRepository.existsByUserId(userId)) {
            throw new SubscriptionExistsException();
        }

        Subscription subscription = new Subscription();
        subscription.setUserId(userId);
        subscription.setTier(SubscriptionTier.BASIC);
        subscription.setPlan(SubscriptionPlan.ANNUAL);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusYears(100));
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        subscription.setAutoRenew(true);
        subscription.setCurrentPrice(0.0);

        subscriptionRepository.save(subscription);
    }

    @Transactional
    public void upgradeTier(Long userId, SubscriptionTier newTier, SubscriptionPlan newPlan) {
        Subscription subscription = subscriptionRepository.findByUserId(userId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription for user " + userId + " not found"));

        if (newTier.ordinal() <= subscription.getTier().ordinal()) {
            throw new InvalidUpgradeException("Can only upgrade to a higher tier");
        }

        boolean paymentSuccess = billingService.processPayment(userId, newTier.getPrice());
        if (!paymentSuccess) {
            throw new PaymentFailedException("Unable to process payment for upgrade");
        }

        subscription.setTier(newTier);
        subscription.setEndDate(calculateNewEndDate(subscription, newPlan));
        subscription.setCurrentPrice(newTier.getPrice());
        subscriptionRepository.save(subscription);

        UserResponse user = userClient.getUserById(userId);

        var event = SubscriptionUpgradeConfirmEvent.builder()
                .email(user.getEmail())
                .name(user.getName())
                .tier(newTier.name())
                .plan(newPlan.name())
                .price(newTier.getPrice())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .autoRenew(subscription.isAutoRenew())
                .build();

        eventPublisher.send(event);
    }

    @Transactional
    public void cancelSubscription(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException(subscriptionId));

        subscription.setTier(SubscriptionTier.BASIC);
        subscription.setStatus(SubscriptionStatus.CANCELLED);
        subscriptionRepository.save(subscription);

        UserResponse user = userClient.getUserById(subscription.getUserId());

        SubscriptionCancelEvent event = SubscriptionCancelEvent.builder()
                .email(user.getEmail())
                .name(user.getName())
                .tier(subscription.getTier().name())
                .plan(subscription.getPlan().name())
                .cancelDate(LocalDateTime.now())
                .build();

        eventPublisher.send(event);
    }

}
