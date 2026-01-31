package com.doido.todolistback.domain.user.entity;

import com.doido.todolistback.domain.task.entity.Task;
import com.doido.todolistback.domain.user.shared.enums.RolesUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")

public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @CreationTimestamp
    private LocalDate createDate;

    @UpdateTimestamp
    private LocalDate updateDate;

    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Task> tasks;

    @Enumerated(EnumType.STRING)
    private RolesUser role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == RolesUser.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true /*UserDetails.super.isAccountNonExpired()*/;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true /*UserDetails.super.isAccountNonLocked()*/;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true/*UserDetails.super.isCredentialsNonExpired()*/;
    }

    @Override
    public boolean isEnabled() {
        return true /*UserDetails.super.isEnabled()*/;
    }

}