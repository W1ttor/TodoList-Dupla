package com.doido.todolistback.domain.user.servicies;

import com.doido.todolistback.domain.user.dtos.post.PostUserDto;
import com.doido.todolistback.domain.user.dtos.request.RequestUserDto;

public interface UserService {

    RequestUserDto addUser(PostUserDto userDto);
}
