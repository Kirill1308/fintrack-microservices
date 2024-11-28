package com.popov.service;

import com.popov.EmailType;
import com.popov.kafka.event.ForgotPasswordEvent;
import com.popov.kafka.event.InvitationEvent;
import com.popov.kafka.event.PasswordResetSuccessEvent;
import com.popov.kafka.event.SubscriptionCancelEvent;
import com.popov.kafka.event.SubscriptionReminderEvent;
import com.popov.kafka.event.SubscriptionRenewalFailedEvent;
import com.popov.kafka.event.SubscriptionRenewalSuccessEvent;
import com.popov.kafka.event.SubscriptionTierDegradationEvent;
import com.popov.kafka.event.SubscriptionUpgradeConfirmEvent;
import com.popov.kafka.event.UserRegisteredEvent;
import com.popov.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailService emailService;

    @Value("${app.web.base-url}")
    private String baseUrl;

    @KafkaListener(topics = "events.collaborator.invitation")
    public void invitationToCollaborate(InvitationEvent event) {
        Properties mailProperties = new Properties();
        mailProperties.put("senderEmail", event.getSenderEmail());
        mailProperties.put("acceptLink", baseUrl + "/collaborators/accept?token=" + event.getToken());

        emailService.sendEmail(event.getReceiverEmail(), EmailType.COLLABORATOR_INVITATION, mailProperties);
    }

    @KafkaListener(topics = "events.authentication.user-registered")
    public void userRegistration(UserRegisteredEvent event) {
        Properties mailProperties = new Properties();
        mailProperties.put("name", event.getName());

        emailService.sendEmail(event.getEmail(), EmailType.REGISTRATION, mailProperties);
    }

    @KafkaListener(topics = "events.password.forgot-password")
    public void forgotPassword(ForgotPasswordEvent event) {
        String email = event.getEmail();
        String token = event.getToken();

        Properties mailProperties = new Properties();
        mailProperties.put("resetLink", baseUrl + "/reset/" + token);

        emailService.sendEmail(email, EmailType.PASSWORD_RESET_INSTRUCTIONS, mailProperties);
    }

    @KafkaListener(topics = "events.password.reset-success")
    public void handlePasswordChangeNotification(PasswordResetSuccessEvent event) {
        String email = event.getEmail();
        LocalDateTime time = event.getTimestamp();

        Properties mailProperties = new Properties();
        mailProperties.put("time", time.toString());
        mailProperties.put("loginLink", baseUrl + "/login");

        emailService.sendEmail(email, EmailType.PASSWORD_RESET_CONFIRMATION, mailProperties);
    }

    @KafkaListener(topics = "events.subscription.reminder")
    public void handleSubscriptionReminder(SubscriptionReminderEvent event) {
        String email = event.getEmail();

        Properties mailProperties = new Properties();
        mailProperties.put("name", event.getName());
        mailProperties.put("currentTier", event.getCurrentTier());
        mailProperties.put("currentPlan", event.getCurrentPlan());
        mailProperties.put("renewalDate", event.getEndDate().toString());

        emailService.sendEmail(email, EmailType.SUBSCRIPTION_REMINDER, mailProperties);
    }

    @KafkaListener(topics = "events.subscription.renewal-success")
    public void handleSubscriptionRenewalSuccess(SubscriptionRenewalSuccessEvent event) {
        String email = event.getEmail();

        Properties mailProperties = new Properties();
        mailProperties.put("name", event.getName());
        mailProperties.put("tier", event.getTier());
        mailProperties.put("plan", event.getPlan());
        mailProperties.put("startDate", event.getStartDate().toString());
        mailProperties.put("endDate", event.getEndDate().toString());

        emailService.sendEmail(email, EmailType.SUBSCRIPTION_RENEWAL_SUCCESS, mailProperties);
    }

    @KafkaListener(topics = "events.subscription.renewal-failed")
    public void handleSubscriptionRenewalFailed(SubscriptionRenewalFailedEvent event) {
        String email = event.getEmail();

        Properties mailProperties = new Properties();
        mailProperties.put("name", event.getName());
        mailProperties.put("tier", event.getTier());
        mailProperties.put("plan", event.getPlan());

        emailService.sendEmail(email, EmailType.SUBSCRIPTION_RENEWAL_FAILED, mailProperties);
    }

    @KafkaListener(topics = "events.subscription.upgrade-confirm")
    public void handleSubscriptionUpgradeConfirm(SubscriptionUpgradeConfirmEvent event) {
        String email = event.getEmail();

        Properties mailProperties = new Properties();
        mailProperties.put("name", event.getName());
        mailProperties.put("tier", event.getTier());
        mailProperties.put("plan", event.getPlan());
        mailProperties.put("startDate", event.getStartDate().toString());
        mailProperties.put("endDate", event.getEndDate().toString());
        mailProperties.put("autoRenew", event.isAutoRenew());
        mailProperties.put("price", event.getPrice());

        emailService.sendEmail(email, EmailType.SUBSCRIPTION_UPGRADE_CONFIRM, mailProperties);
    }

    @KafkaListener(topics = "events.subscription.tier-degradation")
    public void handleTierDegradation(SubscriptionTierDegradationEvent event) {
        String email = event.getEmail();

        Properties mailProperties = new Properties();
        mailProperties.put("name", event.getName());
        mailProperties.put("degradedTier", event.getDegradedTier());

        emailService.sendEmail(email, EmailType.SUBSCRIPTION_TIER_DEGRADATION, mailProperties);
    }

    @KafkaListener(topics = "events.subscription.cancel")
    public void handleSubscriptionCancel(SubscriptionCancelEvent event) {
        String email = event.getEmail();

        Properties mailProperties = new Properties();
        mailProperties.put("name", event.getName());
        mailProperties.put("tier", event.getTier());
        mailProperties.put("plan", event.getPlan());
        mailProperties.put("cancelDate", event.getCancelDate().toString());

        emailService.sendEmail(email, EmailType.SUBSCRIPTION_CANCEL_CONFIRMATION, mailProperties);
    }

}
