package ru.lgtu.app.model;

import ru.lgtu.app.service.LogService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Mod {
    private int id;
    private String author;
    private int gameId;
    private String gameName;
    private String name;
    private ModCategory category;
    private String version;
    private LocalDateTime publicationDate;
    private LocalDateTime updatedDate;
    private int sizeInMb;
    private String image;
    private String descriptionFull;
    private int endorsementsCount;
    private int downloadsTotalCount;

    public int getId() {
        return id;
    }
    public String getAuthor() {
        return author;
    }
    public int getGameId() {
        return gameId;
    }
    public String getName() {
        return name;
    }
    public ModCategory getCategory() {
        return category;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    public int getSizeInMb() {
        return sizeInMb;
    }
    public String getImage() {
        return image;
    }
    public String getDescriptionFull() {
        return descriptionFull;
    }
    public int getEndorsementsCount() {
        return endorsementsCount;
    }
    public int getDownloadsTotalCount() {
        return downloadsTotalCount;
    }


    public Mod() {
    }

    private Mod(int id, String author, int gameId, String name, ModCategory category, String version, LocalDateTime publicationDate, LocalDateTime updatedDate, int sizeInMb, String image, String descriptionFull, int endorsementsCount, int downloadsTotalCount) {
        this.id = id;
        this.author = author;
        this.gameId = gameId;
        this.name = name;
        this.category = category;
        this.version = version;
        this.publicationDate = publicationDate;
        this.updatedDate = updatedDate;
        this.sizeInMb = sizeInMb;
        this.image = image;
        this.descriptionFull = descriptionFull;
        this.endorsementsCount = endorsementsCount;
        this.downloadsTotalCount = downloadsTotalCount;
        LogService.info("Создан мод: {},{},{},{},{},{},{},{},{},{},{},{}", id, author, gameId, name, category, version, publicationDate, updatedDate, sizeInMb, image, descriptionFull, endorsementsCount, downloadsTotalCount);
    }

    public static Mod createNew(int id, String author, int gameId, String name, ModCategory category) {
        return new Mod(
                id,
                author,
                gameId,
                name,
                category,
                "",
                LocalDateTime.now(),
                LocalDateTime.now(),
                0,
                "",
                "Описание отсутствует",
                0,
                0
        );
    }


    @Override
    public String toString() {
        return "%d = %s = %s = %d = %s = %d = %d = %d MB = %s = %s = %s =".formatted(
                id,
                name,
                author,
                gameId,
                category,
                endorsementsCount,
                downloadsTotalCount,
                sizeInMb,
                version,
                updatedDate.toLocalDate(),
                publicationDate.toLocalDate());
    }

    // Для таблицы (короткая строка). oneLinePrint()
//    public String consoleShort() {
//        LogService.info("Выведен краткий отчет по моду: {}", id);
//        return "Мод: " + name + " | Автор: " + author + " | Игра: " + GameService.findById(gameId).getName() + " | Категория: " + category + ".";
//    }
//    // Для детального отчета. print()

    public String viewFull() {
        LogService.info("Выведен полный отчет по моду: {}", id);
        return """
                \n┍━☽【 %s 】☾╾─┈┄╌
                │ Автор: %s
                │ Игра: %s
                │ Категория: %s
                │ Описание: %s
                │ Размер: %d МБ
                │ Лайков: %d
                │ Скачиваний: %d
                │ Версия: %s
                │ Дата обновления: %s
                │ Дата публикации: %s
                │ ID: %d
                ├─────────────────────────────
                """.formatted(
                name,
                author,
                gameName,
                category,
                descriptionFull,
                sizeInMb,
                endorsementsCount,
                downloadsTotalCount,
                version,
                updatedDate.toLocalDate() + updatedDate.toLocalTime().format(DateTimeFormatter.ofPattern(" HH:mm:ss")),
                publicationDate.toLocalDate()+ updatedDate.toLocalTime().format(DateTimeFormatter.ofPattern(" HH:mm:ss")),
                id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mod mod = (Mod) o;
        return id == mod.id; // Считаем моды одинаковыми, если у них совпадает ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getShortDescription() {
        return (descriptionFull.length() > 50) ? descriptionFull.substring(0, 47) + "..." : descriptionFull;
    }

    public void incrementDownloads() {
        this.downloadsTotalCount++;
        LogService.info("Мод ID: {} | Добавлено: 1 скачивание | Теперь: {}", id, downloadsTotalCount);
    }

    public void like() {
        this.endorsementsCount++;
        LogService.info("Мод ID: {} | Добавлено: 1 лайк | Теперь: {}", id, endorsementsCount);
    }

    public void unlike(){
        if (this.endorsementsCount == 0) {
            String errorMessage = "Мод уже имеет 0 лайков, нельзя его убрать.";

            LogService.warn("Мод ID: {} | Удалить: 1 лайк. | Причина: {}", id, errorMessage);
            throw new IllegalStateException(errorMessage);
        }
        this.endorsementsCount--;
        LogService.info("Мод ID: {} | Удалить: 1 лайк | Теперь: {}", id, endorsementsCount);
    }

    public ModSummary toSummary() {
        return new ModSummary(
                this.id,
                this.name,
                this.version,
                this.author,
                this.gameId,
                this.gameName,
                this.endorsementsCount,
                this.downloadsTotalCount
        );
    }
}