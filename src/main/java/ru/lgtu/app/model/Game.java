package ru.lgtu.app.model;

import java.util.Objects;

public class Game {
    private int id;
    private String name;
    private String modFolderName;

    public Game(){}

    public Game(int id, String name, String modFolderName) {
        this.id = id;
        this.name = name;
        this.modFolderName = modFolderName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getModFolderName() {
        return modFolderName;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id; // Считаем игры одинаковыми, если у них совпадает ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}