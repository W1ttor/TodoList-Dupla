package com.doido.todolistback.domain.user.shared.mappers;

import com.doido.todolistback.domain.user.dtos.request.LoginRequest;
import com.doido.todolistback.domain.user.dtos.request.RegisterRequest;
import com.doido.todolistback.domain.user.dtos.response.UserResponse;
import com.doido.todolistback.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper{


    User registerRequest(RegisterRequest postUserDto);

    UserResponse userResponse(User user);
}
