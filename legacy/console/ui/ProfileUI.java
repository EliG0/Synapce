package ru.lgtu.xarxes.legacy.console.ui;

import ru.lgtu.xarxes.legacy.config.AppContext;
import ru.lgtu.xarxes.legacy.common.model.User;
import ru.lgtu.xarxes.legacy.vortex.service.InstalledModService;
import ru.lgtu.xarxes.legacy.common.service.LogService;
import ru.lgtu.xarxes.legacy.nexus.service.ModService;
import ru.lgtu.xarxes.legacy.common.service.UserService;
public class ProfileUI {

    private final User currentUser;
    private final UserService userService;
    private final ModService modService;
    private final InstalledModService installedModService;
    private final AppContext ctx;

    public ProfileUI(AppContext ctx, User user) {
        this.ctx = ctx;
        this.userService = ctx.userService;
        this.modService = ctx.modService;
        this.installedModService = ctx.installedModService;
        this.currentUser = user;
    }

    public void start() {
        while (true) {
            showMenu();
            String choice = IO.readln();
            if ("0".equals(choice)) return;
            processChoice(choice);
        }
    }

    private void showMenu() {
        String MenuHeader = """
                \n╭•❃°•°❀°• МОЙ ПРОФИЛЬ
                │ %d ─ Логин: %s
                """;

        String MenuRole = """
                │ %d ─ Роль: %s
                """;

        String MenuInstalledModsCount = """
                │ %d ─ Установленых модификаций: %d
                """;

        String MenuLikesCount = """
                │ %d ─ Лайки: %d
                """;

        String MenuTheme = """
                │ %d ─ Тема: %s
                """;

        String MenuPassword = """
                │ %d ─ Сменить пароль
                """;

        String MenuDateOfRegistration = """
                │ %d ─ Дата регистрации: %s
                """;

        String MenuId = """
                │ %d ─ ID: %d
                """;

        String MenuUploadMods = """
                │ %d ─ Мои модификации: %d
                """;

        String MenuBottom = """
                │ 0 ─ В главное меню
                ├•❃°•°❀°•°❃•❃°•°❀°•°❃•
                ╰➤ㅤ""";

        String result = MenuHeader.formatted(1, currentUser.getUsername());
        result += MenuRole.formatted(2, currentUser.getRole());
        result += MenuInstalledModsCount.formatted(3, currentUser.getInstalledMods().size());
        result += MenuLikesCount.formatted(4, currentUser.getLikedModIds().size());
        result += MenuTheme.formatted(5, currentUser.getCurrentTheme());
        result += MenuPassword.formatted(6);
        result += MenuDateOfRegistration.formatted(7, currentUser.getRegistrationDate());
        result += MenuId.formatted(8, currentUser.getId());
        result += currentUser.getUploadMods().isEmpty() ? "" : MenuUploadMods.formatted(7, currentUser.getUploadMods().size());
        result += MenuBottom;
       IO.print(result);
    }

    private void processChoice(String choice) {
        try {
            switch (choice) {
                case "1" -> {
                    IO.println("Добро пожаловать %s!".formatted(currentUser.getUsername()));
                    return;
                }
                case "2" -> {
                    IO.println("Пользователю %s была назначена роль '%s' - 01.01.2000 - Администратором _".formatted(currentUser.getUsername(), currentUser.getRole()));
                    return;
                }
                case "3" -> {
                    IO.println("Установленных модификаций: %d".formatted(currentUser.getInstalledMods().size()));
                    return;
                }
                case "4" -> {
                    IO.println("Лайков: %d".formatted(currentUser.getLikedModIds().size()));
                    return;
                }
                case "5" -> {
                    IO.println("Тема: %s".formatted(currentUser.getCurrentTheme()));
                    return;
                }
                case "6" -> {
                    IO.println("Обратитесь к администратору");
                    return;
                }
                case "7" -> {
                    IO.println("Подключение произошло в %s".formatted(currentUser.getRegistrationDate()));
                    return;
                }
                case "8" -> {
                    IO.println("ID в системе: %d".formatted(currentUser.getId()));
                    return;
                }

                default -> IO.println("Неверный выбор");
            }
        } catch (Exception e) {
            LogService.error("Ошибка при выполнении операции: ", e);
            IO.println("Произошла ошибка: " + e.getMessage());
        }
    }



}


//
//
//private void changeOwnPassword() {
//    String password = IO.readln("Новый пароль: ");
//    if (userService.changePassword(currentUser.getUsername(), password)) {
//        IO.println("Пароль изменен.");
//    }
//}