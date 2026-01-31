package com.doido.todolistback.web.controller;

import com.doido.todolistback.domain.user.dtos.request.UserUpdateRequest;
import com.doido.todolistback.domain.user.dtos.response.UserResponse;
import com.doido.todolistback.domain.user.servicies.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/user")
@RequiredArgsConstructor

@Tag(name = "User", description = "Controlador de Usuario")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Obter usuario", description = "Entrega todas as informacoes do usuario")
    @GetMapping()
    public ResponseEntity<UserResponse> getInfoUser() {
        return new ResponseEntity<>(userService.getInfoUser(), HttpStatus.OK);
    }

    @Operation(summary = "Atualiza o usuario", description = "Atualiza os campos do usuario")
    @PostMapping("/update")
    public ResponseEntity<UserResponse> userUpdate(@RequestBody UserUpdateRequest userUpdateDto) {
        return new ResponseEntity<>(userService.updateUserInfo(userUpdateDto), HttpStatus.OK);
    }

}
