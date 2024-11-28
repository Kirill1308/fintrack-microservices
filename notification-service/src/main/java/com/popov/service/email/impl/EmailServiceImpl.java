package com.popov.service.email.impl;

import com.popov.EmailType;
import com.popov.exception.custom.EmailBodyGenerationException;
import com.popov.service.email.EmailService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    protected final Configuration configuration;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    @SneakyThrows
    public void sendEmail(String email, EmailType emailType, Properties mailProperties) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
        helper.setTo(email);
        helper.setFrom(fromEmail);

        String subject = getEmailSubject(emailType);
        String body = getEmailBody(emailType, mailProperties);

        helper.setSubject(subject);
        helper.setText(body, true);

        emailSender.send(message);
    }

    private String getEmailSubject(EmailType emailType) {
        return switch (emailType) {
            // Account-related
            case REGISTRATION -> "Welcome to our service!";
            case PASSWORD_RESET_INSTRUCTIONS -> "Password reset instructions";
            case PASSWORD_RESET_CONFIRMATION -> "Password reset confirmation";

            // Subscription-related
            case SUBSCRIPTION_REMINDER -> "Subscription reminder";
            case SUBSCRIPTION_RENEWAL_SUCCESS -> "Subscription renewal success";
            case SUBSCRIPTION_RENEWAL_FAILED -> "Subscription renewal failed";
            case SUBSCRIPTION_UPGRADE_CONFIRM -> "Subscription upgrade confirmation";
            case SUBSCRIPTION_TIER_DEGRADATION -> "Subscription tier degradation";
            case SUBSCRIPTION_CANCEL_CONFIRMATION -> "Subscription cancel confirmation";

            // Announcement-related
            case NEW_FEATURE_ANNOUNCEMENT -> "New feature announcement";

            // Collaboration-related
            case COLLABORATOR_INVITATION -> "Collaborator invitation";
        };
    }

    private String getEmailBody(EmailType emailType, Properties mailProperties) {
        String templateName = getTemplateName(emailType);
        Map<String, Object> model = new HashMap<>();

        mailProperties.forEach((key, value) -> model.put((String) key, value));

        try {
            StringWriter writer = new StringWriter();
            configuration.getTemplate(templateName).process(model, writer);
            return writer.getBuffer().toString();
        } catch (IOException | TemplateException e) {
            throw new EmailBodyGenerationException("Failed to generate email body", e);
        }
    }

    private String getTemplateName(EmailType emailType) {
        return switch (emailType) {
            // Account-related
            case REGISTRATION -> "account/registrationEmail.ftlh";
            case PASSWORD_RESET_INSTRUCTIONS -> "account/passwordResetInstructionsEmail.ftlh";
            case PASSWORD_RESET_CONFIRMATION -> "account/passwordResetConfirmationEmail.ftlh";

            // Subscription-related
            case SUBSCRIPTION_REMINDER -> "subscription/subscriptionReminderEmail.ftlh";
            case SUBSCRIPTION_RENEWAL_SUCCESS -> "subscription/subscriptionRenewalSuccessEmail.ftlh";
            case SUBSCRIPTION_RENEWAL_FAILED -> "subscription/subscriptionRenewalFailedEmail.ftlh";
            case SUBSCRIPTION_UPGRADE_CONFIRM -> "subscription/subscriptionUpgradeConfirmEmail.ftlh";
            case SUBSCRIPTION_TIER_DEGRADATION -> "subscription/subscriptionTierDegradationEmail.ftlh";
            case SUBSCRIPTION_CANCEL_CONFIRMATION -> "subscription/subscriptionCancelConfirmationEmail.ftlh";

            // Announcement-related
            case NEW_FEATURE_ANNOUNCEMENT -> "announcement/newFeatureAnnouncementEmail.ftlh";

            // Collaboration-related
            case COLLABORATOR_INVITATION -> "collaboration/collaboratorInvitationEmail.ftlh";
        };
    }

}
