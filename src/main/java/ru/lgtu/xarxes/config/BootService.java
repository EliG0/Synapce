package ru.lgtu.xarxes.config;


import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Инициализация системы при запуске (создание файлов, дефолтных пользователей и игр)
 */

public class BootService {
//    private final FileService fileService;
//    private final String usersFilePath;
//    private final String modsFilePath;
//    private final String gamesFilePath;
//
//    public BootService(FileService fileService, ConfigService configService) {
//        this.fileService = fileService;
//        this.usersFilePath = configService.getUsersJsonPath();
//        this.modsFilePath = configService.getModsJsonPath();
//        this.gamesFilePath = configService.getGamesJsonPath();
//    }
//
//    public void initSystem() {
//        LogService.info("Запуск инициализации системы...");
//
//        String[] filesToCheck = {
//                usersFilePath,
//                modsFilePath,
//                gamesFilePath
//        };
//
//        for (String path : filesToCheck) {
//            checkAndCreateFile(path);
//        }
//
//        checkUsers();
//        checkGames();
//
//        LogService.info("Инициализация завершена.");
//    }
//
//    private void checkAndCreateFile(String filePath) {
//        File file = new File(filePath);
//
//        try {
//            if (file.getParentFile() != null) {
//                file.getParentFile().mkdirs();
//            }
//
//            if (!file.exists()) {
//                if (file.createNewFile()) {
//                    LogService.info("Файл создан: {}", filePath);
//                }
//            }
//        } catch (IOException e) {
//            LogService.error("Ошибка при создании файла {}: {}", filePath, e);
//        }
//    }
//
//    private void checkUsers() {
//        try {
//            List<User> users = fileService.loadList(usersFilePath, User[].class);
//            boolean guestExist = users.stream().anyMatch(u -> u.getId() == 0);
//            if (!guestExist) {
//                users.add(User.createGuest());
//                LogService.info("Гость добавлен в: {}", usersFilePath);
//            }
//
//            boolean adminExists = users.stream().anyMatch(u -> u.getId() == 1);
//            if (!adminExists) {
//                users.add(User.createAdmin());
//                LogService.info("Админ добавлен в: {}", usersFilePath);
//            }
//
//            try {
//                fileService.saveList(usersFilePath, users);
//            } catch (IOException e) {
//                LogService.error("Ошибка при создании файла {}: {}", usersFilePath, e);
//            }
//        } catch (IOException e) {
//            LogService.error("Ошибка невозможно прочитать базу пользователей! {}", usersFilePath, e);
//        }
//    }
//
//    private void checkGames() {
//        try {
//            List<Game> games = fileService.loadList(gamesFilePath, Game[].class);
//            if (games.isEmpty()) {
//
//                games.add(new Game(1, "The Elder Scrolls V: Skyrim", "Skyrim"));
//                games.add(new Game(2, "The Witcher 3: Wild Hunt", "TheWitcher3"));
//                games.add(new Game(3, "Minecraft", "Minecraft"));
//
//                fileService.saveList(gamesFilePath, games);
//                LogService.info("BOOT: Созданы дефолтные игры в: {}", modsFilePath);
//            }
//
//        } catch (Exception e) {
//            LogService.error("BOOT: Ошибка при инициализации игр", e);
//        }
//    }
}