package com.doido.todolistback.infra.controller;

import com.doido.todolistback.domain.user.dtos.post.PostUserDto;
import com.doido.todolistback.domain.user.dtos.request.RequestUserDto;
import com.doido.todolistback.domain.user.servicies.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping
    public RequestUserDto addUser(@RequestBody PostUserDto postUserDto) {
        return userService.addUser(postUserDto);
    }

    public PostUserDto findUser(@RequestBody PostUserDto postUserDto) {
        return null;
    }

}
