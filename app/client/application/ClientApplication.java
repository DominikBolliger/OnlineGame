package app.client.application;

import app.client.logic.ConfigData;
import app.init.Init;
import app.client.logic.ClientRunnable;
import app.client.logic.Game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientApplication extends Application {
    public Canvas canvas;
    public GraphicsContext ctx;
    private Group root;
    private Scene scene;
    private ClientRunnable handler;
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    @Override
    public void start(Stage primaryStage) throws Exception {
        ConfigData.loadData();
        int port = ConfigData.getPort();
        String IP = ConfigData.getIP();
        canvas = new Canvas(Init.CANVAS_SIZE, Init.CANVAS_SIZE);
        ctx = canvas.getGraphicsContext2D();
        root = new Group(canvas);
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Client");
        primaryStage.show();
        try {
            socket = new Socket(IP, port);
            handler = new ClientRunnable(socket);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            new Thread(handler).start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Game game = new Game(ctx, canvas, scene, output);
        game.start();
    }
}
