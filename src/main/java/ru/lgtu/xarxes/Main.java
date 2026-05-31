package ru.lgtu.xarxes;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;


import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/lgtu/xarxes/fxml/main.fxml"));
            Parent root = loader.load();
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root, 1600, 900);
            scene.getStylesheets().add(getClass().getResource("/ru/lgtu/xarxes/styles/xarxes.css").toExternalForm());
            stage.setScene(scene);
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.show();

        } catch (IOException e) {
            System.err.println("Ошибка загрузки файла интерфейса! Проверь путь к FXML.");
            e.printStackTrace();
        }
    }
}
