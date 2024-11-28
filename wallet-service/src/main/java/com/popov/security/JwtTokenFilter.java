package com.popov.security;

import com.popov.security.context.UserContext;
import com.popov.security.context.UserContextHolder;
import com.popov.exception.custom.JwtAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String bearerToken = request.getHeader("Authorization");
            if (bearerToken != null) {
                String token = jwtService.extractToken(bearerToken);
                UserContext userContext = jwtService.createUserContext(token);
                UserContextHolder.setUserContext(userContext);
            }
            filterChain.doFilter(request, response);
        } catch (JwtAuthenticationException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        } finally {
            UserContextHolder.clear();
        }
    }

}
