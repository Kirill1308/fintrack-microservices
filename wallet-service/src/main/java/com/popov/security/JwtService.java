package com.popov.security;

import com.popov.exception.custom.JwtAuthenticationException;
import com.popov.security.context.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;
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
        if (bearerToken != null && isValid(bearerToken)) {
            return bearerToken.substring(7);
        }
        throw new JwtAuthenticationException("Invalid token format");
    }

    private boolean isValid(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        Jws<Claims> claims = Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
        return claims.getPayload()
                .getExpiration()
                .after(new Date());
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
