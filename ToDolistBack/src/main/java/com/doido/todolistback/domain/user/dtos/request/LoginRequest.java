package com.doido.todolistback.domain.user.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email não pode ser vazio")
    @Email
    private String email;

    @NotBlank(message = "Senha não pode ser vazia")
    private String password;
}
