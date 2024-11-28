package com.popov.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvitationEvent implements Serializable {

    private String senderEmail;
    private String receiverEmail;
    private String token;
    private String role;

}
