package ru.lgtu.xarxes.common.service;

import ru.lgtu.xarxes.common.model.Role;
import ru.lgtu.xarxes.common.model.User;

public class SessionService {

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isAuthorized() {
        return currentUser != null;
    }

    public boolean isAdmin() {
        return currentUser.getRole() == Role.ADMIN;
    }

    public void login(User user) {
        currentUser = user;
    }

    public void logout() {
        currentUser = null;
    }
}