package com.popov.kafka.producer;

import com.popov.kafka.event.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class UserEventProducer {

    private final KafkaTemplate<String, UserRegisteredEvent> userRegisteredEventTemplate;

    public void send(UserRegisteredEvent event) {
        Message<UserRegisteredEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(TOPIC, "events.authentication.user-registered")
                .build();

        userRegisteredEventTemplate.send(message);
    }

}
