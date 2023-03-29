package app.client.application;

import app.client.init.Init;
import app.client.logic.Game;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ClientApplication extends Application {
    public static Canvas canvas;
    public static GraphicsContext ctx;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        canvas = new Canvas(Init.CANVASSIZE, Init.CANVASSIZE);
        ctx = canvas.getGraphicsContext2D();
        Group root = new Group(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Client");
        primaryStage.show();
        Game game = new Game(ctx, canvas, scene);
        game.start();
    }
}
