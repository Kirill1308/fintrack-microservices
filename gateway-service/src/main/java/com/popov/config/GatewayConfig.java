package com.popov.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/users/**", "/password/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://user-service"))
                .route("subscription-service", r -> r.path("/subscriptions/**", "/subscription-tiers/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://subscription-service"))
                .route("authentication-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://authentication-service"))
                .route("wallet-service", r -> r.path("/wallets/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://wallet-service"))
                .route("transaction-service", r -> r.path("/transactions/**", "/categories/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://transaction-service"))
                .route("collaborator-service", r -> r.path("/collaborators/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://collaborator-service"))
                .route("analytics-service", r -> r.path("/analytics/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://analytics-service"))
                .build();
    }

}
