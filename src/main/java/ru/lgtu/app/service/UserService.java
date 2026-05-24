package ru.lgtu.app.service;
import ru.lgtu.app.model.User;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final FileService fileService;
    private final String usersFilePath;

    public UserService(FileService fileService, ConfigService configService, InputService inputService, ImageService imageService) {
        this.fileService = fileService;
        this.usersFilePath = configService.getUsersJsonPath();
    }


    public void addUser(User user) {
        try {
            List<User> users = fileService.loadList(usersFilePath, User[].class);
            users.add(user);
            fileService.saveList(usersFilePath, users);
            LogService.info("Добавлен новый пользователь {}!", user);

        } catch (IOException e) {
            LogService.error("addUser: Ошибка ввода-вывода: {}", e);
        } catch (Exception e) {
            LogService.error("addUser: Системная ошибка для {}: {}", user.toString(), e);
        }
    }

    public int generateNextId() {
        try {
            List<User> users = fileService.loadList(usersFilePath, User[].class);
            return users.stream().mapToInt(User::getId).max().orElse(0) + 1;
        } catch (IOException e) {
            LogService.error("generateNextId: Ошибка ввода-вывода: {}", e);
            return 2; // Страховка: если файла нет, начинаем с ID=2
        }
    }

    public Optional<User> getUser(int userId) {
        try {
            List<User> users = fileService.loadList(usersFilePath, User[].class);
            return users.stream()
                    .filter(user -> user.getId() == userId)
                    .findFirst();
        } catch (Exception e) {
            LogService.error("getUser: Системная ошибка для {}: {}", String.valueOf(userId), e);
            return Optional.empty();
        }
    }

    public Optional<User> getUser(String username) {
        try {
            List<User> users = fileService.loadList(usersFilePath, User[].class);
            return users.stream()
                    .filter(user -> username.equals(user.getUsername()))
                    .findFirst();
        } catch (Exception e) {
            LogService.error("getUser: Системная ошибка для {}: {}", username, e);
            return Optional.empty();
        }
    }

    public boolean isUserExist(String username) {
        try {
            List<User> users = fileService.loadList(usersFilePath, User[].class);
            return users.stream()
                    .anyMatch(user -> username.equals(user.getUsername()));
        } catch (Exception e) {
            LogService.error("isUserExist: Системная ошибка для {}: {}", username, e);
            return false;
        }
    }

    public List<User> getAllUsers() {
        try {
            return fileService.loadList(usersFilePath, User[].class);
        } catch (Exception e) {
            LogService.error("getAllUsers: Системная ошибка {}", e);
            return new ArrayList<>();
        }
    }

    public boolean deleteUser(String username) {
        if ("admin".equalsIgnoreCase(username)) {
            LogService.warn("deleteUser: нельзя удалить главного администратора");
            return false;
        }
        try {
            List<User> users = fileService.loadList(usersFilePath, User[].class);

            // Удаляем пользователя из списка, если логин совпадает
            boolean removed = users.removeIf(user -> user.getUsername().equalsIgnoreCase(username));

            if (removed) {
                fileService.saveList(usersFilePath, users);
                LogService.info("Пользователь {} был успешно удален", username);
                return true;
            }

            LogService.warn("deleteUser: Пользователь {} не найден для удаления", username);
            return false;
        } catch (Exception e) {
            LogService.error("Системная ошибка: невозможно прочитать базу пользователей! {}", e);
            return false;
        }
    }


    public boolean changePassword(String username, String newPassword) {
        try {
            List<User> users = fileService.loadList(usersFilePath, User[].class);

            Optional<User> userOpt = users.stream()
                    .filter(u -> u.getUsername().equalsIgnoreCase(username))
                    .findFirst();

            if (userOpt.isEmpty()) {
                LogService.info("changePassword: Пользователь {} не найден для смены пароля", username);
                return false;
            }

            User oldUser = userOpt.get();
            String newHash = SecureService.hashPassword(newPassword);

            User updatedUser = oldUser.withPassword(newHash);

            int index = users.indexOf(oldUser);
            users.set(index, updatedUser);

            fileService.saveList(usersFilePath, users);
            LogService.info("changePassword: Пароль для пользователя {} успешно изменен", username);
            return true;

        } catch (IOException e) {
            LogService.error("changePassword: Ошибка ввода-вывода: {}", e);
            return false;
        } catch (Exception e) {
            LogService.error("changePassword: Системная ошибка для {}: {}", username, e);
            return false;
        }
    }

}