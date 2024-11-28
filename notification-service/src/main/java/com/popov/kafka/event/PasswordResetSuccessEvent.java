package com.popov.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetSuccessEvent implements Serializable {

    private String email;
    private LocalDateTime timestamp;

}
