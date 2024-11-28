package com.popov.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {

    private static final List<String> OPEN_ENDPOINTS = List.of(
            "/auth/login",
            "/auth/register",
            "/collaborators/accept",
            "/password/forgot",
            "/password/reset",
            "/swagger-ui.html/**",
            "/v3/api-docs/**"
    );

    private static final Predicate<ServerHttpRequest> IS_SECURED =
            request -> OPEN_ENDPOINTS.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

    public boolean isSecuredRequest(ServerHttpRequest request) {
        return IS_SECURED.test(request);
    }

}
