package app.client.logic;

import app.client.controller.ClientController;
import app.client.init.Init;
import app.client.init.Util;
import app.client.model.Player;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Game extends Thread {

    private Scene scene;
    private Canvas canvas;
    private Player player;
    private GraphicsContext ctx;

    public Game(GraphicsContext ctx, Canvas canvas, Scene scene){
        this.canvas = canvas;
        this.ctx = ctx;
        this.scene = scene;
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
        if (player.getX() > Init.CANVASSIZE - player.getSize()) {
            player.setX(0);
        } else if (player.getX() < 0) {
            player.setX(Init.CANVASSIZE - player.getSize());
        } else if (player.getY() > Init.CANVASSIZE - player.getSize()) {
            player.setY(0);
        } else if (player.getY() < 0) {
            player.setY(Init.CANVASSIZE - player.getSize());
        }
    }

    private void draw() {
        clearCanvas();
        player.drawPlayer(ctx);
    }

    private void update() {
        getKeyPressed();
        player.movePlayer();
        player.setxChange(0);
        player.setyChange(0);
        checkWallCollision();
    }

    public void clearCanvas() {
        ctx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

}
