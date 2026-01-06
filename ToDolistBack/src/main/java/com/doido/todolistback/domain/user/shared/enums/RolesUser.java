package com.doido.todolistback.domain.user.shared.enums;

public enum RolesUser {

    ADMIN("admin"),
    USER("user");

    private String role;

    RolesUser(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
