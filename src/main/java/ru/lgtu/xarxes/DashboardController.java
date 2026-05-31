package ru.lgtu.xarxes;

import javafx.fxml.FXML;

public class DashboardController {
    @FXML
    private void handleSwitchToHermaeus() {
        MainController.getInstance().setScreen("/ru/lgtu/xarxes/fxml/hermaeus.fxml");
        System.out.println("Dashboard запросил переключение на Hermaeus");
    }
}