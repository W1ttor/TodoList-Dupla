package com.doido.todolistback.web.controller;

import com.doido.todolistback.domain.user.dtos.request.LoginRequest;
import com.doido.todolistback.domain.user.dtos.request.RegisterRequest;
import com.doido.todolistback.domain.user.dtos.request.PasswordUpdateRequest;
import com.doido.todolistback.domain.user.dtos.response.UserResponse;
import com.doido.todolistback.domain.user.servicies.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
       return new ResponseEntity<>(authService.registerUser(registerRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginDto) {
        return new ResponseEntity<>(authService.loginUser(loginDto), HttpStatus.OK);
    }

    @PostMapping("/updatepassword")
    public  ResponseEntity<UserResponse> updatePassword(@Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest) {
        return new ResponseEntity<>(authService.UpdatePassword(passwordUpdateRequest), HttpStatus.OK);
    }

}
