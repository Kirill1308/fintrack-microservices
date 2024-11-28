package com.popov.user.mapper;

import com.popov.user.dto.UserRequest;
import com.popov.user.dto.UserResponse;
import com.popov.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest userRequest);

    UserResponse toResponse(User user);

}
