package com.doido.todolistback.domain.group.shared.mapper;

import com.doido.todolistback.domain.group.dto.post.GroupPost;
import com.doido.todolistback.domain.group.entity.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupPost groupPost(Group group);
}
