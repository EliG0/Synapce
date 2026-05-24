package ru.lgtu.app.ui;

import ru.lgtu.app.AppContext;
import ru.lgtu.app.model.User;
import ru.lgtu.app.service.*;

import java.util.Optional;

public class MainUI {

    private User currentUser;
    private final AppContext ctx;

    public MainUI(AppContext ctx, User user) {
        this.ctx = ctx;
        this.currentUser = user;
    }

    public void start() {
        boolean running = true;
        while (running) {
            showMenu();
            String choice = IO.readln();

            if ("2".equals(choice) && currentUser.isGuest()) {
                User newUser = viewLogin();
                if (newUser != null)
                    this.currentUser = newUser;

            } else if ("0".equals(choice)) {
                running = false;
            } else {
                processChoice(choice);
            }
        }
    }

    private void showMenu() {
        String mainMenuHeader = """
                \n┍━━━━━━━━━━━━━━━»•» 🌺  ГЛАВНОЕ МЕНЮ
                """;

        String mainMenuCommon = """
                │ %d ─ Глобальный каталог модов (Nexus)
                """.formatted(1);

        String mainMenuGuest = """
                │ %d ─ Войти
                │ %d ─ Зарегистрироваться
                │ %d ─ Восстановить пароль
                """.formatted(2, 3, 4);

        String mainMenuUser = """
                │ %d ─ Мод органайзер (Vortex)
                │ %d ─ Мой профиль
                """.formatted(2, 3);

        String mainMenuAdmin = """
                │ %d ─ Панель Администратора
                """.formatted(4);

        String mainMenuBottom = """
                │ 0 ─ Выход
                ├─────»•» 🌺
                ╰➤ㅤ""";

        String result = mainMenuHeader;
        result += mainMenuCommon;
        result += currentUser.isGuest() ? mainMenuGuest : mainMenuUser;
        result += currentUser.isAdmin() ? mainMenuAdmin : "";
        result += mainMenuBottom;

        IO.print(result);
    }

    private void processChoice(String choice) {
        boolean userIsGuest = currentUser.isGuest();
        boolean userIsAdmin = currentUser.isAdmin();

        try {
            switch (choice) {
                case "1" -> viewNexus();
                case "2" -> {
                    if (userIsGuest) {
                        viewLogin();
                    } else {
                        viewVortex();
                    }
                }
                case "3" -> {
                    if (userIsGuest) {
                        viewRegistration();
                    } else {
                        viewProfile();
                    }
                }
                case "4" -> {
                    if (userIsGuest) {
                        viewPasswordRecovery();
                    } else if (userIsAdmin) {
                        viewAdminPanel();
                    }
                }
                case "0" -> System.exit(0);
                default -> IO.println("Неверный выбор");
            }
        } catch (Exception e) {
            LogService.error("Ошибка при выполнении операции: ", e);
            IO.println("Произошла ошибка: " + e.getMessage());
        }
    }

    private void viewRegistration() {
        LogService.userAction(currentUser.getUsername(), "открыл окно регистрации");
        AuthUI authUI = new AuthUI(ctx);
        authUI.registration();
    }

    private User viewLogin() {
        LogService.userAction(currentUser.getUsername(), "открыл окно авторизации");
        AuthUI authUI = new AuthUI(ctx);
        Optional<User> loggedInUser = authUI.login();
        return loggedInUser.orElse(null);
    }

    private void viewAdminPanel() {
        LogService.userAction(currentUser.getUsername(),"зашел в админку");
        AdminUI adminUI = new AdminUI(ctx, currentUser);
        if (currentUser.isAdmin()) adminUI.start();// Перепроверка админности, на всякий
    }

    private void viewPasswordRecovery() {
        LogService.userAction(currentUser.getUsername(), "открыл окно восстановления пароля");
        IO.println("Для восстановления пароля братитесь к администратору!");
    }

    private void viewProfile() {
        LogService.userAction(currentUser.getUsername(), "открыл свой профиль");
        ProfileUI profileUI = new ProfileUI(ctx, currentUser);
        profileUI.start();
    }

    private void viewNexus() {
        LogService.userAction(currentUser.getUsername(), "открыл Nexus");
        NexusUI nexusUI = new NexusUI(ctx, currentUser);
        nexusUI.start();
    }

    private void viewVortex() {
        LogService.userAction(currentUser.getUsername(), "открыл Vortex");
        VortexUI vortexUI = new VortexUI(ctx, currentUser);
        vortexUI.start();
    }
}