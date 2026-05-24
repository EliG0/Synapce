package ru.lgtu.app.model;

public enum Role {
    GUEST("Гость"),
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