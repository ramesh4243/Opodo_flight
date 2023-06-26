package com.opodo.service.impl;

import com.opodo.payload.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto updateUserPassword(Long userId, UserDto userDto);
    UserDto deleteUser(Long userId);
    UserDto getUserById(Long userId);
    Page<UserDto> getUsers(Pageable pageable);
}

