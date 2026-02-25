package com.doido.todolistback.domain.group.shared.enuns;

public enum RolesGroup {

    OWNER(3, "owner"),
    ADMIN(2, "admin"),
    MEMBER(1, "member");

    private int level;
    private String role;

    RolesGroup(int level, String role) {
        this.level = level;
        this.role = role;
    }

    public int getLevel() {return level;}

    public String getRole() {
        return role;
    }
}
