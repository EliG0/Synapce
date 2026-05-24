package ru.lgtu.app.model; // Твой package

public enum ModCategory {
    NONE(""),
    ANIMATIONS("Анимации"),
    CREATURES("Существа"),
    GAMEPLAY("Геймплей"),
    GRAPHICS("Графика"),
    LOCALIZATION("Локализация"),
    MAPS("Карты и локации"),
    OPTIMIZATION("Оптимизация"),
    QUESTS("Квесты"),
    SOUNDS("Звуки и музыка"),
    UI("Интерфейс"),
    UTILITIES("Утилиты и инструменты"),
    WEAPONS("Оружие и броня");

    private final String title;

    ModCategory(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}