package ru.lgtu.app.ui;

import ru.lgtu.app.AppContext;
import ru.lgtu.app.model.User;
import ru.lgtu.app.service.*;

public class AdminUI {
    private final User currentUser;
    private final UserService userService;
    private final ModService modService;
    private final AuthService authService;

    public AdminUI(AppContext ctx, User user) {
        this.currentUser = user;
        this.authService = ctx.authService;
        this.modService = ctx.modService;
        this.userService = ctx.userService;
    }
    public void start() {
        while (true) {
            showMenu();
            String choice = IO.readln();
            if ("0".equals(choice)) {
                return;
            }
            processChoice(choice);
        }
    }

    private void showMenu() {
        String Menu = """
                \n╔═════════════《 😈 АДМИН МЕНЮ 》══════════════
                ║ %d ─ Поиск пользователя
                ║ %d ─ Добавить пользователя
                ║ %d ─ Удалить пользователя
                ║ %d ─ Изменить пароль пользователя
                ╠═════════════════《 NEXUS 》═════════════════
                ║ %d ─ Поиск мода
                ║ %d ─ Добавить мод
                ║ %d ─ Удалить мод
                ╠════════════════════《✧》════════════════════
                ║ 0 ─ Выход
                ╚➤ㅤ""".formatted(1,2,3,4,5,6,7);

        IO.print(Menu);
    }

    private void processChoice(String choice) {
        try {
            switch (choice) {
                case "1" -> {
                    viewUserSearch();
                }
                case "2" -> {
                    viewUserAdd();
                }
                case "3" -> {
                    viewUserDelete();
                }
                case "4" -> {
                    viewUserChangePassword();
                }
                case "5" -> {
                    viewNexusSearch();
                }
                case "6" -> {
                    viewNexusAdd();
                }
                case "7" -> {
                    viewNexusDelete();
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

    private void viewUserSearch() {
    }


    private void viewUserAdd() {
        String addUsername = InputService.inputField(currentUser.getUsername(), "новый логин", s -> s);

        if (userService.isUserExist(addUsername)) {
            IO.println("Пользователь с таким логином уже существует.");
            return;
        }

        String addpassword = InputService.inputField(currentUser.getUsername(), "новый пароль", s -> s);

        boolean isRegistered = authService.registration(addUsername, addpassword);

        if (isRegistered) {
            IO.println("Пользователь зарегистрирован");
        } else {
            IO.println("Не получилось зарегистрировать пользователя");
        }
    }

    private void viewUserDelete() {
        String username = IO.readln("Логин для удаления: ");

        if (userService.isUserExist(username)) {
            if (username.equals(currentUser.getUsername())) {
                IO.println("Нельзя удалить самого себя.");
                return;
            }
        } else {
            IO.println("Пользователь не найден.");
            return;
        }

        if (userService.deleteUser(username)) {
            IO.println("Пользователь удалён.");
        } else {
            IO.println("Ошибка удаления.");
        }
    }

    private void viewUserChangePassword() {
        String username = IO.readln("Логин пользователя: ");

        if (!userService.isUserExist(username)) {
            IO.println("Пользователь не найден.");
            return;
        }

        String password = IO.readln("Новый пароль: ");

        if (userService.changePassword(username, password)) {
            IO.println("Пароль изменён");
        } else {
            IO.println("Пользователь не найден.");
        }
    }

    private void viewNexusSearch(){

    }

    private void viewNexusAdd() {
        String name = InputService.inputField(currentUser.getUsername(), "Название мода", s -> s);
        String desc = InputService.inputField(currentUser.getUsername(), "Описание мода", s -> s);
        String version = InputService.inputField(currentUser.getUsername(), "Версия", s -> s);
        String author = InputService.inputField(currentUser.getUsername(), "Автор", s -> s);
        int gameId = InputService.inputField(currentUser.getUsername(), "ID поддерживаемой игры", Integer::parseInt);
        String imageBase64 = InputService.inputImage(currentUser.getUsername());

        modService.addModToCatalog(name, desc, version, author, gameId, imageBase64);
        IO.println("Мод '" + name + "' успешно добавлен в глобальный каталог Nexus.");
    }

    private void viewNexusDelete() {
        int modId = InputService.inputField(currentUser.getUsername(), "ID мода для полного удаления", Integer::parseInt);
        if (modService.deleteModFromCatalog(modId)) {
            IO.println("Мод полностью удален из каталога.");
        } else {
            IO.println("Мод не найден.");
        }
    }
}
