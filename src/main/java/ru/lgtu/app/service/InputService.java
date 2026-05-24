package ru.lgtu.app.service;

import ru.lgtu.app.model.ModCategory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.function.Function;

public class InputService {
    public static <T> T inputField(String user, String field, Function<String, T> parser) {
        while (true) {
            String input = IO.readln("Введите " + field + ": ");

            LogService.userAction(user, "ввел " + field + " '" + input + "'");

            try {
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Поле не может быть пустым");
                }
                return parser.apply(input);
            } catch (Exception e) {
                LogService.warn("Пользователь ввёл некорректное {}. Ошибка: {}", field, e);
                IO.println("Ошибка: " + e.getMessage());
            }
        }
    }

    public static <T> T inputFieldNew(String user, String prompt, Function<String, T> parser) {
        while (true) {
            IO.print(prompt);

            String input = IO.readln("");

            LogService.userAction(user, "ввод данных: '" + input + "'");

            try {
                if (input == null || input.trim().isEmpty()) {
                    throw new IllegalArgumentException("Поле не может быть пустым");
                }
                return parser.apply(input.trim());
            } catch (Exception e) {
                LogService.warn("Ошибка ввода. Значение: '{}'. Ошибка: {}", input, e.getMessage());
                IO.println("│ ❌ Ошибка: " + e.getMessage());
                IO.println("├─────────────────────────────────────────");
            }
        }
    }
    
    public static LocalDate inputLocalDate(String user) {
        String input = IO.readln("Дата (гггг-мм-дд, или Enter): ");
        return input.isEmpty() ? LocalDate.now() : LocalDate.parse(input);
    }

    public static LocalTime inputLocalTime(String user) {
        String input = IO.readln("Время (чч:мм): ");
        return input.isEmpty() ? LocalTime.now() : LocalTime.parse(input);
    }

    public static String inputImage(String user) {
        String input = IO.readln("Путь к изображению (или Enter): ");
        String image = input.isBlank()
                ? ""
                : ImageService.encodeImage(input);
        LogService.userAction(user, "ввел Путь к изображению '" + input + "' и получил изображение '" + (image) + "'");
        return image;
    }


    public static ModCategory inputTrainType(String user) {
        IO.print("Тип поезда (");

        ModCategory[] types = ModCategory.values();

        for (int i = 0; i < types.length; i++) {
            IO.print((i + 1) + " - " + types[i]);

            if (i < types.length - 1) {
                IO.print(", ");
            }
        }

        IO.println("):");

        int choice = Integer.parseInt(IO.readln().trim()) - 1;

        if (choice < 0 || choice >= types.length) {
            IO.println("Неверный выбор. Использован NONE.");
            return ModCategory.NONE;
        }

        return types[choice];
    }
}
