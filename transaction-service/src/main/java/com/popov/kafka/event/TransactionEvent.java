package com.popov.kafka.event;

import com.popov.transaction.constant.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEvent implements Serializable {

    private Long walletId;
    private Double amount;
    private Double previousAmount;
    private TransactionType transactionType;
    private LocalDate date;
    private TransactionEventType eventType;

}
