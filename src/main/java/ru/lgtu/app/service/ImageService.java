package ru.lgtu.app.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class ImageService {

    public static String encodeImage(String filePath) {
        try {
            byte[] bytes = Files.readAllBytes(Path.of(filePath));
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            LogService.error("Ошибка кодирования изображения по пути: " + filePath, e);
            return "";
        }
    }
    public static byte[] decodeImage(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}