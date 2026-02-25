package com.doido.todolistback.domain.group.dto.post;

import com.doido.todolistback.domain.group.entity.Members;
import com.doido.todolistback.domain.group.shared.enuns.RolesGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
public class MembersPost {

    private UUID id;
    private UUID userId;
    private String username;
    private RolesGroup rolesGroup;


    private MembersPost(Members members) {
        this.id = members.getId();
        this.userId = members.getUser().getId();
        this.username = members.getUser().getUsername();
        this.rolesGroup = members.getRolesGroup();
    }

    public static MembersPost Create(Members members){
        return new MembersPost(members);
    }

}
