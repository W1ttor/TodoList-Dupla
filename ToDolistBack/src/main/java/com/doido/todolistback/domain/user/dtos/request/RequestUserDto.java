package com.doido.todolistback.domain.user.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RequestUserDto {

    private UUID id;
    private String username;
    private String email;
    private LocalDate createDate;
    private LocalDate updateDate;
}
