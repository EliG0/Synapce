package ru.lgtu.app.service;

import ru.lgtu.app.model.Mod;
import ru.lgtu.app.model.ModSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ModService {
    private final FileService fileService;
    private final String modsFilePath;


    public ModService(FileService fileService, ConfigService configService, InputService inputService, ImageService imageService) {
        this.fileService = fileService;
        this.modsFilePath = configService.getModsJsonPath();
    }


    private List<Mod> loadAllMods() {
        try {
            return fileService.loadList(modsFilePath, Mod[].class);
        } catch (Exception e) {
            LogService.error("loadAllMods: Ошибка загрузки списка модов: {}", e);
            return new ArrayList<>();
        }
    }

    public Optional<Mod> getMod(int modId) {
        return loadAllMods().stream()
                .filter(mod -> mod.getId() == modId)
                .findFirst();
    }

    public List<ModSummary> getModsSummaryByName(String name) {
        return loadAllMods().stream()
                .filter(mod -> mod.getName().toLowerCase().contains(name.toLowerCase()))
                .map(Mod::toSummary)
                .collect(Collectors.toList());
    }


    public List<ModSummary> getModsSummaryByGame(int gameId) {
        return loadAllMods().stream()
                .filter(mod -> mod.getId() == gameId)
                .map(Mod::toSummary)
                .collect(Collectors.toList());
    }

    public List<ModSummary> getModsSummaryByAuthor(String author){
        return loadAllMods().stream()
                .filter(mod -> mod.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .map(Mod::toSummary)
                .collect(Collectors.toList());
    }

    public void addModToCatalog(String name, String description, String version, String author, int gameId, String imageBase64) {
//        List<Mod> mods = fileService.loadList(modsFilePath, Mod[].class);
//
//        int nextId = mods.stream().mapToInt(Mod::getId).max().orElse(0) + 1;
//
//        // Создаем полноценный объект Mod через public конструктор
//        Mod newMod = new Mod(
//                nextId,
//                name,
//                description,
//                version,
//                author,
//                gameId,
//                0, // На старте 0 лайков
//                imageBase64
//        );
//
//        mods.add(newMod);
//        fileService.saveList(modsFilePath, mods);
//        LogService.info("Мод '{}' (ID: {}) успешно добавлен в глобальный каталог Nexus.", name, nextId);
    }

    public boolean deleteModFromCatalog(int modId) {
//        try {
//            List<Mod> mods = fileService.loadList(modsFilePath, Mod[].class);
//            boolean removed = mods.removeIf(mod -> mod.getId() == modId);
//
//            if (removed) {
//                fileService.saveList(modsFilePath, mods);
//                LogService.info("Мод с ID {} был удален из каталога.", modId);
//                return true;
//            }
//
//            LogService.warn("Попытка удалить несуществующий мод с ID {}.", modId);
//            return false;
//        } catch (Exception e) {
//            LogService.error("Системная ошибка: невозможно прочитать базу пользователей! {}", e);
//            return false;
//        }
        return false;
    }


//    public void changeLikesCount(int modId, boolean isLike) {
//        List<Mod> mods = fileService.loadList(modsFilePath, Mod[].class);
//        boolean updated = false;
//
//        for (int i = 0; i < mods.size(); i++) {
//            Mod mod = mods.get(i);
//            if (mod.getId() == modId) {
//                int currentLikes = mod.getEndorsementsCount();
//                int newLikes = isLike ? currentLikes + 1 : Math.max(0, currentLikes - 1);
//
//                // Пересоздаем объект мода с обновленным количеством лайков
//                Mod updatedMod = new Mod(
//                        mod.getId(),
//                        mod.getName(),
//                        mod.getDescriptionFull(),
//                        mod.getVersion(),
//                        mod.getAuthor(),
//                        mod.getGameId(),
//                        newLikes, // Измененное поле
//                        mod.getImage()
//                );
//
//                mods.set(i, updatedMod);
//                updated = true;
//                break;
//            }
//        }
//
//        if (updated) {
//            fileService.saveList(modsFilePath, mods);
//        }
//    }
    //    private void toggleLike() {
//        int modId = InputService.inputField(currentUser.getUsername(), "ID мода", Integer::parseInt);
//        String username = currentUser.getUsername();
//
//        boolean hasLiked = currentUser.getLikedModIds().contains(modId);
//
//        if (hasLiked) {
//            currentUser.getLikedModIds().remove((Integer) modId);
////            modService.changeLikesCount(modId, false);
//            IO.println("Вы убрали лайк.");
//        } else {
//            currentUser.getLikedModIds().add(modId);
////            modService.changeLikesCount(modId, true);
//            IO.println("Вы поставили лайк моду!");
//        }
//        // Сохраняем состояние пользователя через UserService, чтобы лайк записался в базу
//        userService.saveUserListsUpdate(currentUser);
//    }
}