package com.doido.todolistback.domain.user.servicies;

import com.doido.todolistback.domain.user.entity.User;

import java.time.Instant;

public interface TokenService {
    String generateToken(User user);
    String ValidationToken (String token);
    Instant generateTokenExpirationDate();
}