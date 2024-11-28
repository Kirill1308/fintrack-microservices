package com.popov.subscription.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String email;
    private String password;
    private String role;
    private String name;
    private String avatar;

}
