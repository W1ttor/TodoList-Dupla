package com.doido.todolistback.domain.user.servicies;

import com.doido.todolistback.domain.user.dtos.request.LoginRequest;
import com.doido.todolistback.domain.user.dtos.request.RegisterRequest;
import com.doido.todolistback.domain.user.dtos.request.PasswordUpdateRequest;
import com.doido.todolistback.domain.user.dtos.response.UserResponse;

public interface AuthService {
    String loginUser (LoginRequest userDto);
    UserResponse registerUser (RegisterRequest userDto);
    UserResponse UpdatePassword(PasswordUpdateRequest userDto);
}
