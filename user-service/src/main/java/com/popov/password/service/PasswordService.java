package com.popov.password.service;

import com.popov.exception.custom.PasswordResetException;
import com.popov.exception.custom.UserNotFoundException;
import com.popov.kafka.event.ForgotPasswordEvent;
import com.popov.kafka.event.PasswordResetSuccessEvent;
import com.popov.kafka.producer.PasswordEventProducer;
import com.popov.password.PasswordRepository;
import com.popov.password.PasswordResetToken;
import com.popov.password.dto.ForgotPasswordResponse;
import com.popov.password.dto.ResetPasswordRequest;
import com.popov.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final UserService userService;
    private final PasswordRepository passwordRepository;

    private final PasswordEventProducer passwordEventProducer;

    @Transactional
    public ForgotPasswordResponse handleForgotPassword(String email) {
        if (!userService.isEmailExists(email)) {
            throw new UserNotFoundException("email", email);
        }

        if (passwordRepository.existsByEmail(email)) {
            passwordRepository.deleteByEmail(email);
        }

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmail(email);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1));

        passwordRepository.save(resetToken);

        var event = new ForgotPasswordEvent(email, token);
        passwordEventProducer.send(event);

        return new ForgotPasswordResponse(token);
    }

    @Transactional
    public void handleResetPassword(String token, ResetPasswordRequest request) {
        PasswordResetToken resetToken = passwordRepository.findByToken(token)
                .orElseThrow(() -> new PasswordResetException("token", token));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new PasswordResetException("Token has expired");
        }

        request.setNewPassword(BCrypt.hashpw(request.getNewPassword(), BCrypt.gensalt()));

        userService.updatePassword(resetToken.getEmail(), request.getNewPassword());
        passwordRepository.delete(resetToken);

        var event = new PasswordResetSuccessEvent(resetToken.getEmail(), LocalDateTime.now());
        passwordEventProducer.send(event);
    }

}
