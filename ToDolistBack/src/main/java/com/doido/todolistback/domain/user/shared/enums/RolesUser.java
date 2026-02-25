package com.doido.todolistback.domain.user.shared.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Getter
public enum RolesUser {

    ADMIN("admin") {
        @Override
        public Set<String> getPermissions() {
            return Set.of("CON", "ALT", "INC", "EXC", "INA", "EXP");
        }
    },
    USER("user") {
        @Override
        public Set<String> getPermissions() {
            return Set.of("CON", "EXP");
        }
    };

    private String role;

    RolesUser(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return role;
    }

    @JsonCreator
    public static RolesUser of(final String role) {
        AtomicReference<String> roleRaw = new AtomicReference<>(role);
        Optional.ofNullable(role).ifPresent(roleRaw::set);

        return Stream.of(values())
                .filter(v -> v.getRole().equals(roleRaw.get()))
                .findFirst()
                .orElseThrow(()  -> new IllegalArgumentException("Tipo de usuário não suportado!"));
    }


    public abstract Set<String> getPermissions();
}
