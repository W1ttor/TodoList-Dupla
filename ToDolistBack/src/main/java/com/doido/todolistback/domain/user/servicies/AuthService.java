package com.doido.todolistback.domain.user.servicies;

import com.doido.todolistback.domain.user.dtos.post.LoginDto;
import com.doido.todolistback.domain.user.dtos.post.PostUserDto;
import com.doido.todolistback.domain.user.dtos.post.UpdatePasswordUser;
import com.doido.todolistback.domain.user.dtos.request.RequestUserDto;

public interface AuthService {
    String loginUser (LoginDto userDto);
    RequestUserDto registerUser (PostUserDto userDto);
    RequestUserDto UpdatePassword(UpdatePasswordUser userDto);
}
