package com.popov.kafka.producer;

import com.popov.kafka.event.WalletDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class WalletEventProducer {

    private final KafkaTemplate<String, WalletDeletedEvent> walletDeletedEventTemplate;

    public void send(WalletDeletedEvent event) {
        Message<WalletDeletedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(TOPIC, "wallet-deleted")
                .build();

        walletDeletedEventTemplate.send(message);
    }

}
