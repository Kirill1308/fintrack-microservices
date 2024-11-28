package com.popov.security.expression;

import com.popov.security.context.UserContext;
import com.popov.security.context.UserContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("accessControl")
@RequiredArgsConstructor
public class AccessControl {

    public boolean hasAccess(Long id) {
        UserContext userContext = UserContextHolder.getUserContext();

        return userContext.getUserId().equals(id);
    }

}
