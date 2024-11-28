package com.popov.kafka.event;

import com.popov.analytics.constant.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionEvent implements Serializable {

    private Long walletId;
    private Double amount;
    private Double previousAmount;
    private TransactionType transactionType;
    private LocalDate date;
    private TransactionEventType eventType;

}
