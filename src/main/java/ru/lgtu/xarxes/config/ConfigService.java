package ru.lgtu.xarxes.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Загружает настройки из config.properties
 */
public class ConfigService {
    private static final String CONFIG_FILE = "src/main/resources/config.properties";
    private final Properties properties = new Properties();
    private final String usersDefault = "";
    private final String modsDefault = "";
    private final String gamesDefault = "";


    public ConfigService() {
        loadConfig();
    }

    private void loadConfig() {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException ex) {
            System.err.println("Критическая ошибка: Не удалось загрузить config.properties! Используются дефолтные значения.");
            properties.setProperty("app.users.json", usersDefault);
            properties.setProperty("app.mods.json", modsDefault);
            properties.setProperty("app.games.json", gamesDefault);
        }
    }

    public String getUsersJsonPath() {
        return properties.getProperty("app.users.json", usersDefault);
    }

    public String getModsJsonPath() {
        return properties.getProperty("app.mods.json", modsDefault);
    }

    public String getGamesJsonPath() {
        return properties.getProperty("app.games.json", gamesDefault);
    }

}