package com.popov.password.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ForgotPasswordResponse {

    private String acceptToken;

}
