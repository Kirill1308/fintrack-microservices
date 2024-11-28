package com.popov.subscription.repository;

import com.popov.subscription.constant.SubscriptionStatus;
import com.popov.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findByUserId(Long userId);

    List<Subscription> findByEndDateAndStatus(LocalDate endDate, SubscriptionStatus status);

    List<Subscription> findByEndDateBeforeAndStatus(LocalDate expirationDate, SubscriptionStatus status);

    List<Subscription> findByEndDateAndStatusAndAutoRenew(LocalDate tomorrow, SubscriptionStatus status, boolean autoRenew);

    List<Subscription> findByStatusAndGracePeriodEndBefore(SubscriptionStatus status, LocalDate date);

    boolean existsByUserId(Long userId);
}
