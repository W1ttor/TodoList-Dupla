package com.doido.todolistback.domain.user.servicies.impl;

import com.doido.todolistback.domain.user.dtos.request.LoginRequest;
import com.doido.todolistback.domain.user.dtos.request.RegisterRequest;
import com.doido.todolistback.domain.user.dtos.request.PasswordUpdateRequest;
import com.doido.todolistback.domain.user.dtos.response.UserResponse;
import com.doido.todolistback.domain.user.entity.User;
import com.doido.todolistback.domain.user.servicies.AuthService;
import com.doido.todolistback.domain.user.servicies.TokenService;
import com.doido.todolistback.domain.user.shared.enums.RolesUser;
import com.doido.todolistback.domain.user.shared.mappers.UserMapper;
import com.doido.todolistback.domain.user.repositories.UserRepository;
import com.doido.todolistback.shared.exception.CustomsExceptions.InvalidCredentialsException;
import com.doido.todolistback.shared.exception.CustomsExceptions.UserAlreadyExistsException;
import com.doido.todolistback.shared.exception.CustomsExceptions.UserNotFoundException;
import com.doido.todolistback.shared.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Override
    public String loginUser(LoginRequest loginDto) {

        User user = (User) userRepository.findByEmail(loginDto.getEmail());

        if (user == null || !passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return tokenService.generateToken(user);
    }

    @Override
    public UserResponse registerUser(RegisterRequest userDto) {

        var user = userMapper.registerRequest(userDto);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }

        user.setRole(RolesUser.USER);

        String SenhaCriptografada = passwordEncoder.encode(user.getPassword());
        user.setPassword(SenhaCriptografada);
        userRepository.save(user);

        return userMapper.userResponse(user);
    }

    @Override
    public UserResponse UpdatePassword(PasswordUpdateRequest userDto) {
        var user =  getUser();
        if (!passwordEncoder.matches(userDto.getOldPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        user.setPassword(passwordEncoder.encode(userDto.getNewPassword()));
        userRepository.save(user);
        return userMapper.userResponse(user);

    }


    //Private Methods
    private User getUser(){
        var user =  (User) SecurityUtils.getUser();
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }
}
