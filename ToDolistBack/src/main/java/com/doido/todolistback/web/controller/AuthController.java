package com.doido.todolistback.web.controller;

import com.doido.todolistback.domain.user.dtos.request.LoginRequest;
import com.doido.todolistback.domain.user.dtos.request.RegisterRequest;
import com.doido.todolistback.domain.user.dtos.request.PasswordUpdateRequest;
import com.doido.todolistback.domain.user.dtos.response.UserResponse;
import com.doido.todolistback.domain.user.servicies.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
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

@Tag(name = "Auth", description = "Controlador de autenticacao")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Registrar usuário", description = "Ao enviar os dados ele registra o usuario no banco")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
       return new ResponseEntity<>(authService.registerUser(registerRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Faz login no sistema", description = "Ao enviar os dados ele faz login no sistema")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginDto) {
        return new ResponseEntity<>(authService.loginUser(loginDto), HttpStatus.OK);
    }

    @Operation(summary = "Atualiza senha do usuario", description = "Ao enviar os dados ele atualiza os campos de senha do usuario")
    @PostMapping("/updatepassword")
    public  ResponseEntity<UserResponse> updatePassword(@Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest) {
        return new ResponseEntity<>(authService.UpdatePassword(passwordUpdateRequest), HttpStatus.OK);
    }

}
