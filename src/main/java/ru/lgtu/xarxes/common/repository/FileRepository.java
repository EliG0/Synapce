package ru.lgtu.xarxes.common.repository;

import com.google.gson.*;
import ru.lgtu.xarxes.common.core.LogService;
import ru.lgtu.xarxes.common.utils.JsonUtils;
import ru.lgtu.xarxes.common.utils.TomlUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Работа с файлами, чтение и сохранение.
 */

public class FileRepository {
    private final Gson gson = JsonUtils.getGson();

    private String loadRawString(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Файл не найден: " + filePath);
        }
        if (file.length() == 0) return "";
        return Files.readString(file.toPath());
    }

    private void saveRawString(String filePath, String content) throws IOException {
        File file = new File(filePath);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        Files.writeString(file.toPath(), content);
    }

    /**
     * Загрузка JSON Списков
     */
    public <T> List<T> loadJsonList(String filePath, Class<T[]> arrayClass) throws IOException {
        String json = loadRawString(filePath);
        if (json.isEmpty()) return new ArrayList<>();

        T[] array = gson.fromJson(json, arrayClass);
        return (array != null) ? new ArrayList<>(Arrays.asList(array)) : new ArrayList<>();
    }

    /**
     * Сохранение JSON Списков
     */
    public <T> void saveJsonList(String filePath, List<T> data) throws IOException {
        String json = gson.toJson(data);
        saveRawString(filePath, json);
    }

    /**
     * Загрузка TOML конфигурации
     */
    public <T> T loadTomlConfig(String filePath, Class<T> clazz) throws IOException {
        String tomlContent = loadRawString(filePath);
        return TomlUtils.parse(tomlContent, clazz);
    }

    /**
     * Сохранение TOML конфигурации
     */
    public <T> void saveTomlConfig(String filePath, T data) throws IOException {
        String tomlContent = TomlUtils.toTomlString(data);
        saveRawString(filePath, tomlContent);
    }
}