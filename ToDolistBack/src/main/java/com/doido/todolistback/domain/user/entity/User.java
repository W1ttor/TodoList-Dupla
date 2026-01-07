package com.doido.todolistback.domain.user.entity;

import com.doido.todolistback.domain.user.shared.enums.RolesUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TABLE_USER")

public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

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