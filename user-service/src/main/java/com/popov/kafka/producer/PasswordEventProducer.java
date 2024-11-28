package com.popov.kafka.producer;

import com.popov.kafka.event.ForgotPasswordEvent;
import com.popov.kafka.event.PasswordResetSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class PasswordEventProducer {

    private final KafkaTemplate<String, ForgotPasswordEvent> forgotPasswordKafkaTemplate;
    private final KafkaTemplate<String, PasswordResetSuccessEvent> resetSuccessKafkaTemplate;

    public void send(ForgotPasswordEvent event) {
        Message<ForgotPasswordEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(TOPIC, "events.password.forgot-password")
                .build();

        forgotPasswordKafkaTemplate.send(message);
    }

    public void send(PasswordResetSuccessEvent event) {
        Message<PasswordResetSuccessEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(TOPIC, "events.password.reset-success")
                .build();

        resetSuccessKafkaTemplate.send(message);
    }

}
