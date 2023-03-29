package app.client.logic;

import app.init.Init;
import app.init.Util;
import app.client.model.Player;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.PrintWriter;

public class Game extends Thread {

    private Scene scene;
    private Canvas canvas;
    private Player player;
    private PrintWriter output;
    private GraphicsContext ctx;

    public Game(GraphicsContext ctx, Canvas canvas, Scene scene, PrintWriter output){
        this.canvas = canvas;
        this.ctx = ctx;
        this.scene = scene;
        this.output = output;
        this.player = new Player(100, 100, 10);
    }

    public void run() {
        while (true) {
            draw();
            update();
            Util.sleep();
        }
    }

    private void getKeyPressed() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case A:
                    player.setxChange(-10);
                    player.setyChange(0);
                    break;
                case D:
                    player.setxChange(10);
                    player.setyChange(0);
                    break;
                case W:
                    player.setxChange(0);
                    player.setyChange(-10);
                    break;
                case S:
                    player.setxChange(0);
                    player.setyChange(10);
                    break;
                case ESCAPE:
                    System.exit(0);
            }
        });
    }

    private void checkWallCollision() {
        if (player.getX() > Init.CANVAS_SIZE - player.getSize()) {
            player.setX(0);
        } else if (player.getX() < 0) {
            player.setX(Init.CANVAS_SIZE - player.getSize());
        } else if (player.getY() > Init.CANVAS_SIZE - player.getSize()) {
            player.setY(0);
        } else if (player.getY() < 0) {
            player.setY(Init.CANVAS_SIZE - player.getSize());
        }
    }

    private void draw() {
        clearCanvas();
        player.drawPlayer(ctx);
    }

    private void update() {
        getKeyPressed();
        player.movePlayer(output);
        player.setxChange(0);
        player.setyChange(0);
        checkWallCollision();
    }

    public void clearCanvas() {
        ctx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public static void createEnemy(){

    }

}
