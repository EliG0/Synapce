package ru.lgtu.app.model;

/**
 * Краткая информация о моде
 */

public class ModSummary {
    private final int id;
    private final String name;
    private final String version;
    private final String author;
    private final int gameId;
    private final String gameName;
    private final int likes;
    private final int downloads;

    public ModSummary(int id, String name, String version, String author, int gameId, String gameName, int likes, int downloads) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.author = author;
        this.gameId = gameId;
        this.gameName = gameName;
        this.likes = likes;
        this.downloads = downloads;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getVersion() { return version; }
    public String getAuthor() { return author; }
    public int getGameId() { return gameId; }
    public int getLikes() { return likes; }
    public String getGameName() {
        return gameName;
    }

    public int getDownloads() { return downloads;
    }
}