package com.popov.security.context;

import com.popov.exception.custom.UserContextNotFoundException;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    public static void setUserContext(UserContext context) {
        userContext.set(context);
    }

    public static UserContext getUserContext() {
        UserContext context = userContext.get();
        if (context == null) {
            throw new UserContextNotFoundException();
        }
        return context;
    }

    public static void clear() {
        userContext.remove();
    }

}
