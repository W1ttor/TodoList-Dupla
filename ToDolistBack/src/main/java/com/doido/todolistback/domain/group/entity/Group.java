package com.doido.todolistback.domain.group.entity;

import com.doido.todolistback.domain.group.shared.enuns.RolesGroup;
import com.doido.todolistback.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_groups")
@NoArgsConstructor
@Getter @Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Members> members =  new HashSet<>();

    private Group(String title) {
        this.title = title;
    }

    public static Group create(String title){
        Group group = new Group(title);
        return group;
    }

    public Boolean addMember(User user, RolesGroup rolesGroup) {;
        return members.add(Members.Create(this , user, rolesGroup));
    }


}
