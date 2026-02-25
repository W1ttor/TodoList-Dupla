package com.doido.todolistback.domain.group.shared.mapper;

import com.doido.todolistback.domain.group.dto.post.MembersPost;
import com.doido.todolistback.domain.group.entity.Members;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MembersMapper {

    MembersPost membersPost(Members  members);
}
