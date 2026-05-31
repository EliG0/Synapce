package ru.lgtu.xarxes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainController {
    private static MainController instance;

    public static MainController getInstance() {
        return instance;
    }

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private HBox mainHeader;

    @FXML
    private void handleMousePressed(javafx.scene.input.MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();

        if (event.getButton() == javafx.scene.input.MouseButton.PRIMARY && event.getClickCount() == 2) {
            handleMaximize();
        }
    }

    @FXML
    private void handleMouseDragged(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) mainHeader.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }



    @FXML
    private StackPane contentArea;

    public void setScreen(String fxmlPath) {
        try {
            Parent screen = FXMLLoader.load(getClass().getResource(fxmlPath));

            contentArea.getChildren().setAll(screen);

        } catch (IOException e) {
            System.err.println("Ошибка при загрузке экрана: " + fxmlPath);
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        instance = this;
        setScreen("/ru/lgtu/xarxes/fxml/dashboard.fxml");
    }




    @FXML
    private void openOghma() {
        setScreen("/ru/lgtu/xarxes/fxml/oghma.fxml");
        System.out.println("Открыта Огма (Локальные моды)");
    }

    @FXML private Button minimizeButton;
    @FXML private Button maximizeButton;


    @FXML
    private void handleClose() {
        System.out.println("Кнопка закрытия нажата!");
        javafx.application.Platform.exit();
    }
    @FXML
    private void handleMinimize() {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void handleMaximize() {
        Stage stage = (Stage) maximizeButton.getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }
}