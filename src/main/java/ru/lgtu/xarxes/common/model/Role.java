package ru.lgtu.xarxes.common.model;

public enum Role {
    USER("Пользователь"),
    MODERATOR("Модератор"),
    ADMIN("Администратор");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}