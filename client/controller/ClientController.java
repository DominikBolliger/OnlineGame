package client.controller;

import client.application.ClientApplication;
import client.logic.Handler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class ClientController {

    @FXML
    protected FlowPane root;
    private Handler handler;

    @FXML
    public void initialize() {
        createButtons();
        handler = new Handler();
        handler.start();
    }

    private void createButtons() {
        float size = (float) ClientApplication.getScreenSize().getHeight();
        size = (size / 2) / 5;
        for (int i = 1; i <= 25; i++) {
            Button button = new Button("test");
            button.setStyle("-fx-background-radius: 0");
            button.setOnAction(event -> {
                String[] styles = button.getStyle().split(";");
                for (String style : styles) {
                    if (style.equals("-fx-background-color: red")) {
                        button.setStyle("-fx-background-color: blue; -fx-background-radius: 0; -fx-border-color: black");
                    } else if(style.equals("-fx-background-color: blue")){
                        button.setStyle("-fx-background-color: red; -fx-background-radius: 0; -fx-border-color: black");
                    } else {
                        button.setStyle("-fx-background-color: red; -fx-background-radius: 0; -fx-border-color: black");
                    }
                    break;
                }
                handler.send();
            });
            button.setPrefHeight(size);
            button.setPrefWidth(size);
            root.getChildren().add(button);
        }
    }
}
