package com.popov.user.controller;

import com.popov.user.dto.UserRequest;
import com.popov.user.dto.UserResponse;
import com.popov.user.entity.User;
import com.popov.user.mapper.UserMapper;
import com.popov.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User API", description = "Endpoints for managing users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    @Operation(summary = "Get current user details", description = "Fetch details of the currently authenticated user")
    public UserResponse getCurrentUser() {
        User user = userService.getCurrentUser();
        return userMapper.toResponse(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Fetch user details by their ID")
    @Parameter(name = "id", description = "ID of the user to retrieve")
    public UserResponse getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return userMapper.toResponse(user);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get user by email", description = "Fetch user details by their email")
    @Parameter(name = "email", description = "Email of the user to retrieve")
    public UserResponse getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return userMapper.toResponse(user);
    }

    @GetMapping("/exists/email/{email}")
    @Operation(summary = "Check if email exists", description = "Verify whether a user with the given email exists")
    @Parameter(name = "email", description = "Email to check existence")
    @ApiResponse(responseCode = "200", description = "Boolean indicating if the email exists")
    public boolean isEmailExists(@PathVariable String email) {
        return userService.isEmailExists(email);
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user with the provided details")
    public UserResponse createUser(@RequestBody @Validated UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        User createdUser = userService.createUser(user);
        return userMapper.toResponse(createdUser);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Update the details of a user by their ID")
    @Parameter(name = "id", description = "ID of the user to update")
    @PreAuthorize("@accessControl.hasAccess(#id)")
    public UserResponse updateUser(@PathVariable Long id,
                                   @RequestBody @Validated UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        User updatedUser = userService.updateUser(id, user);
        return userMapper.toResponse(updatedUser);
    }

    @PostMapping("/{id}/avatar")
    @Operation(summary = "Upload user avatar", description = "Upload an avatar for the user")
    @Parameter(name = "id", description = "ID of the user to upload avatar for")
    @PreAuthorize("@accessControl.hasAccess(#id)")
    public UserResponse uploadAvatar(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        User updatedUser = userService.uploadAvatar(id, file);
        return userMapper.toResponse(updatedUser);
    }

    @DeleteMapping("/{id}/avatar")
    @Operation(summary = "Delete user avatar", description = "Delete the avatar of the user")
    @Parameter(name = "id", description = "ID of the user to delete avatar for")
    @PreAuthorize("@accessControl.hasAccess(#id)")
    public void deleteAvatar(@PathVariable Long id) {
        userService.deleteAvatar(id);
    }

}
