package ru.lgtu.app.service;

import com.google.gson.*;
import java.io.*;
import java.time.*;
import java.util.*;

public class FileService {

    private final Gson gson;

    public FileService() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, context) -> LocalDate.parse(json.getAsString()))
                .registerTypeAdapter(LocalTime.class, (JsonSerializer<LocalTime>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
                .registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (json, type, context) -> LocalTime.parse(json.getAsString()))
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, context) -> LocalDateTime.parse(json.getAsString()))
                .create();
    }

    public <T> void saveList(String filePath, List<T> data) throws IOException {
        File file = new File(filePath);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
            LogService.info("Успешно сохранено объектов: {} в файл {}", data.size(), filePath);
        }
    }

    public <T> List<T> loadList(String filePath, Class<T[]> arrayClass) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new FileNotFoundException("Файл не найден: " + filePath);
        }

        if (file.length() == 0) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(filePath)) {
            T[] array = gson.fromJson(reader, arrayClass);
            List<T> list = (array != null) ? new ArrayList<>(Arrays.asList(array)) : new ArrayList<>();

            LogService.info("Успешно загружено объектов: {} из файла {}", list.size(), filePath);
            return list;

        } catch (JsonSyntaxException e) {
            throw new IOException("Ошибка парсинга JSON в файле: " + filePath, e);
        }
    }
}