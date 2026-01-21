package com.doido.todolistback.domain.user.servicies;

import com.doido.todolistback.domain.user.dtos.post.UserUpdateDto;
import com.doido.todolistback.domain.user.dtos.request.RequestUserDto;

public interface UserService {

    RequestUserDto getInfoUser();

    RequestUserDto updateUserInfo(UserUpdateDto userUpdateDto);
}
