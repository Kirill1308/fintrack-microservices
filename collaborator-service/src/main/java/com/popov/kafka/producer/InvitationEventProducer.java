package com.popov.kafka.producer;

import com.popov.kafka.event.InvitationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class InvitationEventProducer {

    private final KafkaTemplate<String, InvitationEvent> invitationEventTemplate;

    public void send(InvitationEvent event) {
        Message<InvitationEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(TOPIC, "events.collaborator.invitation")
                .build();

        invitationEventTemplate.send(message);

    }

}
