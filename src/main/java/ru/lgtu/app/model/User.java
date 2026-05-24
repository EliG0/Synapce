package ru.lgtu.app.model;

import org.mindrot.jbcrypt.BCrypt;
import ru.lgtu.app.service.LogService;
import ru.lgtu.app.ui.Theme;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
    private int id;
    private String username;
    private Role role;
    private String passwordHash;
    private LocalDateTime registrationDate;
    private List<InstalledMod> installedMods = new ArrayList<>();
    private List<Integer> likedModIds = new ArrayList<>();
    private Theme currentTheme = Theme.CLASSIC; // дефолтная тема


    public User() {

    }

    public User(int id, String username, Role role, String passwordHash, LocalDateTime registrationDate, List<InstalledMod> installedMods, List<Integer> likedModIds, Theme currentTheme) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.passwordHash = passwordHash;
        this.registrationDate = registrationDate;
        this.installedMods = installedMods;
        this.likedModIds = likedModIds;
        this.currentTheme = currentTheme;
    }

    public Theme getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(Theme theme) {
        this.currentTheme = theme;
    }

    public static User createNew(int id, String username, String passwordHash) {
        return new User(
                id,
                username,
                Role.USER,
                passwordHash,
                LocalDateTime.now(),
                new ArrayList<>(),
                new ArrayList<>(),
                Theme.CLASSIC
        );
    }

    public static User createNew(int id, String username, String passwordHash, Role role) {
        return new User(
                id,
                username,
                role,
                passwordHash,
                LocalDateTime.now(),
                new ArrayList<>(),
                new ArrayList<>(),
                Theme.CLASSIC
        );
    }

    public static User createGuest() {
        return User.createNew(
                0,
                "Гость",
                "",
                Role.GUEST
                );
    }

    public static User createAdmin() {
        return User.createNew(
                1,
                "admin",
                BCrypt.hashpw("admin", BCrypt.gensalt()),
                Role.ADMIN
        );
    }

    public boolean isGuest() {
        return role == Role.GUEST;
    }

    public boolean isModer() {
        return role == Role.MODERATOR;
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public List<InstalledMod> getInstalledMods() {
        return installedMods;
    }

    public List<Integer> getLikedModIds() {
        return likedModIds;
    }

    public List<Integer> getUploadMods() {
        return new ArrayList<>();
    }

    public boolean isModInstalled(int modId) {
        return installedMods.stream()
                .anyMatch(mod -> mod.getModId() == modId);
    }

    public boolean isModEndorsement(int modId) {
        return likedModIds.stream()
                .anyMatch(like -> like == modId);
    }


    void setUsername(String username) {
        if (username == null || username.length() < 3) {
            throw new IllegalArgumentException("Имя пользователя слишком короткое");
            //  лог:
        }
        this.username = username;
        //  лог:
    }

    void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        //  лог:
    }


    void setRole(Role role) {
        this.role = role;
        //  лог:
    }

    public void installMod(InstalledMod mod) {
        if (!installedMods.contains(mod)) {
            installedMods.add(mod);
            //  лог: "Пользователь установил мод..."
        }
    }

    public void uninstallMod(int modId) {
        installedMods.removeIf(m -> m.getModId() == modId);
        //  лог:
    }

    public void likeMod(int modId) {
        if (!likedModIds.contains(modId)) {
            likedModIds.add(modId);
            //  лог:
        }
        //  лог:
    }

    public void unlikeMod(int modId) {
        likedModIds.remove(Integer.valueOf(modId));
        //  лог:
    }

    public boolean checkPassword(String candidatePassword) {
        return BCrypt.checkpw(candidatePassword, this.passwordHash);

    }

    public User withPassword(String newPasswordHash) {
        return new User(
                this.id,
                this.username,
                this.role,
                newPasswordHash, // Меняем только хэш
                this.registrationDate,
                this.installedMods,
                this.likedModIds,
                this.currentTheme
        );
    }

    @Override
    public String toString() {
        return username;
    }
}
