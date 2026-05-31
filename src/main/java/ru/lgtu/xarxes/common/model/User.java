package ru.lgtu.xarxes.common.model;

import org.mindrot.jbcrypt.BCrypt;
import ru.lgtu.xarxes.oghma.model.InstalledMod;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User{
    private int id;
    private String username;
    private String passwordHash;
    private Role role;
    private LocalDateTime registrationDate;
    private List<Integer> installedMods = new ArrayList<>();
    private List<Integer> endorsementModIds = new ArrayList<>();
    private List<Integer> favoriteModIds = new ArrayList<>();
    private List<Integer> uploadedModIds = new ArrayList<>();
    private Theme currentTheme = Theme.CLASSIC;

    public User() {

    }

    public User(int id, String username, String passwordHash, Role role, LocalDateTime registrationDate, List<Integer> installedMods, List<Integer> endorsementModIds, List<Integer> favoriteModIds, List<Integer> uploadedModIds, Theme currentTheme) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.registrationDate = registrationDate;
        this.installedMods = installedMods;
        this.endorsementModIds = endorsementModIds;
        this.favoriteModIds = favoriteModIds;
        this.uploadedModIds = uploadedModIds;
        this.currentTheme = currentTheme;
    }

    public static User createNew(int id, String username, String passwordHash) {
        return new User(
                id,
                username,
                passwordHash,
                Role.USER,
                LocalDateTime.now(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                Theme.CLASSIC
        );
    }

    public static User createAdmin() {
        return new User(
                1,
                "admin",
                BCrypt.hashpw("admin", BCrypt.gensalt()),
                Role.ADMIN,
                LocalDateTime.now(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                Theme.CLASSIC
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Integer> getInstalledMods() {
        return installedMods;
    }

    public void setInstalledMods(List<Integer> installedMods) {
        this.installedMods = installedMods;
    }

    public List<Integer> getEndorsementModIds() {
        return endorsementModIds;
    }

    public void setEndorsementModIds(List<Integer> endorsementModIds) {
        this.endorsementModIds = endorsementModIds;
    }

    public List<Integer> getFavoriteModIds() {
        return favoriteModIds;
    }

    public void setFavoriteModIds(List<Integer> favoriteModIds) {
        this.favoriteModIds = favoriteModIds;
    }

    public List<Integer> getUploadedModIds() {
        return uploadedModIds;
    }

    public void setUploadedModIds(List<Integer> uploadedModIds) {
        this.uploadedModIds = uploadedModIds;
    }

    public Theme getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(Theme currentTheme) {
        this.currentTheme = currentTheme;
    }

    @Override
    public String toString() {
        return username;
    }
}
