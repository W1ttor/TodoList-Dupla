package com.doido.todolistback.infra.controller;

import com.doido.todolistback.domain.user.dtos.post.LoginDto;
import com.doido.todolistback.domain.user.dtos.post.PostUserDto;
import com.doido.todolistback.domain.user.servicies.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody PostUserDto userDto) {
        authService.registerUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto) {
        authService.loginUser(loginDto);
        return ResponseEntity.ok().build();
    }

}
