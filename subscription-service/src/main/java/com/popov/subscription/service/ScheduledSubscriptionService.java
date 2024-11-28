package com.popov.subscription.service;

import com.popov.kafka.event.SubscriptionReminderEvent;
import com.popov.kafka.event.SubscriptionRenewalFailedEvent;
import com.popov.kafka.event.SubscriptionRenewalSuccessEvent;
import com.popov.kafka.event.SubscriptionTierDegradationEvent;
import com.popov.kafka.producer.SubscriptionEventProducer;
import com.popov.subscription.client.UserClient;
import com.popov.subscription.client.UserResponse;
import com.popov.subscription.constant.SubscriptionStatus;
import com.popov.subscription.constant.SubscriptionTier;
import com.popov.subscription.entity.Subscription;
import com.popov.subscription.repository.SubscriptionRepository;
import com.popov.subscription.service.billing.DevBillingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.popov.subscription.utils.SubscriptionUtils.calculateNewEndDate;

@Service
@RequiredArgsConstructor
public class ScheduledSubscriptionService {

    private final DevBillingServiceImpl billingService;
    private final SubscriptionRepository subscriptionRepository;

    private final UserClient userClient;
    private final SubscriptionEventProducer eventPublisher;

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void processAutoRenewals() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Subscription> renewingSubscriptions = subscriptionRepository
                .findByEndDateAndStatusAndAutoRenew(tomorrow, SubscriptionStatus.ACTIVE, true);

        for (Subscription subscription : renewingSubscriptions) {
            if (subscription.getTier() == SubscriptionTier.BASIC) {
                continue;
            }

            try {
                processRenewal(subscription);
            } catch (Exception e) {
                handleFailedRenewal(subscription);
            }
        }
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void checkGracePeriods() {
        List<Subscription> failedSubscriptions = subscriptionRepository
                .findByStatusAndGracePeriodEndBefore(
                        SubscriptionStatus.PAYMENT_FAILED,
                        LocalDate.now()
                );

        for (Subscription subscription : failedSubscriptions) {
            degradeToBasicTier(subscription);
        }
    }

    private void processRenewal(Subscription subscription) {
        boolean paymentSuccess = billingService.processPayment(
                subscription.getUserId(),
                subscription.getCurrentPrice()
        );

        if (paymentSuccess) {
            subscription.setEndDate(calculateNewEndDate(subscription));
            subscriptionRepository.save(subscription);

            UserResponse user = userClient.getUserById(subscription.getUserId());

            SubscriptionRenewalSuccessEvent event = SubscriptionRenewalSuccessEvent.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .tier(subscription.getTier().name())
                    .plan(subscription.getPlan().name())
                    .startDate(subscription.getStartDate())
                    .endDate(subscription.getEndDate())
                    .build();

            eventPublisher.send(event);
        } else {
            handleFailedRenewal(subscription);
        }
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void checkExpiringSubscriptions() {
        processUpcomingExpirations();
        processExpiredSubscriptions();
    }

    private void degradeToBasicTier(Subscription subscription) {
        subscription.setTier(SubscriptionTier.BASIC);
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        subscription.setCurrentPrice(0.0);
        subscription.setAutoRenew(false);
        subscriptionRepository.save(subscription);

        UserResponse user = userClient.getUserById(subscription.getUserId());

        SubscriptionTierDegradationEvent event = SubscriptionTierDegradationEvent.builder()
                .email(user.getEmail())
                .name(user.getName())
                .degradedTier(SubscriptionTier.BASIC.name())
                .build();

        eventPublisher.send(event);
    }

    private void handleFailedRenewal(Subscription subscription) {
        subscription.setStatus(SubscriptionStatus.PAYMENT_FAILED);
        subscription.setGracePeriodEnd(LocalDate.now().plusDays(30));
        subscriptionRepository.save(subscription);

        UserResponse user = userClient.getUserById(subscription.getUserId());

        SubscriptionRenewalFailedEvent event = SubscriptionRenewalFailedEvent.builder()
                .email(user.getEmail())
                .name(user.getName())
                .tier(subscription.getTier().name())
                .plan(subscription.getPlan().name())
                .build();

        eventPublisher.send(event);
    }

    private void processUpcomingExpirations() {
        LocalDate reminderDate = LocalDate.now().plusDays(7);
        List<Subscription> expiringSubscriptions = subscriptionRepository
                .findByEndDateAndStatus(reminderDate, SubscriptionStatus.ACTIVE);

        for (Subscription subscription : expiringSubscriptions) {
            UserResponse user = userClient.getUserById(subscription.getUserId());

            SubscriptionReminderEvent event = SubscriptionReminderEvent.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .currentTier(subscription.getTier().name())
                    .currentPlan(subscription.getPlan().name())
                    .endDate(subscription.getEndDate())
                    .build();

            eventPublisher.send(event);
        }
    }

    private void processExpiredSubscriptions() {
        List<Subscription> expiredSubscriptions = subscriptionRepository
                .findByEndDateBeforeAndStatus(LocalDate.now(), SubscriptionStatus.ACTIVE);

        for (Subscription subscription : expiredSubscriptions) {
            subscription.setStatus(SubscriptionStatus.EXPIRED);
            subscriptionRepository.save(subscription);
        }
    }

}
