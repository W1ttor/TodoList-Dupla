package com.doido.todolistback.domain.user.shared.mappers;

import com.doido.todolistback.domain.user.dtos.post.LoginDto;
import com.doido.todolistback.domain.user.entity.User;
import com.doido.todolistback.domain.user.dtos.post.PostUserDto;
import com.doido.todolistback.domain.user.dtos.request.RequestUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper{


    User toUserPost(PostUserDto postUserDto);
    RequestUserDto toRequestUserDto(User user);

    LoginDto toUser(User user);
}
