package com.popov.kafka.producer;

import com.popov.kafka.event.SubscriptionCancelEvent;
import com.popov.kafka.event.SubscriptionReminderEvent;
import com.popov.kafka.event.SubscriptionRenewalFailedEvent;
import com.popov.kafka.event.SubscriptionRenewalSuccessEvent;
import com.popov.kafka.event.SubscriptionUpgradeConfirmEvent;
import com.popov.kafka.event.SubscriptionTierDegradationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class SubscriptionEventProducer {

    private final KafkaTemplate<String, SubscriptionRenewalSuccessEvent> renewalSuccessEventTemplate;
    private final KafkaTemplate<String, SubscriptionRenewalFailedEvent> renewalFailedEventTemplate;
    private final KafkaTemplate<String, SubscriptionUpgradeConfirmEvent> upgradeConfirmEventTemplate;
    private final KafkaTemplate<String, SubscriptionReminderEvent> reminderEventTemplate;
    private final KafkaTemplate<String, SubscriptionTierDegradationEvent> tierDegradationEventTemplate;
    private final KafkaTemplate<String, SubscriptionCancelEvent> cancelEventTemplate;

    public void send(SubscriptionRenewalSuccessEvent event) {
        Message<SubscriptionRenewalSuccessEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(TOPIC, "events.subscription.renewal-success")
                .build();

        renewalSuccessEventTemplate.send(message);
    }

    public void send(SubscriptionRenewalFailedEvent event) {
        Message<SubscriptionRenewalFailedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(TOPIC, "events.subscription.renewal-failed")
                .build();

        renewalFailedEventTemplate.send(message);
    }

    public void send(SubscriptionUpgradeConfirmEvent event) {
        Message<SubscriptionUpgradeConfirmEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(TOPIC, "events.subscription.upgrade-confirm")
                .build();

        upgradeConfirmEventTemplate.send(message);
    }

    public void send(SubscriptionReminderEvent event) {
        Message<SubscriptionReminderEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(TOPIC, "events.subscription.reminder")
                .build();

        reminderEventTemplate.send(message);
    }

    public void send(SubscriptionTierDegradationEvent event) {
        Message<SubscriptionTierDegradationEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(TOPIC, "events.subscription.tier-degradation")
                .build();

        tierDegradationEventTemplate.send(message);
    }

    public void send(SubscriptionCancelEvent event) {
        Message<SubscriptionCancelEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(TOPIC, "events.subscription.cancel")
                .build();

        cancelEventTemplate.send(message);
    }

}
