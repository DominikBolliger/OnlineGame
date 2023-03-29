package app.client.controller;

import app.client.application.ClientApplication;
import app.client.logic.ClientRunnable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientController {

    @FXML
    protected FlowPane root;
    private ClientRunnable handler;
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private static String layout;
    private static List<Button> buttonList = new ArrayList<>();

    @FXML
    public void initialize() {
        createButtons();
        try {
            socket = new Socket("localhost", 5000);
            handler = new ClientRunnable(socket);
            new Thread(handler).start();
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createButtons() {
        float size = (float) ClientApplication.getScreenSize().getHeight();
        size = (size / 2) / 5;
        for (int i = 1; i <= 25; i++) {
            Button button = new Button("test");
            buttonList.add(button);
            setButtonAction(button);
            button.setPrefHeight(size);
            button.setPrefWidth(size);
            root.getChildren().add(button);
        }
    }

    private void setButtonAction(Button button) {
        button.setOnAction(event -> {
            if (button.getStyle().contains("-fx-background-color: red")){
                layout = layout.substring(0, buttonList.indexOf(button)) + "x" + layout.substring(buttonList.indexOf(button) + 1);
            } else {
                layout = layout.substring(0, buttonList.indexOf(button)) + "r" + layout.substring(buttonList.indexOf(button) + 1);
            }
            output.println(layout);
        });
    }

    public static void setButtonStyles(){
        for (int i = 0; i < layout.length(); i++) {
            if (layout.charAt(i) == 'r'){
                buttonList.get(i).setStyle("-fx-background-radius: 0; -fx-background-color: red; -fx-border-color: black");
            } else {
                buttonList.get(i).setStyle("-fx-background-radius: 0");
            }
        }
    }

    public static void setLayout(String layoutPattern){
        layout = layoutPattern;
        setButtonStyles();
    }

}
