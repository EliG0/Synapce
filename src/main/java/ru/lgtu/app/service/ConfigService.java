package ru.lgtu.app.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigService {
    private static final String CONFIG_FILE = "config.properties";
    private final Properties properties = new Properties();

    public ConfigService() {
        loadConfig();
    }

    private void loadConfig() {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException ex) {
            System.err.println("Критическая ошибка: Не удалось загрузить config.properties! Используются дефолтные значения.");
            properties.setProperty("app.users.json", "data/users.json");
            properties.setProperty("app.mods.json", "data/mods_catalog.json");
            properties.setProperty("app.games.json", "data/games.json");
            properties.setProperty("modrunner.game.directory", "Games/Skyrim");
        }
    }

    public String getUsersJsonPath() {
        return properties.getProperty("app.users.json", "data/users.json");
    }

    public String getModsJsonPath() {
        return properties.getProperty("app.mods.json", "data/mods_catalog.json");
    }

    public String getGamesJsonPath() {
        return properties.getProperty("app.games.json", "data/games.json");
    }

    public String getGameDirectory() {
        return properties.getProperty("modrunner.game.directory", "Games/Skyrim");
    }
}