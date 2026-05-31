package ru.lgtu.xarxes.config;

/**
 */

public class AppContext {

    // === Инфраструктура ===
    public final ConfigService configService;


    // === Сервисы ===


    public AppContext() {
        this.configService = new ConfigService();

    }
}