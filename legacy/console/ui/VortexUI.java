package ru.lgtu.xarxes.legacy.console.ui;

import ru.lgtu.xarxes.legacy.config.AppContext;
import ru.lgtu.xarxes.legacy.common.model.User;
import ru.lgtu.xarxes.service.*;

public class VortexUI {

    private final User currentUser;
    private final UserService userService;
    private final ModService modService;
    private final InstalledModService installedModService;

    public VortexUI(AppContext ctx, User user) {
        this.currentUser = user;
        this.userService = ctx.userService;
        this.modService = ctx.modService;
        this.installedModService = ctx.installedModService;
    }

    public void start() {
        while (true) {
            showMenu();
            String choice = IO.readln();
            processChoice(choice);
        }
    }

    private void showMenu() {
        String MenuHeader = """
                ╭┈──── ◌ೄ◌ྀ ˊˎ
                │
                """;

        String MenuCommon = """
                │ %d ─ Глобальный каталог модов (Nexus)
                """.formatted(1);

        String MenuGuest = """
                │ %d ─ Войти
                │ %d ─ Зарегистрироваться
                │ %d ─ Восстановить пароль
                """.formatted(2, 3, 4);

        String MenuUser = """
                │ %d ─ Мод органайзер (Vortex)
                │ %d ─ Мой профиль
                """.formatted(2, 3);

        String MenuAdmin = """
                │ %d ─ Панель Администратора
                """.formatted(4);

        String MenuBottom = """
                │ 0 ─ Выход
                ├─────»•» 🌺
                ╰➤ 
                """;

        String result = MenuHeader;
        result += MenuCommon;
        result += currentUser.isGuest() ? MenuGuest : MenuUser;
        result += currentUser.isAdmin() ? MenuAdmin : "";
        result += MenuBottom;

        IO.print(result);
    }

    private void processChoice(String choice) {
        boolean userIsGuest = currentUser.isGuest();
        boolean userIsAdmin = currentUser.isAdmin();

        try {
            switch (choice) {
                case "1" -> {
                    return;
                }
                case "2" -> {
                    return;
                }
                case "0" -> {
                    return;
                }
                default -> IO.println("Неверный выбор");
            }
        } catch (Exception e) {
            LogService.error("Ошибка при выполнении операции: ", e);
            IO.println("Произошла ошибка: " + e.getMessage());
        }
    }

//
//        List<InstalledMod> installed = installedModService.getInstalledMods(currentUser.getUsername());
//
//        if (installed.isEmpty()) {
//            IO.println("У вас пока нет установленных модов.");
//            return;
//        }
//
//        IO.println("\n=== ВАШ СПИСОК ЗАГРУЗКИ (LOAD ORDER) ===");
//        IO.println(String.format("%-10s | %-7s | %-10s", "Приоритет", "Mod ID", "Статус"));
//        IO.println("-".repeat(35));
//        for (InstalledMod im : installed) {
//            String status = im.isEnabled() ? "[ АКТИВЕН ]" : "[ ВЫКЛЮЧЕН ]";
//            IO.println(String.format("Line %-5d | ID: %-4d | %s", im.getPriority(), im.getModId(), status));
//        }

//    private void toggleModStatus() {
//        int modId = InputService.inputField(currentUser.getUsername(), "ID мода для изменения активности", Integer::parseInt);
//        if (installedModService.toggleModStatus(currentUser.getUsername(), modId)) {
//            IO.println("Статус мода успешно изменен.");
//        } else {
//            IO.println("Мод не найден.");
//        }
//    }
//
//    private void changeModPriority() {
//        int modId = InputService.inputField(currentUser.getUsername(), "ID мода", Integer::parseInt);
//        int priority = InputService.inputField(currentUser.getUsername(), "Новый приоритет (Load Order)", Integer::parseInt);
//        if (installedModService.changeModPriority(currentUser.getUsername(), modId, priority)) {
//            IO.println("Порядок загрузки изменен.");
//        } else {
//            IO.println("Ошибка изменения приоритета.");
//        }
//    }

//    private void uninstallMod() {
//        int modId = InputService.inputField(currentUser.getUsername(), "ID мода для удаления", Integer::parseInt);
//        if (installedModService.uninstallMod(currentUser.getUsername(), modId)) {
//            IO.println("Мод удален из вашей системы.");
//        } else {
//            IO.println("Мод не найден в списке установленных.");
//        }
//    }

}
