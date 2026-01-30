package com.doido.todolistback.domain.user.servicies.impl;

import com.doido.todolistback.domain.user.dtos.post.LoginDto;
import com.doido.todolistback.domain.user.dtos.post.PostUserDto;
import com.doido.todolistback.domain.user.dtos.post.UpdatePasswordUser;
import com.doido.todolistback.domain.user.dtos.request.RequestUserDto;
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
    public String loginUser(LoginDto loginDto) {

        User user = (User) userRepository.findByEmail(loginDto.getEmail());

        if (user == null || !passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return tokenService.generateToken(user);
    }

    @Override
    public RequestUserDto registerUser(PostUserDto userDto) {

        var user = userMapper.toUserPost(userDto);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }

        user.setRole(RolesUser.USER);

        String SenhaCriptografada = passwordEncoder.encode(user.getPassword());
        user.setPassword(SenhaCriptografada);
        userRepository.save(user);

        return userMapper.toRequestUserDto(user);
    }

    @Override
    public RequestUserDto UpdatePassword(UpdatePasswordUser userDto) {
        var user =  getUser();
        if (!passwordEncoder.matches(userDto.getOldPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        user.setPassword(passwordEncoder.encode(userDto.getNewPassword()));
        userRepository.save(user);
        return userMapper.toRequestUserDto(user);

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
