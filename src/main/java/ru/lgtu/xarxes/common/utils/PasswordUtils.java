package ru.lgtu.xarxes.common.utils;

import org.mindrot.jbcrypt.BCrypt;
/**
 * Работа с паролями
 */
public class PasswordUtils {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }

}
