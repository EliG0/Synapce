package ru.lgtu.app.service;

import org.mindrot.jbcrypt.BCrypt;
import ru.lgtu.app.model.*;
import ru.lgtu.app.service.ConfigService;
import ru.lgtu.app.service.FileService;
import ru.lgtu.app.service.LogService;
import ru.lgtu.app.ui.Theme;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DebugData {
    static ConfigService configService = new ConfigService();
    static FileService fileService = new FileService();
    static String usersPath = configService.getUsersJsonPath();
    static String modsPath = configService.getModsJsonPath();
    static String gamesPath = configService.getGamesJsonPath();

    public static void generate() {
//        generateGames();
//        generateUsers();
//        generateMods();
    }

//    public static void generateUsers() {
//        // 1. ГЕНЕРАЦИЯ ПОЛЬЗОВАТЕЛЕЙ (Проверяем, пуст ли список)
//        List<User> users = fileService.loadList(usersPath, User[].class);
//
//        // Проверяем, есть ли вообще админ в системе
//        boolean adminExists = users.stream()
//                .anyMatch(user -> "admin".equalsIgnoreCase(user.getUsername()));
//
//        if (!adminExists) {
//            String adminHash = BCrypt.hashpw("admin", BCrypt.gensalt());
//
//            User defaultAdmin = new User(
//                    1,
//                    "admin",
//                    Role.ADMIN,
//                    adminHash,
//                    LocalDateTime.now(),
//                    new ArrayList<>(),
//                    new ArrayList<>(),
//                    Theme.CLASSIC
//            );
//
//            // Создадим еще одного обычного пользователя для тестов админки
//            String userHash = BCrypt.hashpw("user", BCrypt.gensalt());
//            User defaultUser = new User(
//                    2,
//                    "user",
//                    Role.USER,
//                    userHash,
//                    LocalDateTime.now().minusDays(5), // Зарегистрирован 5 дней назад
//                    new ArrayList<>(),
//                    new ArrayList<>(),
//                    Theme.CLASSIC
//            );
//
//            users.add(defaultAdmin);
//            users.add(defaultUser);
//
//            fileService.saveList(usersPath, users);
//            LogService.info("ДЕБАГ: Созданы дефолтные пользователи (admin и GamerNeo)");
//        }
//
//
//    }



//    public static void generateGames() {
//        // 2. ГЕНЕРАЦИЯ ИГР
//        List<Game> games = fileService.loadList(gamesPath, Game[].class);
//        if (games.isEmpty()) {
//
//            games.add(new Game(1, "The Elder Scrolls V: Skyrim", "Skyrim"));
//            games.add(new Game(2, "The Witcher 3: Wild Hunt", "TheWitcher3"));
//            games.add(new Game(3, "Minecraft", "Minecraft"));
//
//            fileService.saveList(gamesPath, games);
//            IO.println("ДЕБАГ: 3 тестовые игры успешно добавлены в " + gamesPath);
//        }
//    }



//    public static void generateMods() {
//        // 3. ГЕНЕРАЦИЯ МОДОВ
//        List<Mod> mods = fileService.loadList(modsPath, Mod[].class);
//
//        if (mods.isEmpty()) {
//            Mod mod1 = Mod.createNew(
//                    1,
//                    "SkyTeam",
//                    1, // Skyrim
//                    "SkyUI",
//                    ModCategory.UI);
//
//            Mod mod2 = Mod.createNew(
//                    2,
//                    "SureAI",
//                    1, // Skyrim
//                    "Enderal: Forgotten Stories",
//                    ModCategory.GAMEPLAY);
//
//            Mod mod3 = Mod.createNew(
//                    3,
//                    "Halk Hogan",
//                    2, // Witcher 3
//                    "The Witcher 3 HD Reworked",
//                    ModCategory.GRAPHICS);
//
//            mods.add(mod1);
//            mods.add(mod2);
//            mods.add(mod3);
//
//            fileService.saveList(modsPath, mods);
//            LogService.info("ДЕБАГ: Глобальный каталог модов успешно заполнен тестовыми модификациями.");
//            IO.println("ДЕБАГ: Глобальный каталог модов успешно заполнен тестовыми модификациями ");
//        }
//    }


}