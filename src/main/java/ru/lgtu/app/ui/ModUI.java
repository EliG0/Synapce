package ru.lgtu.app.ui;

import ru.lgtu.app.AppContext;
import ru.lgtu.app.model.Mod;
import ru.lgtu.app.model.User;
import ru.lgtu.app.service.InstalledModService;
import ru.lgtu.app.service.LogService;
import ru.lgtu.app.service.ModService;
import ru.lgtu.app.service.UserService;

public class ModUI {

    private final User currentUser;
    private final Mod currentMod;
    private ModService modService;

    public ModUI(AppContext ctx, User user, int modId) {
        this.currentUser = user;
        this.currentMod = ctx.modService.getMod(modId).orElse(null);
    }

    public ModUI(AppContext ctx, User user, Mod mod) {
        this.currentUser = user;
        this.currentMod = mod;
    }


    public void start() {
        while (true) {
            showMenu();
            String choice = IO.readln();
            processChoice(choice);
        }
    }

    private void showMenu() {
        int width = 50;
        boolean isInstalled = currentUser.isModInstalled(currentMod.getId());
        boolean isEndorsement = currentUser.isModEndorsement(currentMod.getId());
        boolean isOwner = currentMod.getAuthor().equals(currentUser.getUsername());

        String menuHeader = currentMod.viewFull();

        String menuDownload = """
                │ %d ─ %s
                """.formatted(1, isInstalled ? "Удалить" : "Установить");

        String menuLike = """
                │ %d ─ %s
                """.formatted(2, isEndorsement ? "Убрать поддержку" : "Поддержать");

        String MenuOwner = """
                │ %d ─ Изменить модификацию
                │ %d ─ Удалить модификацию
                """.formatted(3, 4);

        String MenuBottom = """
                │ 0 ─ В главное меню
                ┝━━━━━━━━━━━━━━╾☽【❖】☾╾─┈┄╌
                ╰➤ㅤ""";

        String result = menuHeader;
        result += menuDownload;
        result += menuLike;
        result += currentUser.isAdmin() ? MenuOwner : "";
        result += MenuBottom;

        IO.print(result);
    }

    private void processChoice(String choice) {
        boolean userIsGuest = currentUser.isGuest();
        boolean userIsAdmin = currentUser.isAdmin();
        boolean userIsModer = currentUser.isModer();

        try {
            switch (choice) {
                case "1" -> {

                    return;
                }
                case "2" -> {

                    return;
                }
                case "3" -> {

                    return;
                }
                case "4" -> {

                    return;
                }
                case "5" -> {

                    return;
                }
                case "6" -> {
                    return;
                }
                case "7" -> {
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


}
