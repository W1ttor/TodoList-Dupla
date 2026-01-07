package com.doido.todolistback.infra.controller;

import com.doido.todolistback.domain.user.servicies.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;


}
