package com.doido.todolistback.domain.user.servicies.impl;

import com.doido.todolistback.domain.user.dtos.post.UserUpdateDto;
import com.doido.todolistback.domain.user.dtos.request.RequestUserDto;
import com.doido.todolistback.domain.user.entity.User;
import com.doido.todolistback.domain.user.servicies.UserService;
import com.doido.todolistback.domain.user.shared.mappers.UserMapper;
import com.doido.todolistback.domain.user.repositories.UserRepository;
import com.doido.todolistback.shared.exception.CustomsExceptions.UserNotFoundException;
import com.doido.todolistback.shared.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Override
    public RequestUserDto getInfoUser() {
        User user = getUser();
        return userMapper.toRequestUserDto(user);
    }

    @Override
    public RequestUserDto updateUserInfo(UserUpdateDto userUpdateDto) {
        User user = getUser();

        if (userUpdateDto.getUsername() != null) {
            user.setUsername(userUpdateDto.getUsername());
        }

        userRepository.save(user);
        return userMapper.toRequestUserDto(user);
    }


    //private method
    private User getUser(){
        User user = (User) SecurityUtils.getUser();
        if (user == null){
            throw new UserNotFoundException("User not found");
        }
        return user;
    }
}
