package ru.lgtu.xarxes.oghma.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * Логика работы с установленными модами

 */
public class InstalledModService {
//    private final FileService fileService;
//    private final String usersFilePath;
//
//    public InstalledModService(FileService fileService, ConfigService configService, InputService inputService, ImageUtils imageUtils) {
//        this.fileService = fileService;
//        this.usersFilePath = configService.getUsersJsonPath();
//    }
//
//
//    public boolean isModInstalled(String username, int modId) {
//        try {
//            List<User> users = fileService.loadList(usersFilePath, User[].class);
//
//            for (User user : users) {
//                if (user.getUsername().equalsIgnoreCase(username)) {
//                    return user.isModInstalled(modId);
//                }
//            }
//            return false;
//        }
//        catch (Exception e) {
//            LogService.error("Системная ошибка: невозможно прочитать базу пользователей! {}", e);
//            return false;
//        }
//    }
//
//
//    public List<InstalledMod> getInstalledMods(String username) {
//        try {
//            List<User> users = fileService.loadList(usersFilePath, User[].class);
//
//            for (User user : users) {
//                if (user.getUsername().equalsIgnoreCase(username)) {
//                    List<InstalledMod> mods = user.getInstalledMods();
//                    if (mods == null) return new ArrayList<>();
//
//                    mods.sort(Comparator.comparingInt(InstalledMod::getPriority));
//                    return mods;
//                }
//            }
//            return new ArrayList<>();
//        }
//        catch (Exception e) {
//            LogService.error("Системная ошибка: невозможно прочитать базу пользователей! {}", e);
//            return new ArrayList<>();
//        }
//    }
//
//    public boolean installMod(String username, int modId) {
//        try {
//            List<User> users = fileService.loadList(usersFilePath, User[].class);
//            boolean updated = false;
//
//            for (User user : users) {
//                if (user.getUsername().equalsIgnoreCase(username)) {
//                    // Вычисляем следующий приоритет (максимальный + 1), чтобы мод встал в конец очереди
//                    int nextPriority = user.getInstalledMods().stream()
//                            .mapToInt(InstalledMod::getPriority)
//                            .max()
//                            .orElse(0) + 1;
//
//                    // Создаем новый установленный мод
//                    // Предполагаем конструктор: InstalledMod(modId, priority, enabled)
//                    InstalledMod newInstalledMod = new InstalledMod(modId, nextPriority);
//
//                    // Используем твой публичный метод из доменной модели User!
//                    user.installMod(newInstalledMod);
//                    updated = true;
//                    break;
//                }
//            }
//
//            if (updated) {
//                fileService.saveList(usersFilePath, users);
//                LogService.info("Пользователь {} установил мод с ID {}", username, modId);
//                return true;
//            }
//            return false;
//        }
//        catch (Exception e) {
//            LogService.error("Системная ошибка: невозможно прочитать базу пользователей! {}", e);
//            return false;
//        }
//    }

//
//    public boolean uninstallMod(String username, int modId) {
//
//        List<User> users = fileService.loadList(usersFilePath, User[].class);
//        boolean updated = false;
//
//        for (User user : users) {
//            if (user.getUsername().equalsIgnoreCase(username)) {
//                // Используем твой метод из модели User
//                user.uninstallMod(modId);
//                updated = true;
//                break;
//            }
//        }
//
//        if (updated) {
//            fileService.saveList(usersFilePath, users);
//            LogService.info("Пользователь {} удалил мод с ID {}", username, modId);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean toggleModStatus(String username, int modId) {
//        List<User> users = fileService.loadList(usersFilePath, User[].class);
//        boolean updated = false;
//
//        for (User user : users) {
//            if (user.getUsername().equalsIgnoreCase(username)) {
//                for (InstalledMod mod : user.getInstalledMods()) {
//                    if (mod.getModId() == modId) {
//                        // Меняем статус на противоположный
//                        mod.setEnabled(!mod.isEnabled());
//                        updated = true;
//                        break;
//                    }
//                }
//            }
//        }
//
//        if (updated) {
//            fileService.saveList(usersFilePath, users);
//            LogService.info("Пользователь {} изменил статус активности мода ID {}", username, modId);
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * Изменение приоритета загрузки (Load Order).
//     */
//    public boolean changeModPriority(String username, int modId, int newPriority) {
//        List<User> users = fileService.loadList(usersFilePath, User[].class);
//        boolean updated = false;
//
//        for (User user : users) {
//            if (user.getUsername().equalsIgnoreCase(username)) {
//                for (InstalledMod mod : user.getInstalledMods()) {
//                    if (mod.getModId() == modId) {
//                        mod.setPriority(newPriority);
//                        updated = true;
//                        break;
//                    }
//                }
//            }
//        }
//
//        if (updated) {
//            fileService.saveList(usersFilePath, users);
//            LogService.info("Пользователь {} изменил приоритет мода ID {} на {}", username, modId, newPriority);
//            return true;
//        }
//        return false;
//    }
}