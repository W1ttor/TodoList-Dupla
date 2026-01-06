package com.doido.todolistback.domain.user.servicies.impl;

import com.doido.todolistback.domain.user.dtos.post.PostUserDto;
import com.doido.todolistback.domain.user.dtos.request.RequestUserDto;
import com.doido.todolistback.domain.user.shared.mappers.UserMapper;
import com.doido.todolistback.infra.repositories.UserRepository;
import com.doido.todolistback.domain.user.servicies.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Override
    public RequestUserDto addUser(PostUserDto userDto) {

        var user = userMapper.toUserPost(userDto);

        String SenhaCriptografada = encoder.encode(user.getPassword());
        user.setPassword(SenhaCriptografada);
        userRepository.save(user);

        return userMapper.toRequestUserDto(user);
    }
}
