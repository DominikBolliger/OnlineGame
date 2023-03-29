package app.client.controller;

import app.client.application.ClientApplication;
import app.client.logic.ClientRunnable;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    protected Canvas canvas;
    @FXML
    protected Group root;
    private ClientRunnable handler;
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private static GraphicsContext ctx;

    @FXML
    public void initialize() {
        ClientController.ctx = canvas.getGraphicsContext2D();
//        try {
//            socket = new Socket("localhost", 5000);
//            handler = new ClientRunnable(socket);
//            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            output = new PrintWriter(socket.getOutputStream(), true);
//            new Thread(handler).start();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    public static GraphicsContext getCtx() {
        return ctx;
    }



}
