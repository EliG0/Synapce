package ru.lgtu.app.service;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;
import ru.lgtu.app.model.User;
import ru.lgtu.app.model.Role;
import ru.lgtu.app.ui.Theme;

import java.time.LocalDateTime;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthService {
    private final FileService fileService;
    private final String usersFilePath;
    private final UserService userService;

    public AuthService(FileService fileService, ConfigService configService,InputService inputService, ImageService imageService, UserService userService) {
        this.fileService = fileService;
        this.usersFilePath = configService.getUsersJsonPath();
        this.userService = userService;
    }

    public Optional<User> login(String username, String password) {
        List<User> users;

        try {
            users = fileService.loadList(usersFilePath, User[].class);
        } catch (IOException e) {
            LogService.error("Ошибка: База пользователей недоступна! {}", e);
            return Optional.empty();
        }

        Optional<User> foundUser = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();

        if (foundUser.isEmpty()) {
            LogService.info("Попытка входа: пользователь '{}' не найден", username);
            return Optional.empty();
        }

        if (!foundUser.get().checkPassword(password)) {
            LogService.info("Попытка входа: неверный пароль для '{}'. Введено: {}", username, password);
            return Optional.empty();
        }

        LogService.info("Успешный вход: пользователь '{}'", username);
        return foundUser;
    }

    public User loginAsGuest() {
        try {
            List<User> users = fileService.loadList(usersFilePath, User[].class);
            return users.stream()
                    .filter(u -> u.getId() == 0)
                    .findFirst()
                    .orElse(User.createGuest()); // Страховка: если файла нет, создаем гостя в памяти

        } catch (IOException e) {
            LogService.error("Ошибка загрузки данных для Гостя: {}", e);
            return User.createGuest();
        }
    }
    public boolean isUserExist(String username) {
        try {
            List<User> users = fileService.loadList(usersFilePath, User[].class);
            return users.stream()
                    .anyMatch(u -> u.getUsername().equals(username));
        } catch (Exception e) {
            LogService.error("Ошибка: невозможно прочитать базу пользователей! {}", e);
            return false;
        }
    }

    public boolean registration(String username, String password) {
        try {
            if (userService.isUserExist(username)) {
                LogService.info("Регистрация отклонена: логин {} занят", username);
                return false;
            }

            userService.addUser(User.createNew(userService.generateNextId(), username, SecureService.hashPassword(password)));

            LogService.info("Зарегистрирован новый пользователь {}!", username);
            return true;
        } catch (Exception e) {
            LogService.info("Ошибка: невозможно прочитать базу пользователей! {}", e);
            return false;
        }
    }
}