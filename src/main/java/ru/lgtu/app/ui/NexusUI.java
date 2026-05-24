package ru.lgtu.app.ui;

import ru.lgtu.app.AppContext;
import ru.lgtu.app.model.Game;
import ru.lgtu.app.model.Mod;
import ru.lgtu.app.model.ModSummary;
import ru.lgtu.app.model.User;
import ru.lgtu.app.service.*;

import java.util.List;
import java.util.Optional;
import java.util.Comparator;

public class NexusUI {

    private final User currentUser;
    private final AppContext ctx;

    public NexusUI(AppContext ctx, User user) {
        this.ctx = ctx;
        this.currentUser = user;
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
                \n ‿︵‿︵୨˚̣̣̣͙୧ - NEXUS -ˊ୨˚̣̣̣͙୧‿︵‿ˎ
                """;

        String MenuCommon = """
                │ %d ─ Топ 9 популярных модификаций
                │ %d ─ Поиск
                """.formatted(1, 2);

        String MenuModer = """
                │ %d ─ Добавить модификации
                │ %d ─ Мои модификации
                """.formatted(3, 4);

        String MenuAdmin = """
                │ %d ─ Изменить модификацию
                │ %d ─ Удалить модификацию
                """.formatted(5, 6);

        String MenuUser = """
                │ %d ─ Мой профиль
                """.formatted(9);

        String MenuBottom = """
                ├─────────────────────»•»
                │ 0 ─ В главное меню
                ├─────»•» 🌺
                ╰➤ㅤ""";

        String result = MenuHeader;
        result += MenuCommon;
        result += (currentUser.isModer() || currentUser.isAdmin()) ? MenuModer : "";
        result += currentUser.isAdmin() ? MenuAdmin : "";
        result += currentUser.isGuest() ? "" : MenuUser;
        result += MenuBottom;

        IO.print(result);
    }

    private void processChoice(String choice) {
        boolean userIsGuest = currentUser.isGuest();
        boolean userIsAdmin = currentUser.isAdmin();
        boolean userIsModer = currentUser.isModer();

        try {
            switch (choice) {
                case "1" -> { // ALL
                    viewTopNineModsMenu();
                }
                case "2" -> { // ALL
                    viewSearchModMenu();
                }
                case "3" -> { // MODER
                    if (currentUser.isModer() && currentUser.isAdmin())
                        viewAddModMenu();
                }
                case "4" -> { // MODER
                    if (currentUser.isModer() && currentUser.isAdmin())
                        viewMyModsMenu();
                }
                case "5" -> { // ADMIN
                    if (currentUser.isAdmin())
                        viewEditModMenu();
                }
                case "6" -> { // ADMIN
                    if (currentUser.isAdmin())
                        viewDeleteModMenu();
                }
                case "9" -> { // ADMIN
                    if (currentUser.isAdmin())
                        viewProfileMenu();
                }
                case "0" -> { // ALL
                    return;
                }
                default -> IO.println("Неверный выбор");
            }
        } catch (Exception e) {
            LogService.error("Ошибка при выполнении операции: ", e);
            IO.println("Произошла ошибка: " + e.getMessage());
        }
    }

    private void viewTopNineModsMenu() {
        List<ModSummary> allMods;
        try {
            allMods = ctx.modService.getModsSummaryByName("");
        } catch (Exception e) {
            allMods = List.of();
        }

        allMods.sort(Comparator.comparingInt(ModSummary::getDownloads).reversed());
        List<ModSummary> topMods = allMods.size() > 9 ? allMods.subList(0, 9) : allMods;

        if (topMods.isEmpty()) {
            IO.println("Нет доступных модификаций.");
            return;
        }

        IO.println("Топ 9 популярных модификаций:");
        for (int i = 0; i < topMods.size(); i++) {
            ModSummary mod = topMods.get(i);
            IO.println("%d. %s | Автор: %s | Игра: %s | Установок: %d"
                    .formatted(i + 1, mod.getName(), mod.getAuthor(), mod.getGameName(), mod.getDownloads()));
        }

        while (true) {
            int choice = ctx.inputService.inputFieldNew(currentUser.getUsername(),
                    "Введите номер мода для просмотра (0 — выход): ", Integer::parseInt);

            if (choice == 0) {
                return;
            }

            if (choice >= 1 && choice <= topMods.size()) {
                int modId = topMods.get(choice - 1).getId();
                Optional<Mod> modOpt = ctx.modService.getMod(modId);

                if (modOpt.isPresent()) {
                    ModUI modUI = new ModUI(ctx, currentUser, modOpt.get());
                    modUI.start();
                } else {
                    IO.println("Ошибка загрузки модификации. Обратитесь к администратору.");
                }
                return;
            } else {
                IO.println("Неверный выбор!");
            }
        }
    }

    private void viewSearchModMenu() {
        Mod chosenMod = null;

        int choice = ctx.inputService.inputFieldNew(currentUser.getUsername(),"По чему будем искать мод? (1 - ID, 2 - названию, 3 - автору, 4 - игре)\n-> ", Integer::parseInt);

        switch (choice){
            case 1 -> { // id
                int enteredId = ctx.inputService.inputFieldNew(currentUser.getUsername(), "Введите ID игры: ", Integer::parseInt);
                Optional<Mod> modOpt = ctx.modService.getMod(enteredId);
                if (modOpt.isPresent()) {
                    chosenMod = modOpt.get();
                    IO.println("Найдена модификация: " + chosenMod.getName());
                } else {
                    IO.println("Модификация с ID " + enteredId + " не найдена.");
                }
            }
            case 2 -> { // name
                String enteredName = ctx.inputService.inputFieldNew(currentUser.getUsername(), "Введите название мода:", s -> s);
                List<ModSummary> mods = ctx.modService.getModsSummaryByName(enteredName);

                if (!mods.isEmpty()) {
                    Optional<Mod> choseModOpt = choseModFromList(mods);
                    if (choseModOpt.isPresent()) {
                        chosenMod = choseModOpt.get();
                    }
                } else {
                    IO.println("Модификаций с названием " + enteredName + " не найдено.");
                }
            }
            case 3 -> { // author
                String enteredAuthor = ctx.inputService.inputFieldNew(currentUser.getUsername(), "Введите Автора:", s -> s);
                List<ModSummary> mods = ctx.modService.getModsSummaryByAuthor(enteredAuthor);

                if (!mods.isEmpty()) {
                    Optional<Mod> choseModOpt = choseModFromList(mods);
                    if (choseModOpt.isPresent()) {
                        chosenMod = choseModOpt.get();
                    }
                } else {
                    IO.println("Модификаций с автором " + enteredAuthor + " не найдено.");
                }

            }
            case 4 -> { // game
                String enteredGame = ctx.inputService.inputFieldNew(currentUser.getUsername(), "Введите игру для поиска:", s -> s);
                List<Game> games = ctx.gameService.searchGame(enteredGame);

                if (!games.isEmpty()){
                    IO.println("Найден%s %d игр%s: ".formatted(games.size() == 1 ? "а" : "ы", games.size(), games.size() == 1 ? "а" : games.size() < 5 ? "ы" : "" ));
                    for (int i = 0; i < games.size(); i++) {
                        Game game = games.get(i);
                        IO.println("%d. %s".formatted((i + 1), game.getName()));
                    }

                    while (true) {
                        int choiceGame = ctx.inputService.inputFieldNew(currentUser.getUsername(), "Введите номер для просмотра модов этой игры (0 - для выхода): ", Integer::parseInt);
                        if ("0".equals(String.valueOf(choiceGame))){
                            return;
                        }
                        if (games.size() >= choiceGame && choiceGame > 0) {
                            int gameId = games.get(choiceGame - 1).getId();
                            List<ModSummary> mods = ctx.modService.getModsSummaryByGame(gameId);

                            if (!mods.isEmpty()) {
                                Optional<Mod> choseModOpt = choseModFromList(mods);
                                if (choseModOpt.isPresent()) {
                                    chosenMod = choseModOpt.get();
                                    return;
                                }
                            } else {
                                IO.println("Модификаций для игры " + games.get(choiceGame - 1).getName() + " не найдено.");
                            }
                        } else {
                            IO.println("Неверный выбор!");
                        }
                    }

                } else {
                    IO.println("Игр с названием " + enteredGame + " не найдено.");;
                }
            }
        }
        if (chosenMod != null) {
            IO.print("Выбран: %s".formatted(chosenMod.getName()));
            ModUI modUI = new ModUI(ctx, currentUser, chosenMod);
            modUI.start();
        }

    }

    private Optional<Mod> choseModFromList(List<ModSummary> mods){
        IO.println("Найден%s %d модификаци%s: ".formatted(mods.size() == 1 ? "а" : "ы", mods.size(), mods.size() == 1 ? "я" : mods.size() < 5 ? "и" : "й"));
        for (int i = 0; i < mods.size(); i++) {
            ModSummary mod = mods.get(i);
            IO.println("%d. %s | Автор: %s | Игра: %s".formatted((i + 1), mod.getName(), mod.getAuthor(), mod.getGameName()));
        }
        while (true) {
            int choiceMod = ctx.inputService.inputFieldNew(currentUser.getUsername(), "Введите номер для просмотра желаемого мода (0 - для выхода): ", Integer::parseInt);
            if ("0".equals(String.valueOf(choiceMod))){
                return Optional.empty();
            }
            if (mods.size() >= choiceMod && choiceMod > 0) {
                int modId = mods.get(choiceMod - 1).getId();
                Optional<Mod> modOpt = ctx.modService.getMod(modId);
                if (modOpt.isPresent()) {
                    return modOpt;
                } else {
                    IO.println("Модификация с ID " + modId + " не найдена. Обратитесь к администратору");
                    return Optional.empty();
                }
            } else {
                IO.println("Неверный выбор!");
            }
        }
    }


    private void viewProfileMenu() {
        ProfileUI profileUI = new ProfileUI(ctx, currentUser);
        profileUI.start();
    }


    private void viewMyModsMenu() {
        IO.println("Будет добавлено позднее");
    }

    private void viewAddModMenu() {
        IO.println("Будет добавлено позднее");
    }


    private void viewDeleteModMenu() {
        IO.println("Будет добавлено позднее");
    }

    private void viewEditModMenu() {
        IO.println("Будет добавлено позднее");
    }

//    private void installMod() {
//        int modId = InputService.inputField(currentUser.getUsername(), "ID мода для установки", Integer::parseInt);
//        if (installedModService.installMod(currentUser.getUsername(), modId)) {
//            IO.println("Мод успешно добавлен в ваш менеджер!");
//        } else {
//            IO.println("Не удалось установить мод.");
//        }
//    }
}
