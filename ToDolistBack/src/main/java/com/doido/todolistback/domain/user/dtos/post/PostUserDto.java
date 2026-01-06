package com.doido.todolistback.domain.user.dtos.post;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserDto {

    @NotBlank(message = "Campo Username não pode ser vazio" )
    private String username;

    @NotBlank(message = "Campo Email não pode ser vazio" )
    @Email
    private String email;

    @NotBlank(message = "Campo Password não pode ser vazio" )
    private String password;
}
