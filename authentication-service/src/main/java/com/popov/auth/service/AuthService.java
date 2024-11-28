package com.popov.auth.service;

import com.popov.auth.client.user.UserClient;
import com.popov.auth.dto.LoginRequest;
import com.popov.auth.dto.LoginResponse;
import com.popov.auth.dto.UserRequest;
import com.popov.auth.dto.UserResponse;
import com.popov.exception.custom.AuthenticationException;
import com.popov.exception.custom.UserCreationException;
import com.popov.kafka.event.UserRegisteredEvent;
import com.popov.kafka.producer.UserEventProducer;
import com.popov.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserClient userClient;
    private final UserEventProducer userEventProducer;

    public LoginResponse login(LoginRequest loginRequest) {
        UserResponse userResponse = userClient.getUserByEmail(loginRequest.getEmail());

        if (!BCrypt.checkpw(loginRequest.getPassword(), userResponse.getPassword())) {
            throw new AuthenticationException("Invalid email or password");
        }

        return createLoginResponse(userResponse);
    }

    public void register(UserRequest request) {
        if (userClient.isEmailExists(request.getEmail())) {
            throw new AuthenticationException("User with this email already exists");
        }

        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));

        try {
            userClient.createUser(request);
        } catch (Exception e) {
            throw new UserCreationException("Failed to create user");
        }

        UserRegisteredEvent event = UserRegisteredEvent.builder()
                .email(request.getEmail())
                .name(request.getName())
                .build();

        userEventProducer.send(event);
    }

    private LoginResponse createLoginResponse(UserResponse user) {
        String accessToken = jwtTokenProvider.createAccessToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        String refreshToken = jwtTokenProvider.createRefreshToken(
                user.getId(),
                user.getEmail()
        );

        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                accessToken,
                refreshToken
        );
    }

}
