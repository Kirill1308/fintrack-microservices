package com.popov.auth.client.user;

import com.popov.auth.dto.UserRequest;
import com.popov.auth.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "user-service",
        url = "${application.config.user-url}"
)
public interface UserClient {

    @GetMapping("/email/{email}")
    UserResponse getUserByEmail(@PathVariable String email);

    @PostMapping
    UserResponse createUser(@RequestBody UserRequest request);

    @GetMapping("/exists/email/{email}")
    boolean isEmailExists(@PathVariable String email);

}
