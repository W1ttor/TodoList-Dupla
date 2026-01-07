package com.doido.todolistback.domain.user.servicies.impl;

import com.doido.todolistback.domain.user.servicies.UserService;
import com.doido.todolistback.domain.user.shared.mappers.UserMapper;
import com.doido.todolistback.infra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

}
