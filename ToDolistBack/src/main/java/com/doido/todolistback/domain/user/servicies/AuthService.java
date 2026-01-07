package com.doido.todolistback.domain.user.servicies;

import com.doido.todolistback.domain.user.dtos.post.LoginDto;
import com.doido.todolistback.domain.user.dtos.post.PostUserDto;

public interface AuthService {
    String loginUser (LoginDto userDto);
    String registerUser (PostUserDto userDto);
}
