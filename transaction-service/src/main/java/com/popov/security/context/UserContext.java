package com.popov.security.context;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserContext {
    private Long userId;
    private String username;
    private String role;

    public boolean isAdmin() {
        return "ADMIN".equals(role);
    }

}
