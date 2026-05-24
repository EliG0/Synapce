package ru.lgtu.app.ui;

import ru.lgtu.app.AppContext;
import ru.lgtu.app.model.User;
import ru.lgtu.app.service.*;

import java.util.Optional;

public class AuthUI {
    private final AuthService authService;

    public AuthUI(AppContext ctx) {
        this.authService = ctx.authService;
    }

    public Optional<User> login() {
        String menuHeader = """
            \n┍━━━━━━━━━━━━━━━»•» ОКНО АВТОРИЗАЦИИ
            """;

        String menuInput = """
            ├─────»•»
            ╰➤ㅤ""";

        String menuLoginPrompt = """
            │ Введите логин (либо 0 ─ Выход)
            """;

        String enteredLogin = InputService.inputFieldNew(
                "Guest",
                (menuHeader + menuLoginPrompt + menuInput),
                s -> s
        );
        if ("0".equals(enteredLogin))
            return Optional.empty();

        menuHeader += """
            │ Логин: %s
            """.formatted(enteredLogin);

        String menuPassPrompt = """
            │ Введите пароль (либо 0 ─ Выход)
            """;

        String enteredPass = InputService.inputFieldNew(
                "Guest",
                (menuHeader + menuPassPrompt + menuInput),
                s -> s
        );

        if ("0".equals(enteredPass))
            return Optional.empty();


        menuHeader += """
            │ Пароль: %s
            ├─────────────────────»•»
            """.formatted("*".repeat(enteredPass.length()));

        IO.print(menuHeader);

        Optional<User> user = authService.login(enteredLogin, enteredPass);

        if (user.isPresent()) {
            IO.println("│ 🔓 Добро пожаловать, %s!\n╰───────────────────────────»•»".formatted(enteredLogin));
        } else {
            IO.println("│ ❌ Неверный логин или пароль. Попробуйте снова.\n╰───────────────────────────»•»");
        }
        return user;
    }

    public void registration() {
        String menuHeader = """
            \n┍━━━━━━━━━━━━━━━»•» ОКНО РЕГИСТРАЦИИ
            """;

        String menuInput = """
            ├─────»•»
            ╰➤ㅤ""";

        String menuLoginPrompt = """
            │ Введите новый логин (либо 0 ─ Выход)
            """;

        String enteredLogin = InputService.inputFieldNew(
                "Guest",
                (menuHeader + menuLoginPrompt + menuInput),
                s -> s
        );

        if ("0".equals(enteredLogin))
            return;

        if (authService.isUserExist(enteredLogin)){
            IO.print("Уже существует");
        }

        menuHeader += """
            │ Логин: %s
            """.formatted(enteredLogin);

        String menuPassPrompt = """
            │ Введите пароль (либо 0 ─ Выход)
            """;

        String enteredPass = InputService.inputFieldNew(
                "Guest",
                (menuHeader + menuPassPrompt + menuInput),
                s -> s
        );

        if ("0".equals(enteredPass))
            return;

        menuHeader += """
            │ Пароль: %s
            ├─────────────────────»•»
            """.formatted("*".repeat(enteredPass.length()));

        IO.print(menuHeader);
        boolean isRegistered = authService.registration(enteredLogin, enteredPass);
        if (isRegistered)
            IO.println("│ ✅ Регистрация успешна! Теперь вы можете войти с этими данными.\n╰───────────────────────────»•»");
        else
            IO.println("│ ❌ Не удалось зарегистрироваться. Попробуйте снова.\n╰───────────────────────────»•»");
    }
}
