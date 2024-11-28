package com.popov.user.service;

import com.popov.exception.custom.UserCreationException;
import com.popov.exception.custom.UserNotFoundException;
import com.popov.security.context.UserContext;
import com.popov.security.context.UserContextHolder;
import com.popov.user.client.subscription.SubscriptionClient;
import com.popov.user.entity.User;
import com.popov.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService {

    private final FileService fileService;
    private final UserRepository userRepository;
    private final SubscriptionClient subscriptionClient;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id", id));
    }

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email", email));
    }

    @Transactional(readOnly = true)
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        UserContext userContext = UserContextHolder.getUserContext();
        return userRepository.findById(userContext.getUserId())
                .orElseThrow(() -> new UserNotFoundException("id", userContext.getUserId()));
    }

    @Transactional
    public User createUser(User user) {
        user.setRole("USER");
        User savedUser = userRepository.save(user);

        try {
            subscriptionClient.createBasicSubscription(savedUser.getId());
        } catch (Exception e) {
            userRepository.delete(savedUser);
            throw new UserCreationException();
        }

        return savedUser;
    }

    @Transactional
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id", id));

        existingUser.setName(user.getName());
        existingUser.setAvatar(user.getAvatar());

        return userRepository.save(existingUser);
    }

    @Transactional
    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email", email));

        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Transactional
    public User uploadAvatar(Long id, MultipartFile file) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id", id));

        String fileName = fileService.upload(file);
        user.setAvatar(fileName);

        return userRepository.save(user);
    }

    @Transactional
    public void deleteAvatar(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id", id));

        if (user.getAvatar() != null) {
            fileService.delete(user.getAvatar());
            user.setAvatar(null);
            userRepository.save(user);
        }
    }

}
