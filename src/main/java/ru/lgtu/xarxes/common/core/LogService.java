package ru.lgtu.xarxes.common.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Логирование (системные события и аудит действий пользователя).
 */
public class LogService {
    private static final Logger systemLogger = LoggerFactory.getLogger("SYSTEM");
    private static final Logger auditLogger = LoggerFactory.getLogger("AUDIT");

    private LogService() {
        throw new IllegalStateException("Utility class");
    }

    public static void info(String message) {
        systemLogger.info(message);
    }

    public static void info(String message, Object... args) {
        systemLogger.info(message, args);
    }

    public static void warn(String message) {
        systemLogger.warn(message);
    }

    public static void warn(String message, Object... args) {
        systemLogger.warn(message, args);
    }

    public static void error(String message) {
        systemLogger.error(message);
    }

    public static void error(String message, String object, Throwable throwable) {
        systemLogger.error(message, object, throwable);
    }

    public static void error(String message, Throwable throwable) {
        systemLogger.error(message, throwable);
    }

    public static void userAction(String username, String action) {
        auditLogger.info("User '{}': {}", username, action);
    }
}