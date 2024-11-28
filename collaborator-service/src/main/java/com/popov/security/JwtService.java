package com.popov.security;

import com.popov.exception.custom.JwtAuthenticationException;
import com.popov.security.context.UserContext;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;
    private final JwtTokenProvider jwtTokenProvider;
    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public UserContext createUserContext(String token) {
        UserContext userContext = new UserContext();
        userContext.setUserId(getId(token));
        userContext.setUsername(getUsername(token));
        userContext.setRole(getRole(token));
        return userContext;
    }

    public String extractToken(String bearerToken) {
        if (bearerToken != null && jwtTokenProvider.isValid(bearerToken)) {
            return bearerToken.substring(7);
        }
        throw new JwtAuthenticationException("Invalid token format");
    }

    private Long getId(final String token) {
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id", Long.class);
    }

    private String getUsername(final String token) {
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private String getRole(String token) {
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

}
