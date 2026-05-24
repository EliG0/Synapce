package ru.lgtu.app;
import ru.lgtu.app.model.User;
import ru.lgtu.app.ui.MainUI;

public class Main { // ㅤ
    public static void main(String[] args) {
        IO.println(" ╭─╮╷ ╷╭╮╷╭─╮╭─╮╭─╴╭─╴"+
                "\n ╰─╮╰┬╯│╰┤├─┤├─╯│  ├╴ "+
                "\n ╰─╯ ╵ ╵ ╵╵ ╵╵  ╰─╴╰─╴");
        AppContext ctx = new AppContext();
        ctx.bootService.initSystem();
        User guest = ctx.authService.loginAsGuest();
        MainUI ui = new MainUI(ctx, guest);
        ui.start();
    }
}

//    ╭─╮╷ ╷╭╮╷╭─╮╭─╮╭─╴╭─╴
//    ╰─╮╰┬╯│╰┤├─┤├─╯│  ├╴
//    ╰─╯ ╵ ╵ ╵╵ ╵╵  ╰─╴╰─╴
//    ╷ ╷╭─╮╭─╮╶┬╴╭─╴╷ ╷
//    │╭╯│ │├┬╯ │ ├╴ ╭┼╯
//    ╰╯ ╰─╯╵╰╴ ╵ ╰─╴╵ ╵
//    ╭╮╷╭─╴╷ ╷╷ ╷╭─╮
//    │╰┤├╴ ╭┼╯│ │╰─╮
//    ╵ ╵╰─╴╵ ╵╰─╯╰─╯