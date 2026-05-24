package ru.lgtu.app.service;

import org.mindrot.jbcrypt.BCrypt;

public class SecureService {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
