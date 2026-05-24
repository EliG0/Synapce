package ru.lgtu.app;

import ru.lgtu.app.service.*;


public class AppContext {

    // === Инфраструктура ===
    public final ConfigService configService;
    public final FileService fileService;
    public final InputService inputService;
    public final ImageService imageService;

    // === Бизнес-сервисы ===
    public final AuthService authService;
    public final UserService userService;
    public final GameService gameService;
    public final ModService modService;
    public final InstalledModService installedModService;
    public final BootService bootService;
//    public final ReportService reportService;

    public AppContext() {
        this.configService = new ConfigService();
        this.fileService = new FileService();
        this.inputService = new InputService();
        this.imageService = new ImageService();

        this.userService = new UserService(this.fileService, this.configService, this.inputService, this.imageService);
        this.authService = new AuthService(this.fileService, this.configService, this.inputService, this.imageService, this.userService);
        this.gameService = new GameService(this.fileService, this.configService, this.inputService, this.imageService);
        this.modService = new ModService(this.fileService, this.configService, this.inputService, this.imageService);
        this.installedModService = new InstalledModService(this.fileService, this.configService, this.inputService, this.imageService);

        this.bootService = new BootService(this.fileService, this.configService, this.inputService, this.imageService);
//        this.reportService = new ReportService(this.fileService, this.configService);
    }
}