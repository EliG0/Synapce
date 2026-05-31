package ru.lgtu.xarxes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.TilePane;

import java.io.IOException;

public class ModsContainerController {

    @FXML
    private TilePane modsContainer;

    @FXML
    private void handleDebug() {
        try {
            Parent modCard = FXMLLoader.load(getClass().getResource("/ru/lgtu/xarxes/fxml/mod.fxml"));

            modsContainer.getChildren().add(modCard);

            System.out.println("Карточка мода успешно отрендерилась и добавлена в сетку!");
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке fxml карточки мода!");
            e.printStackTrace();
        }
    }

//    public void addModToGrid(Mod mod) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/lgtu/xarxes/fxml/mod.fxml"));
//            Parent modCard = loader.load();
//
//            ModCardController cardController = loader.getController();
//
//            cardController.setModData(mod);
//
//            modsContainer.getChildren().add(modCard);
//
//        } catch (IOException e) {
//            System.err.println("Не удалось отобразить мод: " + mod.getName());
//            e.printStackTrace();
//        }
//    }
}
