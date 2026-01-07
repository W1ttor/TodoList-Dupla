package com.doido.todolistback.domain.user.servicies.impl;

import com.doido.todolistback.domain.user.dtos.post.LoginDto;
import com.doido.todolistback.domain.user.dtos.post.PostUserDto;
import com.doido.todolistback.domain.user.entity.User;
import com.doido.todolistback.domain.user.servicies.AuthService;
import com.doido.todolistback.domain.user.servicies.TokenService;
import com.doido.todolistback.domain.user.shared.enums.RolesUser;
import com.doido.todolistback.domain.user.shared.mappers.UserMapper;
import com.doido.todolistback.infra.repositories.UserRepository;
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
            throw new RuntimeException("Invalid username or password");
        }

        return tokenService.generateToken(user);
    }

    @Override
    public String registerUser(PostUserDto userDto) {

        var user = userMapper.toUserPost(userDto);

        user.setRole(RolesUser.USER);

        String SenhaCriptografada = passwordEncoder.encode(user.getPassword());
        user.setPassword(SenhaCriptografada);
        userRepository.save(user);

        return "Usuário cadastrado com sucesso!";
    }
}
