package ru.lgtu.xarxes.common.utils;
/**
 * Валидация данных (логин, пароль и т.д.)
 */
public class ValidatorUtils {

    public static boolean isValidLogin(String login) {
        if (login == null) return false;
        return login.matches("^[a-zA-Z0-9]{4,20}$");
    }

    public static boolean isValidPassword(String password) {
        if (password == null) return false;
        // Минимум 6 символов, одна цифра, одна заглавная буква
        return password.length() >= 6 &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[A-Z].*");
    }
}