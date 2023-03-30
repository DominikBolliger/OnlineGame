package app.server.modell;

import app.server.logic.ServerThread;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.PrintWriter;

public class Player {

private int playerNumber;
    private ServerThread thread;

    public Player(int playerNumber, ServerThread thread) {
        this.playerNumber = playerNumber;
        this.thread = thread;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public ServerThread getThread() {
        return thread;
    }
}
