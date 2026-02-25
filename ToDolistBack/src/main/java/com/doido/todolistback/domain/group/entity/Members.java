package com.doido.todolistback.domain.group.entity;


import com.doido.todolistback.domain.group.shared.enuns.RolesGroup;
import com.doido.todolistback.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(
        name = "MEMBERS",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"group_id", "user_id"})
        }
)

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private RolesGroup  rolesGroup;

    private Members(Group group, User user, RolesGroup rolesGroup) {
        this.group = group;
        this.user = user;
        this.rolesGroup = rolesGroup;
    }

    public static Members Create(Group group, User user, RolesGroup rolesGroup){
       return new Members(group,user,rolesGroup);
    }
}
