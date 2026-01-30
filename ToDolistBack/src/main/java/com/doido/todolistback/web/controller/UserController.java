package com.doido.todolistback.web.controller;

import com.doido.todolistback.domain.user.dtos.request.UserUpdateRequest;
import com.doido.todolistback.domain.user.dtos.response.UserResponse;
import com.doido.todolistback.domain.user.servicies.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<UserResponse> getInfoUser() {
        return new ResponseEntity<>(userService.getInfoUser(), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<UserResponse> userUpdate(@RequestBody UserUpdateRequest userUpdateDto) {
        return new ResponseEntity<>(userService.updateUserInfo(userUpdateDto), HttpStatus.OK);
    }

}
