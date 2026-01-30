package com.doido.todolistback.domain.user.servicies;

import com.doido.todolistback.domain.user.dtos.request.UserUpdateRequest;
import com.doido.todolistback.domain.user.dtos.response.UserResponse;

public interface UserService {

    UserResponse getInfoUser();

    UserResponse updateUserInfo(UserUpdateRequest userUpdateDto);
}
