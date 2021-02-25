package main.model;

public enum Permission {
    USER("user:write"),
    ADMIN("user:create");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
