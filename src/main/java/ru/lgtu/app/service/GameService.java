package ru.lgtu.app.service;
import ru.lgtu.app.model.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameService {
    private final FileService fileService;
    private final String gamesFilePath;

    public GameService(FileService fileService, ConfigService configService, InputService inputService, ImageService imageService){
        this.fileService = fileService;
        this.gamesFilePath = configService.getGamesJsonPath();
    }

    private List<Game> loadAllGames() {
        try {
            return fileService.loadList(gamesFilePath, Game[].class);
        } catch (Exception e) {
            LogService.error("loadAllMods: Ошибка загрузки списка игр: {}", e);
            return new ArrayList<>();
        }
    }

    public String getName(int id) {
        return loadAllGames().stream()
                .filter(g -> g.getId() == id)
                .map(Game::getName)
                .findFirst()
                .orElse("Неизвестная игра");
    }


    public List<Game> searchGame(String gameName) {
        return loadAllGames().stream()
            .filter(g -> g.getName().toLowerCase().contains(gameName.toLowerCase()))
                .collect(Collectors.toList());
    }
}