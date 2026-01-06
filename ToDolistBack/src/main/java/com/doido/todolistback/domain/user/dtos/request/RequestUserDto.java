package com.doido.todolistback.domain.user.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestUserDto {

    private String username;
    private String email;
}
