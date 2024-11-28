package com.popov.kafka.producer;

import com.popov.kafka.event.TransactionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class TransactionEventProducer {

    private final KafkaTemplate<String, TransactionEvent> transactionEventTemplate;

    public void send(TransactionEvent event) {
        Message<TransactionEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(TOPIC, "transaction-activity")
                .build();

        transactionEventTemplate.send(message);
    }

}
